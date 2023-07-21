package org.apache.cordova.media;

import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;
import org.apache.cordova.media.AudioPlayer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioHandler extends CordovaPlugin {
    public static final int PERMISSION_DENIED_ERROR = 20;
    public static int RECORD_AUDIO = 0;
    public static String TAG = "AudioHandler";
    public static int WRITE_EXTERNAL_STORAGE = 1;
    public static String[] permissions = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private String fileUriStr;
    private CallbackContext messageChannel;
    private int origVolumeStream = -1;
    ArrayList<AudioPlayer> pausedForPhone = new ArrayList<>();
    HashMap<String, AudioPlayer> players = new HashMap<>();
    private String recordId;

    /* access modifiers changed from: protected */
    public void getWritePermission(int requestCode) {
        PermissionHelper.requestPermission(this, requestCode, permissions[WRITE_EXTERNAL_STORAGE]);
    }

    /* access modifiers changed from: protected */
    public void getMicPermission(int requestCode) {
        PermissionHelper.requestPermission(this, requestCode, permissions[RECORD_AUDIO]);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        String fileUriStr2;
        CordovaResourceApi resourceApi = this.webView.getResourceApi();
        PluginResult.Status status = PluginResult.Status.OK;
        if (action.equals("startRecordingAudio")) {
            this.recordId = args.getString(0);
            String target = args.getString(1);
            try {
                this.fileUriStr = resourceApi.remapUri(Uri.parse(target)).toString();
            } catch (IllegalArgumentException e) {
                this.fileUriStr = target;
            }
            promptForRecord();
        } else if (action.equals("stopRecordingAudio")) {
            stopRecordingAudio(args.getString(0));
        } else if (action.equals("startPlayingAudio")) {
            String target2 = args.getString(1);
            try {
                fileUriStr2 = resourceApi.remapUri(Uri.parse(target2)).toString();
            } catch (IllegalArgumentException e2) {
                fileUriStr2 = target2;
            }
            startPlayingAudio(args.getString(0), FileHelper.stripFileProtocol(fileUriStr2));
        } else if (action.equals("seekToAudio")) {
            seekToAudio(args.getString(0), args.getInt(1));
        } else if (action.equals("pausePlayingAudio")) {
            pausePlayingAudio(args.getString(0));
        } else if (action.equals("stopPlayingAudio")) {
            stopPlayingAudio(args.getString(0));
        } else if (action.equals("setVolume")) {
            try {
                setVolume(args.getString(0), Float.parseFloat(args.getString(1)));
            } catch (NumberFormatException e3) {
            }
        } else if (action.equals("getCurrentPositionAudio")) {
            callbackContext.sendPluginResult(new PluginResult(status, getCurrentPositionAudio(args.getString(0))));
            return true;
        } else if (action.equals("getDurationAudio")) {
            callbackContext.sendPluginResult(new PluginResult(status, getDurationAudio(args.getString(0), args.getString(1))));
            return true;
        } else if (action.equals("create")) {
            getOrCreatePlayer(args.getString(0), FileHelper.stripFileProtocol(args.getString(1)));
        } else if (action.equals("release")) {
            callbackContext.sendPluginResult(new PluginResult(status, release(args.getString(0))));
            return true;
        } else if (!action.equals("messageChannel")) {
            return false;
        } else {
            this.messageChannel = callbackContext;
            return true;
        }
        callbackContext.sendPluginResult(new PluginResult(status, ""));
        return true;
    }

    public void onDestroy() {
        if (!this.players.isEmpty()) {
            onLastPlayerReleased();
        }
        for (AudioPlayer audio : this.players.values()) {
            audio.destroy();
        }
        this.players.clear();
    }

    public void onReset() {
        onDestroy();
    }

    public Object onMessage(String id, Object data) {
        if (id.equals("telephone")) {
            if ("ringing".equals(data) || "offhook".equals(data)) {
                for (AudioPlayer audio : this.players.values()) {
                    if (audio.getState() == AudioPlayer.STATE.MEDIA_RUNNING.ordinal()) {
                        this.pausedForPhone.add(audio);
                        audio.pausePlaying();
                    }
                }
            } else if ("idle".equals(data)) {
                Iterator<AudioPlayer> it = this.pausedForPhone.iterator();
                while (it.hasNext()) {
                    it.next().startPlaying((String) null);
                }
                this.pausedForPhone.clear();
            }
        }
        return null;
    }

    private AudioPlayer getOrCreatePlayer(String id, String file) {
        AudioPlayer ret = this.players.get(id);
        if (ret != null) {
            return ret;
        }
        if (this.players.isEmpty()) {
            onFirstPlayerCreated();
        }
        AudioPlayer ret2 = new AudioPlayer(this, id, file);
        this.players.put(id, ret2);
        return ret2;
    }

    private boolean release(String id) {
        AudioPlayer audio = this.players.remove(id);
        if (audio == null) {
            return false;
        }
        if (this.players.isEmpty()) {
            onLastPlayerReleased();
        }
        audio.destroy();
        return true;
    }

    public void startRecordingAudio(String id, String file) {
        getOrCreatePlayer(id, file).startRecording(file);
    }

    public void stopRecordingAudio(String id) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            audio.stopRecording();
        }
    }

    public void startPlayingAudio(String id, String file) {
        getOrCreatePlayer(id, file).startPlaying(file);
    }

    public void seekToAudio(String id, int milliseconds) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            audio.seekToPlaying(milliseconds);
        }
    }

    public void pausePlayingAudio(String id) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            audio.pausePlaying();
        }
    }

    public void stopPlayingAudio(String id) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            audio.stopPlaying();
        }
    }

    public float getCurrentPositionAudio(String id) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            return ((float) audio.getCurrentPosition()) / 1000.0f;
        }
        return -1.0f;
    }

    public float getDurationAudio(String id, String file) {
        return getOrCreatePlayer(id, file).getDuration(file);
    }

    public void setAudioOutputDevice(int output) {
        AudioManager audiMgr = (AudioManager) this.f21cordova.getActivity().getSystemService("audio");
        if (output == 2) {
            audiMgr.setRouting(0, 2, -1);
        } else if (output == 1) {
            audiMgr.setRouting(0, 1, -1);
        } else {
            System.out.println("AudioHandler.setAudioOutputDevice() Error: Unknown output device.");
        }
    }

    public int getAudioOutputDevice() {
        AudioManager audiMgr = (AudioManager) this.f21cordova.getActivity().getSystemService("audio");
        if (audiMgr.getRouting(0) == 1) {
            return 1;
        }
        if (audiMgr.getRouting(0) == 2) {
            return 2;
        }
        return -1;
    }

    public void setVolume(String id, float volume) {
        AudioPlayer audio = this.players.get(id);
        if (audio != null) {
            audio.setVolume(volume);
        } else {
            System.out.println("AudioHandler.setVolume() Error: Unknown Audio Player " + id);
        }
    }

    private void onFirstPlayerCreated() {
        this.origVolumeStream = this.f21cordova.getActivity().getVolumeControlStream();
        this.f21cordova.getActivity().setVolumeControlStream(3);
    }

    private void onLastPlayerReleased() {
        if (this.origVolumeStream != -1) {
            this.f21cordova.getActivity().setVolumeControlStream(this.origVolumeStream);
            this.origVolumeStream = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void sendEventMessage(String action, JSONObject actionData) {
        JSONObject message = new JSONObject();
        try {
            message.put("action", action);
            if (actionData != null) {
                message.put(action, actionData);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create event message", e);
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, message);
        pluginResult.setKeepCallback(true);
        if (this.messageChannel != null) {
            this.messageChannel.sendPluginResult(pluginResult);
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions2, int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == -1) {
                this.messageChannel.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
                return;
            }
        }
        promptForRecord();
    }

    private void promptForRecord() {
        if (PermissionHelper.hasPermission(this, permissions[WRITE_EXTERNAL_STORAGE]) && PermissionHelper.hasPermission(this, permissions[RECORD_AUDIO])) {
            startRecordingAudio(this.recordId, FileHelper.stripFileProtocol(this.fileUriStr));
        } else if (PermissionHelper.hasPermission(this, permissions[RECORD_AUDIO])) {
            getWritePermission(WRITE_EXTERNAL_STORAGE);
        } else {
            getMicPermission(RECORD_AUDIO);
        }
    }
}

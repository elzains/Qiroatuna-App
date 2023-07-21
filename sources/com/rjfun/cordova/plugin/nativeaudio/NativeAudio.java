package com.rjfun.cordova.plugin.nativeaudio;

import android.media.AudioManager;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAudio extends CordovaPlugin implements AudioManager.OnAudioFocusChangeListener {
    public static final String ADD_COMPLETE_LISTENER = "addCompleteListener";
    public static final String ERROR_AUDIOID_EXISTS = "A reference already exists for the specified audio id.";
    public static final String ERROR_NO_AUDIOID = "A reference does not exist for the specified audio id.";
    private static final String LOGTAG = "NativeAudio";
    public static final String LOOP = "loop";
    public static final String OPT_FADE_MUSIC = "fadeMusic";
    public static final String PLAY = "play";
    public static final String PRELOAD_COMPLEX = "preloadComplex";
    public static final String PRELOAD_SIMPLE = "preloadSimple";
    public static final String SET_OPTIONS = "setOptions";
    public static final String SET_VOLUME_FOR_COMPLEX_ASSET = "setVolumeForComplexAsset";
    public static final String STOP = "stop";
    public static final String UNLOAD = "unload";
    private static HashMap<String, NativeAudioAsset> assetMap;
    /* access modifiers changed from: private */
    public static HashMap<String, CallbackContext> completeCallbacks;
    private static ArrayList<NativeAudioAsset> resumeList;
    private boolean fadeMusic = false;

    public void setOptions(JSONObject options) {
        if (options != null && options.has(OPT_FADE_MUSIC)) {
            this.fadeMusic = options.optBoolean(OPT_FADE_MUSIC);
        }
    }

    /* access modifiers changed from: private */
    public PluginResult executePreload(JSONArray data) {
        double volume;
        int voices;
        try {
            String audioID = data.getString(0);
            if (assetMap.containsKey(audioID)) {
                return new PluginResult(PluginResult.Status.ERROR, ERROR_AUDIOID_EXISTS);
            }
            String assetPath = data.getString(1);
            Log.d(LOGTAG, "preloadComplex - " + audioID + ": " + assetPath);
            if (data.length() <= 2) {
                volume = 1.0d;
            } else {
                volume = data.getDouble(2);
            }
            if (data.length() <= 3) {
                voices = 1;
            } else {
                voices = data.getInt(3);
            }
            assetMap.put(audioID, new NativeAudioAsset(this.f21cordova.getActivity().getApplicationContext().getResources().getAssets().openFd("www/".concat(assetPath)), voices, (float) volume));
            return new PluginResult(PluginResult.Status.OK);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        } catch (IOException e2) {
            return new PluginResult(PluginResult.Status.ERROR, e2.toString());
        }
    }

    /* access modifiers changed from: private */
    public PluginResult executePlayOrLoop(String action, JSONArray data) {
        try {
            final String audioID = data.getString(0);
            if (!assetMap.containsKey(audioID)) {
                return new PluginResult(PluginResult.Status.ERROR, ERROR_NO_AUDIOID);
            }
            NativeAudioAsset asset = assetMap.get(audioID);
            if (LOOP.equals(action)) {
                asset.loop();
            } else {
                asset.play(new Callable<Void>() {
                    public Void call() throws Exception {
                        CallbackContext callbackContext;
                        if (NativeAudio.completeCallbacks == null || (callbackContext = (CallbackContext) NativeAudio.completeCallbacks.get(audioID)) == null) {
                            return null;
                        }
                        JSONObject done = new JSONObject();
                        done.put("id", audioID);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, done));
                        return null;
                    }
                });
            }
            return new PluginResult(PluginResult.Status.OK);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        } catch (IOException e2) {
            return new PluginResult(PluginResult.Status.ERROR, e2.toString());
        }
    }

    /* access modifiers changed from: private */
    public PluginResult executeStop(JSONArray data) {
        try {
            String audioID = data.getString(0);
            if (!assetMap.containsKey(audioID)) {
                return new PluginResult(PluginResult.Status.ERROR, ERROR_NO_AUDIOID);
            }
            assetMap.get(audioID).stop();
            return new PluginResult(PluginResult.Status.OK);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        }
    }

    /* access modifiers changed from: private */
    public PluginResult executeUnload(JSONArray data) {
        try {
            String audioID = data.getString(0);
            Log.d(LOGTAG, "unload - " + audioID);
            if (!assetMap.containsKey(audioID)) {
                return new PluginResult(PluginResult.Status.ERROR, ERROR_NO_AUDIOID);
            }
            assetMap.get(audioID).unload();
            assetMap.remove(audioID);
            return new PluginResult(PluginResult.Status.OK);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        } catch (IOException e2) {
            return new PluginResult(PluginResult.Status.ERROR, e2.toString());
        }
    }

    /* access modifiers changed from: private */
    public PluginResult executeSetVolumeForComplexAsset(JSONArray data) {
        try {
            String audioID = data.getString(0);
            float volume = (float) data.getDouble(1);
            Log.d(LOGTAG, "setVolume - " + audioID);
            if (!assetMap.containsKey(audioID)) {
                return new PluginResult(PluginResult.Status.ERROR, ERROR_NO_AUDIOID);
            }
            assetMap.get(audioID).setVolume(volume);
            return new PluginResult(PluginResult.Status.OK);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        int requestAudioFocus = ((AudioManager) this.f21cordova.getActivity().getSystemService("audio")).requestAudioFocus(this, 3, 1);
        this.webView.setButtonPlumbedToJs(25, false);
        this.webView.setButtonPlumbedToJs(24, false);
    }

    public boolean execute(final String action, final JSONArray data, final CallbackContext callbackContext) {
        Log.d(LOGTAG, "Plugin Called: " + action);
        PluginResult result = null;
        initSoundPool();
        try {
            if (SET_OPTIONS.equals(action)) {
                setOptions(data.optJSONObject(0));
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else if (PRELOAD_SIMPLE.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.sendPluginResult(NativeAudio.this.executePreload(data));
                    }
                });
            } else if (PRELOAD_COMPLEX.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.sendPluginResult(NativeAudio.this.executePreload(data));
                    }
                });
            } else if (PLAY.equals(action) || LOOP.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.sendPluginResult(NativeAudio.this.executePlayOrLoop(action, data));
                    }
                });
            } else if (STOP.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.sendPluginResult(NativeAudio.this.executeStop(data));
                    }
                });
            } else if (UNLOAD.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        PluginResult unused = NativeAudio.this.executeStop(data);
                        callbackContext.sendPluginResult(NativeAudio.this.executeUnload(data));
                    }
                });
            } else if (ADD_COMPLETE_LISTENER.equals(action)) {
                if (completeCallbacks == null) {
                    completeCallbacks = new HashMap<>();
                }
                try {
                    completeCallbacks.put(data.getString(0), callbackContext);
                } catch (JSONException e) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.toString()));
                }
            } else if (SET_VOLUME_FOR_COMPLEX_ASSET.equals(action)) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.sendPluginResult(NativeAudio.this.executeSetVolumeForComplexAsset(data));
                    }
                });
            } else {
                result = new PluginResult(PluginResult.Status.OK);
            }
        } catch (Exception ex) {
            result = new PluginResult(PluginResult.Status.ERROR, ex.toString());
        }
        if (result == null) {
            return true;
        }
        callbackContext.sendPluginResult(result);
        return true;
    }

    private void initSoundPool() {
        if (assetMap == null) {
            assetMap = new HashMap<>();
        }
        if (resumeList == null) {
            resumeList = new ArrayList<>();
        }
    }

    public void onAudioFocusChange(int focusChange) {
        if (!(focusChange == -2 || focusChange == 1 || focusChange != -1)) {
        }
    }

    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        for (Map.Entry<String, NativeAudioAsset> entry : assetMap.entrySet()) {
            NativeAudioAsset asset = entry.getValue();
            if (asset.pause()) {
                resumeList.add(asset);
            }
        }
    }

    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        while (!resumeList.isEmpty()) {
            resumeList.remove(0).resume();
        }
    }
}

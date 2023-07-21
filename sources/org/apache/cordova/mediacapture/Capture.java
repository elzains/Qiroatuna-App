package org.apache.cordova.mediacapture;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Capture extends CordovaPlugin {
    private static final String AUDIO_3GPP = "audio/3gpp";
    private static final int CAPTURE_AUDIO = 0;
    private static final int CAPTURE_IMAGE = 1;
    private static final int CAPTURE_INTERNAL_ERR = 0;
    private static final int CAPTURE_NO_MEDIA_FILES = 3;
    private static final int CAPTURE_VIDEO = 2;
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String LOG_TAG = "Capture";
    private static final String VIDEO_3GPP = "video/3gpp";
    private static final String VIDEO_MP4 = "video/mp4";
    /* access modifiers changed from: private */
    public CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public int duration;
    /* access modifiers changed from: private */
    public long limit;
    private int numPics;
    /* access modifiers changed from: private */
    public int quality;
    /* access modifiers changed from: private */
    public JSONArray results;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        this.limit = 1;
        this.duration = 0;
        this.results = new JSONArray();
        this.quality = 1;
        JSONObject options = args.optJSONObject(0);
        if (options != null) {
            this.limit = options.optLong("limit", 1);
            this.duration = options.optInt("duration", 0);
            this.quality = options.optInt("quality", 1);
        }
        if (action.equals("getFormatData")) {
            callbackContext2.success(getFormatData(args.getString(0), args.getString(1)));
            return true;
        } else if (action.equals("captureAudio")) {
            captureAudio();
            return true;
        } else if (action.equals("captureImage")) {
            captureImage();
            return true;
        } else if (!action.equals("captureVideo")) {
            return false;
        } else {
            captureVideo(this.duration, this.quality);
            return true;
        }
    }

    private JSONObject getFormatData(String filePath, String mimeType) throws JSONException {
        Uri fileUrl;
        if (filePath.startsWith("file:")) {
            fileUrl = Uri.parse(filePath);
        } else {
            fileUrl = Uri.fromFile(new File(filePath));
        }
        JSONObject obj = new JSONObject();
        obj.put("height", 0);
        obj.put("width", 0);
        obj.put("bitrate", 0);
        obj.put("duration", 0);
        obj.put("codecs", "");
        if (mimeType == null || mimeType.equals("") || "null".equals(mimeType)) {
            mimeType = FileHelper.getMimeType(fileUrl, this.f21cordova);
        }
        Log.d(LOG_TAG, "Mime type = " + mimeType);
        if (mimeType.equals(IMAGE_JPEG) || filePath.endsWith(".jpg")) {
            return getImageData(fileUrl, obj);
        }
        if (mimeType.endsWith(AUDIO_3GPP)) {
            return getAudioVideoData(filePath, obj, false);
        }
        if (mimeType.equals(VIDEO_3GPP) || mimeType.equals(VIDEO_MP4)) {
            return getAudioVideoData(filePath, obj, true);
        }
        return obj;
    }

    private JSONObject getImageData(Uri fileUrl, JSONObject obj) throws JSONException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileUrl.getPath(), options);
        obj.put("height", options.outHeight);
        obj.put("width", options.outWidth);
        return obj;
    }

    private JSONObject getAudioVideoData(String filePath, JSONObject obj, boolean video) throws JSONException {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(filePath);
            player.prepare();
            obj.put("duration", player.getDuration() / 1000);
            if (video) {
                obj.put("height", player.getVideoHeight());
                obj.put("width", player.getVideoWidth());
            }
        } catch (IOException e) {
            Log.d(LOG_TAG, "Error: loading video file");
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public void captureAudio() {
        this.f21cordova.startActivityForResult(this, new Intent("android.provider.MediaStore.RECORD_SOUND"), 0);
    }

    /* access modifiers changed from: private */
    public String getTempDirectoryPath() {
        File cache = this.f21cordova.getActivity().getCacheDir();
        cache.mkdirs();
        return cache.getAbsolutePath();
    }

    /* access modifiers changed from: private */
    public void captureImage() {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(getTempDirectoryPath(), "Capture.jpg");
        try {
            createWritableFile(photo);
            intent.putExtra("output", Uri.fromFile(photo));
            this.f21cordova.startActivityForResult(this, intent, 1);
        } catch (IOException ex) {
            fail(createErrorObject(0, ex.toString()));
        }
    }

    private static void createWritableFile(File file) throws IOException {
        file.createNewFile();
        file.setWritable(true, false);
    }

    /* access modifiers changed from: private */
    public void captureVideo(int duration2, int quality2) {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (Build.VERSION.SDK_INT > 7) {
            intent.putExtra("android.intent.extra.durationLimit", duration2);
            intent.putExtra("android.intent.extra.videoQuality", quality2);
        }
        this.f21cordova.startActivityForResult(this, intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (resultCode == -1) {
            if (requestCode == 0) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Capture.this.results.put(Capture.this.createMediaFile(intent.getData()));
                        if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.this.results));
                        } else {
                            Capture.this.captureAudio();
                        }
                    }
                });
            } else if (requestCode == 1) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Uri uri;
                        try {
                            ContentValues values = new ContentValues();
                            values.put("mime_type", Capture.IMAGE_JPEG);
                            try {
                                uri = this.f21cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                            } catch (UnsupportedOperationException e) {
                                LOG.m21d(Capture.LOG_TAG, "Can't write to external media storage.");
                                try {
                                    uri = this.f21cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
                                } catch (UnsupportedOperationException e2) {
                                    LOG.m21d(Capture.LOG_TAG, "Can't write to internal media storage.");
                                    this.fail(Capture.this.createErrorObject(0, "Error capturing image - no media storage found."));
                                    return;
                                }
                            }
                            FileInputStream fis = new FileInputStream(Capture.this.getTempDirectoryPath() + "/Capture.jpg");
                            OutputStream os = this.f21cordova.getActivity().getContentResolver().openOutputStream(uri);
                            byte[] buffer = new byte[4096];
                            while (true) {
                                int len = fis.read(buffer);
                                if (len == -1) {
                                    break;
                                }
                                os.write(buffer, 0, len);
                            }
                            os.flush();
                            os.close();
                            fis.close();
                            Capture.this.results.put(Capture.this.createMediaFile(uri));
                            Capture.this.checkForDuplicateImage();
                            if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.this.results));
                            } else {
                                Capture.this.captureImage();
                            }
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            this.fail(Capture.this.createErrorObject(0, "Error capturing image."));
                        }
                    }
                });
            } else if (requestCode == 2) {
                this.f21cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Uri data = null;
                        if (intent != null) {
                            data = intent.getData();
                        }
                        if (data == null) {
                            data = Uri.fromFile(new File(Capture.this.getTempDirectoryPath(), "Capture.avi"));
                        }
                        if (data == null) {
                            this.fail(Capture.this.createErrorObject(3, "Error: data is null"));
                            return;
                        }
                        Capture.this.results.put(Capture.this.createMediaFile(data));
                        if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.this.results));
                        } else {
                            Capture.this.captureVideo(Capture.this.duration, Capture.this.quality);
                        }
                    }
                });
            }
        } else if (resultCode == 0) {
            if (this.results.length() > 0) {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.results));
            } else {
                fail(createErrorObject(3, "Canceled."));
            }
        } else if (this.results.length() > 0) {
            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.results));
        } else {
            fail(createErrorObject(3, "Did not complete!"));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.apache.cordova.PluginManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.cordova.PluginManager} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject createMediaFile(android.net.Uri r15) {
        /*
            r14 = this;
            org.apache.cordova.CordovaWebView r11 = r14.webView
            org.apache.cordova.CordovaResourceApi r11 = r11.getResourceApi()
            java.io.File r4 = r11.mapUriToFile(r15)
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            org.apache.cordova.CordovaWebView r11 = r14.webView
            java.lang.Class r10 = r11.getClass()
            r7 = 0
            java.lang.String r11 = "getPluginManager"
            r12 = 0
            java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            java.lang.reflect.Method r5 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            org.apache.cordova.CordovaWebView r11 = r14.webView     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            r12 = 0
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            java.lang.Object r11 = r5.invoke(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            r0 = r11
            org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchMethodException -> 0x00de, IllegalAccessException -> 0x00db, InvocationTargetException -> 0x00d8 }
            r7 = r0
        L_0x002c:
            if (r7 != 0) goto L_0x003e
            java.lang.String r11 = "pluginManager"
            java.lang.reflect.Field r8 = r10.getField(r11)     // Catch:{ NoSuchFieldException -> 0x00d5, IllegalAccessException -> 0x00d2 }
            org.apache.cordova.CordovaWebView r11 = r14.webView     // Catch:{ NoSuchFieldException -> 0x00d5, IllegalAccessException -> 0x00d2 }
            java.lang.Object r11 = r8.get(r11)     // Catch:{ NoSuchFieldException -> 0x00d5, IllegalAccessException -> 0x00d2 }
            r0 = r11
            org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchFieldException -> 0x00d5, IllegalAccessException -> 0x00d2 }
            r7 = r0
        L_0x003e:
            java.lang.String r11 = "File"
            org.apache.cordova.CordovaPlugin r3 = r7.getPlugin(r11)
            org.apache.cordova.file.FileUtils r3 = (org.apache.cordova.file.FileUtils) r3
            java.lang.String r11 = r4.getAbsolutePath()
            org.apache.cordova.file.LocalFilesystemURL r9 = r3.filesystemURLforLocalPath(r11)
            java.lang.String r11 = "name"
            java.lang.String r12 = r4.getName()     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r11 = "fullPath"
            java.net.URI r12 = r4.toURI()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
            if (r9 == 0) goto L_0x006f
            java.lang.String r11 = "localURL"
            java.lang.String r12 = r9.toString()     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
        L_0x006f:
            java.io.File r11 = r4.getAbsoluteFile()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r12 = ".3gp"
            boolean r11 = r11.endsWith(r12)     // Catch:{ JSONException -> 0x00bd }
            if (r11 != 0) goto L_0x008f
            java.io.File r11 = r4.getAbsoluteFile()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r12 = ".3gpp"
            boolean r11 = r11.endsWith(r12)     // Catch:{ JSONException -> 0x00bd }
            if (r11 == 0) goto L_0x00c2
        L_0x008f:
            java.lang.String r11 = r15.toString()     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r12 = "/audio/"
            boolean r11 = r11.contains(r12)     // Catch:{ JSONException -> 0x00bd }
            if (r11 == 0) goto L_0x00b5
            java.lang.String r11 = "type"
            java.lang.String r12 = "audio/3gpp"
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
        L_0x00a2:
            java.lang.String r11 = "lastModifiedDate"
            long r12 = r4.lastModified()     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r11 = "size"
            long r12 = r4.length()     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
        L_0x00b4:
            return r6
        L_0x00b5:
            java.lang.String r11 = "type"
            java.lang.String r12 = "video/3gpp"
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
            goto L_0x00a2
        L_0x00bd:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00b4
        L_0x00c2:
            java.lang.String r11 = "type"
            android.net.Uri r12 = android.net.Uri.fromFile(r4)     // Catch:{ JSONException -> 0x00bd }
            org.apache.cordova.CordovaInterface r13 = r14.f21cordova     // Catch:{ JSONException -> 0x00bd }
            java.lang.String r12 = org.apache.cordova.mediacapture.FileHelper.getMimeType(r12, r13)     // Catch:{ JSONException -> 0x00bd }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00bd }
            goto L_0x00a2
        L_0x00d2:
            r11 = move-exception
            goto L_0x003e
        L_0x00d5:
            r11 = move-exception
            goto L_0x003e
        L_0x00d8:
            r11 = move-exception
            goto L_0x002c
        L_0x00db:
            r11 = move-exception
            goto L_0x002c
        L_0x00de:
            r11 = move-exception
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.mediacapture.Capture.createMediaFile(android.net.Uri):org.json.JSONObject");
    }

    /* access modifiers changed from: private */
    public JSONObject createErrorObject(int code, String message) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", code);
            obj.put("message", message);
        } catch (JSONException e) {
        }
        return obj;
    }

    public void fail(JSONObject err) {
        this.callbackContext.error(err);
    }

    private Cursor queryImgDB(Uri contentStore) {
        return this.f21cordova.getActivity().getContentResolver().query(contentStore, new String[]{"_id"}, (String) null, (String[]) null, (String) null);
    }

    /* access modifiers changed from: private */
    public void checkForDuplicateImage() {
        Uri contentStore = whichContentStore();
        Cursor cursor = queryImgDB(contentStore);
        if (cursor.getCount() - this.numPics == 2) {
            cursor.moveToLast();
            this.f21cordova.getActivity().getContentResolver().delete(Uri.parse(contentStore + "/" + (Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))).intValue() - 1)), (String) null, (String[]) null);
        }
    }

    private Uri whichContentStore() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }
}

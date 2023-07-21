package org.apache.cordova.filetransfer;

import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileTransfer extends CordovaPlugin {
    public static int ABORTED_ERR = 4;
    private static final String BOUNDARY = "+++++";
    public static int CONNECTION_ERR = 3;
    /* access modifiers changed from: private */
    public static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    public static int FILE_NOT_FOUND_ERR = 1;
    public static int INVALID_URL_ERR = 2;
    private static final String LINE_END = "\r\n";
    private static final String LINE_START = "--";
    private static final String LOG_TAG = "FileTransfer";
    private static final int MAX_BUFFER_SIZE = 16384;
    public static int NOT_MODIFIED_ERR = 5;
    /* access modifiers changed from: private */
    public static HashMap<String, RequestContext> activeRequests = new HashMap<>();
    private static final TrustManager[] trustAllCerts = {new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }};

    private static final class RequestContext {
        boolean aborted;
        CallbackContext callbackContext;
        HttpURLConnection connection;
        String source;
        String target;
        File targetFile;

        RequestContext(String source2, String target2, CallbackContext callbackContext2) {
            this.source = source2;
            this.target = target2;
            this.callbackContext = callbackContext2;
        }

        /* access modifiers changed from: package-private */
        public void sendPluginResult(PluginResult pluginResult) {
            synchronized (this) {
                if (!this.aborted) {
                    this.callbackContext.sendPluginResult(pluginResult);
                }
            }
        }
    }

    private static abstract class TrackingInputStream extends FilterInputStream {
        public abstract long getTotalRawBytesRead();

        public TrackingInputStream(InputStream in) {
            super(in);
        }
    }

    private static class ExposedGZIPInputStream extends GZIPInputStream {
        public ExposedGZIPInputStream(InputStream in) throws IOException {
            super(in);
        }

        public Inflater getInflater() {
            return this.inf;
        }
    }

    private static class TrackingGZIPInputStream extends TrackingInputStream {
        private ExposedGZIPInputStream gzin;

        public TrackingGZIPInputStream(ExposedGZIPInputStream gzin2) throws IOException {
            super(gzin2);
            this.gzin = gzin2;
        }

        public long getTotalRawBytesRead() {
            return this.gzin.getInflater().getBytesRead();
        }
    }

    private static class SimpleTrackingInputStream extends TrackingInputStream {
        private long bytesRead = 0;

        public SimpleTrackingInputStream(InputStream stream) {
            super(stream);
        }

        private int updateBytesRead(int newBytesRead) {
            if (newBytesRead != -1) {
                this.bytesRead += (long) newBytesRead;
            }
            return newBytesRead;
        }

        public int read() throws IOException {
            return updateBytesRead(super.read());
        }

        public int read(byte[] bytes, int offset, int count) throws IOException {
            return updateBytesRead(super.read(bytes, offset, count));
        }

        public long getTotalRawBytesRead() {
            return this.bytesRead;
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("upload") || action.equals("download")) {
            String source = args.getString(0);
            String target = args.getString(1);
            if (action.equals("upload")) {
                upload(source, target, args, callbackContext);
                return true;
            }
            download(source, target, args, callbackContext);
            return true;
        } else if (!action.equals("abort")) {
            return false;
        } else {
            abort(args.getString(0));
            callbackContext.success();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static void addHeadersToRequest(URLConnection connection, JSONObject headers) {
        try {
            Iterator<String> keys = headers.keys();
            while (keys.hasNext()) {
                String headerKey = keys.next().toString();
                String cleanHeaderKey = headerKey.replaceAll("\\n", "").replaceAll("\\s+", "").replaceAll(":", "").replaceAll("[^\\x20-\\x7E]+", "");
                JSONArray headerValues = headers.optJSONArray(headerKey);
                if (headerValues == null) {
                    headerValues = new JSONArray();
                    headerValues.put(headers.getString(headerKey).replaceAll("\\s+", " ").replaceAll("\\n", " ").replaceAll("[^\\x20-\\x7E]+", " "));
                }
                connection.setRequestProperty(cleanHeaderKey, headerValues.getString(0));
                for (int i = 1; i < headerValues.length(); i++) {
                    connection.addRequestProperty(headerKey, headerValues.getString(i));
                }
            }
        } catch (JSONException e) {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCookies(java.lang.String r12) {
        /*
            r11 = this;
            r4 = 0
            r1 = 0
            org.apache.cordova.CordovaWebView r7 = r11.webView
            java.lang.Class r6 = r7.getClass()
            java.lang.String r7 = "getCookieManager"
            r8 = 0
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.reflect.Method r3 = r6.getMethod(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Class r5 = r3.getReturnType()     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.String r7 = "getCookie"
            r8 = 1
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r9 = 0
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            r8[r9] = r10     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.reflect.Method r2 = r5.getMethod(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            org.apache.cordova.CordovaWebView r7 = r11.webView     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r3.invoke(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r5.cast(r7)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r9 = 0
            r8[r9] = r12     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r2.invoke(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r1 = r0
            r4 = 1
        L_0x003f:
            if (r4 != 0) goto L_0x004f
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()
            if (r7 == 0) goto L_0x004f
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()
            java.lang.String r1 = r7.getCookie(r12)
        L_0x004f:
            return r1
        L_0x0050:
            r7 = move-exception
            goto L_0x003f
        L_0x0052:
            r7 = move-exception
            goto L_0x003f
        L_0x0054:
            r7 = move-exception
            goto L_0x003f
        L_0x0056:
            r7 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.getCookies(java.lang.String):java.lang.String");
    }

    private void upload(String source, String target, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final JSONObject headers;
        Log.d(LOG_TAG, "upload " + source + " to " + target);
        final String fileKey = getArgument(args, 2, "file");
        final String fileName = getArgument(args, 3, "image.jpg");
        final String mimeType = getArgument(args, 4, "image/jpeg");
        final JSONObject params = args.optJSONObject(5) == null ? new JSONObject() : args.optJSONObject(5);
        final boolean trustEveryone = args.optBoolean(6);
        final boolean chunkedMode = args.optBoolean(7) || args.isNull(7);
        if (args.optJSONObject(8) == null) {
            headers = params.optJSONObject("headers");
        } else {
            headers = args.optJSONObject(8);
        }
        final String objectId = args.getString(9);
        final String httpMethod = getArgument(args, 10, "POST");
        final CordovaResourceApi resourceApi = this.webView.getResourceApi();
        Log.d(LOG_TAG, "fileKey: " + fileKey);
        Log.d(LOG_TAG, "fileName: " + fileName);
        Log.d(LOG_TAG, "mimeType: " + mimeType);
        Log.d(LOG_TAG, "params: " + params);
        Log.d(LOG_TAG, "trustEveryone: " + trustEveryone);
        Log.d(LOG_TAG, "chunkedMode: " + chunkedMode);
        Log.d(LOG_TAG, "headers: " + headers);
        Log.d(LOG_TAG, "objectId: " + objectId);
        Log.d(LOG_TAG, "httpMethod: " + httpMethod);
        Uri targetUri = resourceApi.remapUri(Uri.parse(target));
        Uri tmpSrc = Uri.parse(source);
        if (tmpSrc.getScheme() == null) {
            tmpSrc = Uri.fromFile(new File(source));
        }
        final Uri sourceUri = resourceApi.remapUri(tmpSrc);
        int uriType = CordovaResourceApi.getUriType(targetUri);
        final boolean useHttps = uriType == 6;
        if (uriType == 5 || useHttps) {
            final RequestContext context = new RequestContext(source, target, callbackContext);
            synchronized (activeRequests) {
                activeRequests.put(objectId, context);
            }
            final Uri uri = targetUri;
            final String str = target;
            final String str2 = source;
            this.f21cordova.getThreadPool().execute(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:105:0x034d, code lost:
                    if (r19 == false) goto L_0x0359;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
                    r31.write(r5);
                    r35 = 0 + r5.length;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:109:0x0359, code lost:
                    r7 = java.lang.Math.min(r27.inputStream.available(), 16384);
                    r6 = new byte[r7];
                    r9 = r27.inputStream.read(r6, 0, r7);
                    r24 = 0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:110:0x037f, code lost:
                    if (r9 <= 0) goto L_0x04e1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:111:0x0381, code lost:
                    r35 = r35 + r9;
                    r30.setBytesSent((long) r35);
                    r31.write(r6, 0, r9);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:112:0x03a4, code lost:
                    if (((long) r35) <= (102400 + r24)) goto L_0x03d9;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:113:0x03a6, code lost:
                    r24 = (long) r35;
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Uploaded " + r35 + " of " + r14 + " bytes");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:114:0x03d9, code lost:
                    r9 = r27.inputStream.read(r6, 0, java.lang.Math.min(r27.inputStream.available(), 16384));
                    r23.setLoaded((long) r35);
                    r0 = new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.OK, r23.toJSONObject());
                    r0.setKeepCallback(true);
                    r5.sendPluginResult(r0);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:115:0x0430, code lost:
                    r37 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r27.inputStream);
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r31);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:119:0x043d, code lost:
                    throw r37;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:120:0x043e, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$600(org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR, r19, r12, r10, r12);
                    android.util.Log.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), r12);
                    android.util.Log.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Failed after uploading " + r35 + " of " + r14 + " bytes.");
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:124:0x04aa, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$700());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:128:0x04bd, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:133:0x04cf, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:139:0x04e1, code lost:
                    if (r19 == false) goto L_0x04f1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:140:0x04e3, code lost:
                    r31.write(r34);
                    r35 = r35 + r34.length;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:141:0x04f1, code lost:
                    r31.flush();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:144:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r27.inputStream);
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r31);
                    r38 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:145:0x0506, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:149:0x0515, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Sent " + r35 + " of " + r14);
                    r28 = r10.getResponseCode();
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "response code: " + r28);
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "response headers: " + r10.getHeaderFields());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:153:0x057a, code lost:
                    r16 = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:155:?, code lost:
                    r16 = org.apache.cordova.filetransfer.FileTransfer.access$500(r10);
                    r38 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:156:0x0586, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:160:0x0593, code lost:
                    if (r5.aborted == false) goto L_0x06bc;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:161:0x0595, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:164:?, code lost:
                    r38 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:165:0x059c, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:168:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:169:0x05ab, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:171:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r16);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:172:0x05af, code lost:
                    r38 = org.apache.cordova.filetransfer.FileTransfer.access$700();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:173:0x05b3, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:176:0x05c5, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:177:0x05c6, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:179:0x05ce, code lost:
                    if (r9 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:181:0x05d6, code lost:
                    if (r8 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:182:0x05d8, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:188:0x05ea, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:191:?, code lost:
                    android.util.Log.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r12.getMessage(), r12);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.JSON_EXCEPTION));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:193:0x060c, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$700());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:195:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:197:0x061f, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:202:0x0631, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:208:0x0643, code lost:
                    r33 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:210:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$600(org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR, r19, r12, r10, r33);
                    android.util.Log.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), r33);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:212:0x0685, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$700());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:214:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:216:0x0698, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:221:0x06aa, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:228:?, code lost:
                    r5.connection = r10;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:229:0x06c6, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:231:?, code lost:
                    r0 = new java.io.ByteArrayOutputStream(java.lang.Math.max(1024, r10.getContentLength()));
                    r6 = new byte[1024];
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:232:0x06e1, code lost:
                    r9 = r16.read(r6);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:233:0x06e7, code lost:
                    if (r9 <= 0) goto L_0x0745;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:234:0x06e9, code lost:
                    r0.write(r6, 0, r9);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:235:0x06f3, code lost:
                    r37 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:239:0x06fa, code lost:
                    monitor-enter(r5);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:242:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:245:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r16);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:246:0x070d, code lost:
                    throw r37;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:247:0x070e, code lost:
                    r37 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:249:0x0713, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$700());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:251:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:258:0x0734, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:259:0x0741, code lost:
                    throw r37;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:265:0x0745, code lost:
                    r29 = r0.toString("UTF-8");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:268:?, code lost:
                    r38 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:269:0x0755, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:272:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:273:0x0764, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:275:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r16);
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "got response from server");
                    android.util.Log.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r29.substring(0, java.lang.Math.min(256, r29.length())));
                    r30.setResponseCode(r28);
                    r30.setResponse(r29);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.OK, r30.toJSONObject()));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:276:0x07ac, code lost:
                    r38 = org.apache.cordova.filetransfer.FileTransfer.access$700();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:277:0x07b0, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:279:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:280:0x07c2, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:281:0x07c3, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:283:0x07cb, code lost:
                    if (r9 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:285:0x07d3, code lost:
                    if (r8 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:286:0x07d5, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:368:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:369:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:370:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:374:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:375:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:378:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:379:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:380:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:381:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:382:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:383:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:386:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:387:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:390:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:391:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:392:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:393:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:394:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:395:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r27.inputStream);
                    org.apache.cordova.filetransfer.FileTransfer.access$400(r31);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:67:0x0285, code lost:
                    r38 = org.apache.cordova.filetransfer.FileTransfer.access$700();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:68:0x0289, code lost:
                    monitor-enter(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:71:0x029b, code lost:
                    monitor-exit(r38);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:72:0x029c, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:74:0x02a4, code lost:
                    if (r9 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:76:0x02ac, code lost:
                    if (r8 == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:77:0x02ae, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:83:0x02cd, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$600(org.apache.cordova.filetransfer.FileTransfer.FILE_NOT_FOUND_ERR, r19, r12, r10, r12);
                    android.util.Log.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), r12);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:87:0x030b, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$700());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$700().remove(r20);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:91:0x031e, code lost:
                    if (r10 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:96:0x0330, code lost:
                    r15 = (javax.net.ssl.HttpsURLConnection) r10;
                    r15.setHostnameVerifier(r20);
                    r15.setSSLSocketFactory(r21);
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:120:0x043e A[ExcHandler: IOException (r12v1 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r10 r14 r20 r21 r35 
                  PHI: (r10v4 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r14v1 'fixedLength' int) = (r14v0 'fixedLength' int), (r14v0 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v0 'fixedLength' int) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r20v4 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) = (r20v0 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r21v4 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) = (r21v0 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r35v1 'totalBytes' int) = (r35v0 'totalBytes' int), (r35v0 'totalBytes' int), (r35v0 'totalBytes' int), (r35v0 'totalBytes' int), (r35v0 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v4 'totalBytes' int), (r35v0 'totalBytes' int), (r35v0 'totalBytes' int), (r35v0 'totalBytes' int) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x0017] */
                /* JADX WARNING: Removed duplicated region for block: B:208:0x0643 A[ExcHandler: Throwable (r33v0 't' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r10 r20 r21 
                  PHI: (r10v2 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r20v2 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) = (r20v0 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r21v2 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) = (r21v0 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x0017] */
                /* JADX WARNING: Removed duplicated region for block: B:83:0x02cd A[ExcHandler: FileNotFoundException (r12v2 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), PHI: r10 r20 r21 
                  PHI: (r10v5 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection), (r10v6 'conn' java.net.HttpURLConnection) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r20v5 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) = (r20v0 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier), (r20v6 'oldHostnameVerifier' javax.net.ssl.HostnameVerifier) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r21v5 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) = (r21v0 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory), (r21v6 'oldSocketFactory' javax.net.ssl.SSLSocketFactory) binds: [B:3:0x0017, B:24:0x00ce, B:81:0x02c9, B:82:?, B:117:0x0433, B:143:0x04f6, B:186:0x05e9, B:187:?, B:151:0x0518, B:152:?, B:237:0x06f6, B:295:0x07e9, B:296:?, B:244:0x070a, B:267:0x0751, B:290:0x07e6, B:291:?, B:274:0x0765, B:275:?, B:163:0x0598, B:206:0x0642, B:207:?, B:170:0x05ac, B:171:?, B:65:0x027b, B:66:?, B:33:0x013f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x0017] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r42 = this;
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5
                        r37 = r0
                        r0 = r37
                        boolean r0 = r0.aborted
                        r37 = r0
                        if (r37 == 0) goto L_0x000f
                    L_0x000e:
                        return
                    L_0x000f:
                        r10 = 0
                        r20 = 0
                        r21 = 0
                        r35 = 0
                        r14 = -1
                        org.apache.cordova.filetransfer.FileUploadResult r30 = new org.apache.cordova.filetransfer.FileUploadResult     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r30.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.apache.cordova.filetransfer.FileProgressResult r23 = new org.apache.cordova.filetransfer.FileProgressResult     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r23.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.CordovaResourceApi r0 = r6     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r42
                        android.net.Uri r0 = r7     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        java.net.HttpURLConnection r10 = r37.createHttpConnection(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        boolean r0 = r8     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x0056
                        r0 = r42
                        boolean r0 = r9     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x0056
                        r0 = r10
                        javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r15 = r0
                        javax.net.ssl.SSLSocketFactory r21 = org.apache.cordova.filetransfer.FileTransfer.trustAllHosts(r15)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        javax.net.ssl.HostnameVerifier r20 = r15.getHostnameVerifier()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        javax.net.ssl.HostnameVerifier r37 = org.apache.cordova.filetransfer.FileTransfer.DO_NOT_VERIFY     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r37
                        r15.setHostnameVerifier(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x0056:
                        r37 = 1
                        r0 = r37
                        r10.setDoInput(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = 1
                        r0 = r37
                        r10.setDoOutput(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = 0
                        r0 = r37
                        r10.setUseCaches(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        java.lang.String r0 = r10     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r37
                        r10.setRequestMethod(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.json.JSONObject r0 = r11     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x008c
                        r0 = r42
                        org.json.JSONObject r0 = r11     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        java.lang.String r38 = "Content-Type"
                        boolean r37 = r37.has(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        if (r37 != 0) goto L_0x02bd
                    L_0x008c:
                        r19 = 1
                    L_0x008e:
                        if (r19 == 0) goto L_0x009b
                        java.lang.String r37 = "Content-Type"
                        java.lang.String r38 = "multipart/form-data; boundary=+++++"
                        r0 = r37
                        r1 = r38
                        r10.setRequestProperty(r0, r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x009b:
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer r0 = org.apache.cordova.filetransfer.FileTransfer.this     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r42
                        java.lang.String r0 = r12     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        java.lang.String r11 = r37.getCookies(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        if (r11 == 0) goto L_0x00b4
                        java.lang.String r37 = "Cookie"
                        r0 = r37
                        r10.setRequestProperty(r0, r11)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x00b4:
                        r0 = r42
                        org.json.JSONObject r0 = r11     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x00c7
                        r0 = r42
                        org.json.JSONObject r0 = r11     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r37
                        org.apache.cordova.filetransfer.FileTransfer.addHeadersToRequest(r10, r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x00c7:
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r4.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.json.JSONObject r0 = r13     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        r37 = r0
                        java.util.Iterator r17 = r37.keys()     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                    L_0x00d6:
                        boolean r37 = r17.hasNext()     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        if (r37 == 0) goto L_0x014a
                        java.lang.Object r18 = r17.next()     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r37 = java.lang.String.valueOf(r18)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r38 = "headers"
                        boolean r37 = r37.equals(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        if (r37 != 0) goto L_0x00d6
                        java.lang.String r37 = "--"
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r38 = "+++++"
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        r37.append(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r37 = "Content-Disposition: form-data; name=\""
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r38 = r18.toString()     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        r38 = 34
                        r37.append(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r37 = "\r\n"
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        r37.append(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        r0 = r42
                        org.json.JSONObject r0 = r13     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        r37 = r0
                        java.lang.String r38 = r18.toString()     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r37 = r37.getString(r38)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        r0 = r37
                        r4.append(r0)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        java.lang.String r37 = "\r\n"
                        r0 = r37
                        r4.append(r0)     // Catch:{ JSONException -> 0x013c, FileNotFoundException -> 0x02cd, IOException -> 0x043e, Throwable -> 0x0643 }
                        goto L_0x00d6
                    L_0x013c:
                        r12 = move-exception
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = r12.getMessage()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r37
                        r1 = r38
                        android.util.Log.e(r0, r1, r12)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x014a:
                        java.lang.String r37 = "--"
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "+++++"
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "Content-Disposition: form-data; name=\""
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        java.lang.String r0 = r14     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "\";"
                        r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = " filename=\""
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        java.lang.String r0 = r15     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = 34
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "Content-Type: "
                        r0 = r37
                        java.lang.StringBuilder r37 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        java.lang.String r0 = r16     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "\r\n"
                        r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = r4.toString()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = "UTF-8"
                        byte[] r5 = r37.getBytes(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "\r\n--+++++--\r\n"
                        java.lang.String r38 = "UTF-8"
                        byte[] r34 = r37.getBytes(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.CordovaResourceApi r0 = r6     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r42
                        android.net.Uri r0 = r17     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        org.apache.cordova.CordovaResourceApi$OpenForReadResult r27 = r37.openForRead(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        int r0 = r5.length     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        r0 = r34
                        int r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        int r32 = r37 + r38
                        r0 = r27
                        long r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        r40 = 0
                        int r37 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
                        if (r37 < 0) goto L_0x0206
                        r0 = r27
                        long r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        r0 = r38
                        int r14 = (int) r0     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        if (r19 == 0) goto L_0x01f3
                        int r14 = r14 + r32
                    L_0x01f3:
                        r37 = 1
                        r0 = r23
                        r1 = r37
                        r0.setLengthComputable(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        long r0 = (long) r14     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        r0 = r23
                        r1 = r38
                        r0.setTotal(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x0206:
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r39 = "Content Length: "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r38
                        java.lang.StringBuilder r38 = r0.append(r14)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = r38.toString()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        boolean r0 = r18     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x02c1
                        int r37 = android.os.Build.VERSION.SDK_INT     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = 8
                        r0 = r37
                        r1 = r38
                        if (r0 < r1) goto L_0x023a
                        r0 = r42
                        boolean r0 = r8     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        if (r37 == 0) goto L_0x02c1
                    L_0x023a:
                        r36 = 1
                    L_0x023c:
                        if (r36 != 0) goto L_0x0244
                        r37 = -1
                        r0 = r37
                        if (r14 != r0) goto L_0x02c5
                    L_0x0244:
                        r36 = 1
                    L_0x0246:
                        if (r36 == 0) goto L_0x02c9
                        r37 = 16384(0x4000, float:2.2959E-41)
                        r0 = r37
                        r10.setChunkedStreamingMode(r0)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "Transfer-Encoding"
                        java.lang.String r38 = "chunked"
                        r0 = r37
                        r1 = r38
                        r10.setRequestProperty(r0, r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x025a:
                        r10.connect()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r31 = 0
                        java.io.OutputStream r31 = r10.getOutputStream()     // Catch:{ all -> 0x0430 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0430 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ all -> 0x0430 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x04de }
                        r37 = r0
                        r0 = r37
                        boolean r0 = r0.aborted     // Catch:{ all -> 0x04de }
                        r37 = r0
                        if (r37 == 0) goto L_0x0342
                        monitor-exit(r38)     // Catch:{ all -> 0x04de }
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r37)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r31)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x033f }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x033f }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x033f }
                        monitor-exit(r38)     // Catch:{ all -> 0x033f }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x02bd:
                        r19 = 0
                        goto L_0x008e
                    L_0x02c1:
                        r36 = 0
                        goto L_0x023c
                    L_0x02c5:
                        r36 = 0
                        goto L_0x0246
                    L_0x02c9:
                        r10.setFixedLengthStreamingMode(r14)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        goto L_0x025a
                    L_0x02cd:
                        r12 = move-exception
                        int r37 = org.apache.cordova.filetransfer.FileTransfer.FILE_NOT_FOUND_ERR     // Catch:{ all -> 0x070e }
                        r0 = r42
                        java.lang.String r0 = r19     // Catch:{ all -> 0x070e }
                        r38 = r0
                        r0 = r42
                        java.lang.String r0 = r12     // Catch:{ all -> 0x070e }
                        r39 = r0
                        r0 = r37
                        r1 = r38
                        r2 = r39
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r12)     // Catch:{ all -> 0x070e }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = r13.toString()     // Catch:{ all -> 0x070e }
                        r0 = r37
                        r1 = r38
                        android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x070e }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x070e }
                        r37 = r0
                        org.apache.cordova.PluginResult r38 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x070e }
                        org.apache.cordova.PluginResult$Status r39 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x070e }
                        r0 = r38
                        r1 = r39
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x070e }
                        r37.sendPluginResult(r38)     // Catch:{ all -> 0x070e }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07ed }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07ed }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x07ed }
                        monitor-exit(r38)     // Catch:{ all -> 0x07ed }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x033f:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x033f }
                        throw r37
                    L_0x0342:
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x04de }
                        r37 = r0
                        r0 = r37
                        r0.connection = r10     // Catch:{ all -> 0x04de }
                        monitor-exit(r38)     // Catch:{ all -> 0x04de }
                        if (r19 == 0) goto L_0x0359
                        r0 = r31
                        r0.write(r5)     // Catch:{ all -> 0x0430 }
                        int r0 = r5.length     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        int r35 = r35 + r37
                    L_0x0359:
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        int r8 = r37.available()     // Catch:{ all -> 0x0430 }
                        r37 = 16384(0x4000, float:2.2959E-41)
                        r0 = r37
                        int r7 = java.lang.Math.min(r8, r0)     // Catch:{ all -> 0x0430 }
                        byte[] r6 = new byte[r7]     // Catch:{ all -> 0x0430 }
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        r38 = 0
                        r0 = r37
                        r1 = r38
                        int r9 = r0.read(r6, r1, r7)     // Catch:{ all -> 0x0430 }
                        r24 = 0
                    L_0x037f:
                        if (r9 <= 0) goto L_0x04e1
                        int r35 = r35 + r9
                        r0 = r35
                        long r0 = (long) r0     // Catch:{ all -> 0x0430 }
                        r38 = r0
                        r0 = r30
                        r1 = r38
                        r0.setBytesSent(r1)     // Catch:{ all -> 0x0430 }
                        r37 = 0
                        r0 = r31
                        r1 = r37
                        r0.write(r6, r1, r9)     // Catch:{ all -> 0x0430 }
                        r0 = r35
                        long r0 = (long) r0     // Catch:{ all -> 0x0430 }
                        r38 = r0
                        r40 = 102400(0x19000, double:5.05923E-319)
                        long r40 = r40 + r24
                        int r37 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
                        if (r37 <= 0) goto L_0x03d9
                        r0 = r35
                        long r0 = (long) r0     // Catch:{ all -> 0x0430 }
                        r24 = r0
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ all -> 0x0430 }
                        r38.<init>()     // Catch:{ all -> 0x0430 }
                        java.lang.String r39 = "Uploaded "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x0430 }
                        r0 = r38
                        r1 = r35
                        java.lang.StringBuilder r38 = r0.append(r1)     // Catch:{ all -> 0x0430 }
                        java.lang.String r39 = " of "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x0430 }
                        r0 = r38
                        java.lang.StringBuilder r38 = r0.append(r14)     // Catch:{ all -> 0x0430 }
                        java.lang.String r39 = " bytes"
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x0430 }
                        java.lang.String r38 = r38.toString()     // Catch:{ all -> 0x0430 }
                        android.util.Log.d(r37, r38)     // Catch:{ all -> 0x0430 }
                    L_0x03d9:
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        int r8 = r37.available()     // Catch:{ all -> 0x0430 }
                        r37 = 16384(0x4000, float:2.2959E-41)
                        r0 = r37
                        int r7 = java.lang.Math.min(r8, r0)     // Catch:{ all -> 0x0430 }
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        r38 = 0
                        r0 = r37
                        r1 = r38
                        int r9 = r0.read(r6, r1, r7)     // Catch:{ all -> 0x0430 }
                        r0 = r35
                        long r0 = (long) r0     // Catch:{ all -> 0x0430 }
                        r38 = r0
                        r0 = r23
                        r1 = r38
                        r0.setLoaded(r1)     // Catch:{ all -> 0x0430 }
                        org.apache.cordova.PluginResult r26 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x0430 }
                        org.apache.cordova.PluginResult$Status r37 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ all -> 0x0430 }
                        org.json.JSONObject r38 = r23.toJSONObject()     // Catch:{ all -> 0x0430 }
                        r0 = r26
                        r1 = r37
                        r2 = r38
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r2)     // Catch:{ all -> 0x0430 }
                        r37 = 1
                        r0 = r26
                        r1 = r37
                        r0.setKeepCallback(r1)     // Catch:{ all -> 0x0430 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        r0 = r37
                        r1 = r26
                        r0.sendPluginResult(r1)     // Catch:{ all -> 0x0430 }
                        goto L_0x037f
                    L_0x0430:
                        r37 = move-exception
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r31)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x043e:
                        r12 = move-exception
                        int r37 = org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR     // Catch:{ all -> 0x070e }
                        r0 = r42
                        java.lang.String r0 = r19     // Catch:{ all -> 0x070e }
                        r38 = r0
                        r0 = r42
                        java.lang.String r0 = r12     // Catch:{ all -> 0x070e }
                        r39 = r0
                        r0 = r37
                        r1 = r38
                        r2 = r39
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r12)     // Catch:{ all -> 0x070e }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = r13.toString()     // Catch:{ all -> 0x070e }
                        r0 = r37
                        r1 = r38
                        android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x070e }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ all -> 0x070e }
                        r38.<init>()     // Catch:{ all -> 0x070e }
                        java.lang.String r39 = "Failed after uploading "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x070e }
                        r0 = r38
                        r1 = r35
                        java.lang.StringBuilder r38 = r0.append(r1)     // Catch:{ all -> 0x070e }
                        java.lang.String r39 = " of "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x070e }
                        r0 = r38
                        java.lang.StringBuilder r38 = r0.append(r14)     // Catch:{ all -> 0x070e }
                        java.lang.String r39 = " bytes."
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ all -> 0x070e }
                        java.lang.String r38 = r38.toString()     // Catch:{ all -> 0x070e }
                        android.util.Log.e(r37, r38)     // Catch:{ all -> 0x070e }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x070e }
                        r37 = r0
                        org.apache.cordova.PluginResult r38 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x070e }
                        org.apache.cordova.PluginResult$Status r39 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x070e }
                        r0 = r38
                        r1 = r39
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x070e }
                        r37.sendPluginResult(r38)     // Catch:{ all -> 0x070e }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07f0 }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07f0 }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x07f0 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07f0 }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x04de:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x04de }
                        throw r37     // Catch:{ all -> 0x0430 }
                    L_0x04e1:
                        if (r19 == 0) goto L_0x04f1
                        r0 = r31
                        r1 = r34
                        r0.write(r1)     // Catch:{ all -> 0x0430 }
                        r0 = r34
                        int r0 = r0.length     // Catch:{ all -> 0x0430 }
                        r37 = r0
                        int r35 = r35 + r37
                    L_0x04f1:
                        r31.flush()     // Catch:{ all -> 0x0430 }
                        r0 = r27
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r37)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r31)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x05e7 }
                        r37 = r0
                        r39 = 0
                        r0 = r39
                        r1 = r37
                        r1.connection = r0     // Catch:{ all -> 0x05e7 }
                        monitor-exit(r38)     // Catch:{ all -> 0x05e7 }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r39 = "Sent "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r38
                        r1 = r35
                        java.lang.StringBuilder r38 = r0.append(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r39 = " of "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r38
                        java.lang.StringBuilder r38 = r0.append(r14)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = r38.toString()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        int r28 = r10.getResponseCode()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r39 = "response code: "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r38
                        r1 = r28
                        java.lang.StringBuilder r38 = r0.append(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = r38.toString()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.StringBuilder r38 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38.<init>()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r39 = "response headers: "
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.util.Map r39 = r10.getHeaderFields()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.StringBuilder r38 = r38.append(r39)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r38 = r38.toString()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r16 = 0
                        org.apache.cordova.filetransfer.FileTransfer$TrackingInputStream r16 = org.apache.cordova.filetransfer.FileTransfer.getInputStream(r10)     // Catch:{ all -> 0x06f3 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x06f3 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ all -> 0x06f3 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0742 }
                        r37 = r0
                        r0 = r37
                        boolean r0 = r0.aborted     // Catch:{ all -> 0x0742 }
                        r37 = r0
                        if (r37 == 0) goto L_0x06bc
                        monitor-exit(r38)     // Catch:{ all -> 0x0742 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0640 }
                        r37 = r0
                        r39 = 0
                        r0 = r39
                        r1 = r37
                        r1.connection = r0     // Catch:{ all -> 0x0640 }
                        monitor-exit(r38)     // Catch:{ all -> 0x0640 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r16)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x06b9 }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x06b9 }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x06b9 }
                        monitor-exit(r38)     // Catch:{ all -> 0x06b9 }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x05e7:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x05e7 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x05ea:
                        r12 = move-exception
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = r12.getMessage()     // Catch:{ all -> 0x070e }
                        r0 = r37
                        r1 = r38
                        android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x070e }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x070e }
                        r37 = r0
                        org.apache.cordova.PluginResult r38 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x070e }
                        org.apache.cordova.PluginResult$Status r39 = org.apache.cordova.PluginResult.Status.JSON_EXCEPTION     // Catch:{ all -> 0x070e }
                        r38.<init>(r39)     // Catch:{ all -> 0x070e }
                        r37.sendPluginResult(r38)     // Catch:{ all -> 0x070e }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07f3 }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07f3 }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x07f3 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07f3 }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x0640:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x0640 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x0643:
                        r33 = move-exception
                        int r37 = org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR     // Catch:{ all -> 0x070e }
                        r0 = r42
                        java.lang.String r0 = r19     // Catch:{ all -> 0x070e }
                        r38 = r0
                        r0 = r42
                        java.lang.String r0 = r12     // Catch:{ all -> 0x070e }
                        r39 = r0
                        r0 = r37
                        r1 = r38
                        r2 = r39
                        r3 = r33
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r3)     // Catch:{ all -> 0x070e }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = r13.toString()     // Catch:{ all -> 0x070e }
                        r0 = r37
                        r1 = r38
                        r2 = r33
                        android.util.Log.e(r0, r1, r2)     // Catch:{ all -> 0x070e }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x070e }
                        r37 = r0
                        org.apache.cordova.PluginResult r38 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x070e }
                        org.apache.cordova.PluginResult$Status r39 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x070e }
                        r0 = r38
                        r1 = r39
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x070e }
                        r37.sendPluginResult(r38)     // Catch:{ all -> 0x070e }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07f6 }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07f6 }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x07f6 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07f6 }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x06b9:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x06b9 }
                        throw r37
                    L_0x06bc:
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0742 }
                        r37 = r0
                        r0 = r37
                        r0.connection = r10     // Catch:{ all -> 0x0742 }
                        monitor-exit(r38)     // Catch:{ all -> 0x0742 }
                        java.io.ByteArrayOutputStream r22 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x06f3 }
                        r37 = 1024(0x400, float:1.435E-42)
                        int r38 = r10.getContentLength()     // Catch:{ all -> 0x06f3 }
                        int r37 = java.lang.Math.max(r37, r38)     // Catch:{ all -> 0x06f3 }
                        r0 = r22
                        r1 = r37
                        r0.<init>(r1)     // Catch:{ all -> 0x06f3 }
                        r37 = 1024(0x400, float:1.435E-42)
                        r0 = r37
                        byte[] r6 = new byte[r0]     // Catch:{ all -> 0x06f3 }
                        r9 = 0
                    L_0x06e1:
                        r0 = r16
                        int r9 = r0.read(r6)     // Catch:{ all -> 0x06f3 }
                        if (r9 <= 0) goto L_0x0745
                        r37 = 0
                        r0 = r22
                        r1 = r37
                        r0.write(r6, r1, r9)     // Catch:{ all -> 0x06f3 }
                        goto L_0x06e1
                    L_0x06f3:
                        r37 = move-exception
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x07e7 }
                        r39 = r0
                        r40 = 0
                        r0 = r40
                        r1 = r39
                        r1.connection = r0     // Catch:{ all -> 0x07e7 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07e7 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r16)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x070e:
                        r37 = move-exception
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r39 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07f9 }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07f9 }
                        r40 = r0
                        r39.remove(r40)     // Catch:{ all -> 0x07f9 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07f9 }
                        if (r10 == 0) goto L_0x0741
                        r0 = r42
                        boolean r0 = r9
                        r38 = r0
                        if (r38 == 0) goto L_0x0741
                        r0 = r42
                        boolean r0 = r8
                        r38 = r0
                        if (r38 == 0) goto L_0x0741
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                    L_0x0741:
                        throw r37
                    L_0x0742:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x0742 }
                        throw r37     // Catch:{ all -> 0x06f3 }
                    L_0x0745:
                        java.lang.String r37 = "UTF-8"
                        r0 = r22
                        r1 = r37
                        java.lang.String r29 = r0.toString(r1)     // Catch:{ all -> 0x06f3 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38 = r0
                        monitor-enter(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x07e4 }
                        r37 = r0
                        r39 = 0
                        r0 = r39
                        r1 = r37
                        r1.connection = r0     // Catch:{ all -> 0x07e4 }
                        monitor-exit(r38)     // Catch:{ all -> 0x07e4 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r16)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "FileTransfer"
                        java.lang.String r38 = "got response from server"
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.lang.String r37 = "FileTransfer"
                        r38 = 0
                        r39 = 256(0x100, float:3.59E-43)
                        int r40 = r29.length()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        int r39 = java.lang.Math.min(r39, r40)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r29
                        r1 = r38
                        r2 = r39
                        java.lang.String r38 = r0.substring(r1, r2)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        android.util.Log.d(r37, r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r30
                        r1 = r28
                        r0.setResponseCode(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r30
                        r1 = r29
                        r0.setResponse(r1)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r0 = r42
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37 = r0
                        org.apache.cordova.PluginResult r38 = new org.apache.cordova.PluginResult     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.apache.cordova.PluginResult$Status r39 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        org.json.JSONObject r40 = r30.toJSONObject()     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r38.<init>((org.apache.cordova.PluginResult.Status) r39, (org.json.JSONObject) r40)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        r37.sendPluginResult(r38)     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r38)
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x07ea }
                        r0 = r42
                        java.lang.String r0 = r20     // Catch:{ all -> 0x07ea }
                        r39 = r0
                        r0 = r37
                        r1 = r39
                        r0.remove(r1)     // Catch:{ all -> 0x07ea }
                        monitor-exit(r38)     // Catch:{ all -> 0x07ea }
                        if (r10 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r9
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r0 = r42
                        boolean r0 = r8
                        r37 = r0
                        if (r37 == 0) goto L_0x000e
                        r15 = r10
                        javax.net.ssl.HttpsURLConnection r15 = (javax.net.ssl.HttpsURLConnection) r15
                        r0 = r20
                        r15.setHostnameVerifier(r0)
                        r0 = r21
                        r15.setSSLSocketFactory(r0)
                        goto L_0x000e
                    L_0x07e4:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07e4 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x07e7:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07e7 }
                        throw r37     // Catch:{ FileNotFoundException -> 0x02cd, IOException -> 0x043e, JSONException -> 0x05ea, Throwable -> 0x0643 }
                    L_0x07ea:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07ea }
                        throw r37
                    L_0x07ed:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07ed }
                        throw r37
                    L_0x07f0:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07f0 }
                        throw r37
                    L_0x07f3:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07f3 }
                        throw r37
                    L_0x07f6:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07f6 }
                        throw r37
                    L_0x07f9:
                        r37 = move-exception
                        monitor-exit(r38)     // Catch:{ all -> 0x07f9 }
                        throw r37
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.C07311.run():void");
                }
            });
            return;
        }
        JSONObject error = createFileTransferError(INVALID_URL_ERR, source, target, (String) null, 0, (Throwable) null);
        Log.e(LOG_TAG, "Unsupported URI: " + targetUri);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, error));
    }

    /* access modifiers changed from: private */
    public static void safeClose(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static TrackingInputStream getInputStream(URLConnection conn) throws IOException {
        String encoding = conn.getContentEncoding();
        if (encoding == null || !encoding.equalsIgnoreCase("gzip")) {
            return new SimpleTrackingInputStream(conn.getInputStream());
        }
        return new TrackingGZIPInputStream(new ExposedGZIPInputStream(conn.getInputStream()));
    }

    /* access modifiers changed from: private */
    public static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init((KeyManager[]) null, trustAllCerts, new SecureRandom());
            connection.setSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
        return oldFactory;
    }

    /* access modifiers changed from: private */
    public static JSONObject createFileTransferError(int errorCode, String source, String target, URLConnection connection, Throwable throwable) {
        BufferedReader reader;
        int httpStatus = 0;
        StringBuilder bodyBuilder = new StringBuilder();
        String body = null;
        if (connection != null) {
            try {
                if (connection instanceof HttpURLConnection) {
                    httpStatus = ((HttpURLConnection) connection).getResponseCode();
                    InputStream err = ((HttpURLConnection) connection).getErrorStream();
                    if (err != null) {
                        reader = new BufferedReader(new InputStreamReader(err, "UTF-8"));
                        String line = reader.readLine();
                        while (line != null) {
                            bodyBuilder.append(line);
                            line = reader.readLine();
                            if (line != null) {
                                bodyBuilder.append(10);
                            }
                        }
                        body = bodyBuilder.toString();
                        reader.close();
                    }
                }
            } catch (Throwable e) {
                Log.w(LOG_TAG, "Error getting HTTP status code from connection.", e);
            }
        }
        return createFileTransferError(errorCode, source, target, body, Integer.valueOf(httpStatus), throwable);
    }

    /* access modifiers changed from: private */
    public static JSONObject createFileTransferError(int errorCode, String source, String target, String body, Integer httpStatus, Throwable throwable) {
        JSONObject error = null;
        try {
            JSONObject error2 = new JSONObject();
            try {
                error2.put("code", errorCode);
                error2.put("source", source);
                error2.put("target", target);
                if (body != null) {
                    error2.put("body", body);
                }
                if (httpStatus != null) {
                    error2.put("http_status", httpStatus);
                }
                if (throwable != null) {
                    String msg = throwable.getMessage();
                    if (msg == null || "".equals(msg)) {
                        msg = throwable.toString();
                    }
                    error2.put("exception", msg);
                }
                return error2;
            } catch (JSONException e) {
                e = e;
                error = error2;
                Log.e(LOG_TAG, e.getMessage(), e);
                return error;
            }
        } catch (JSONException e2) {
            e = e2;
            Log.e(LOG_TAG, e.getMessage(), e);
            return error;
        }
    }

    private static String getArgument(JSONArray args, int position, String defaultString) {
        String arg = defaultString;
        if (args.length() <= position) {
            return arg;
        }
        String arg2 = args.optString(position);
        if (arg2 == null || "null".equals(arg2)) {
            return defaultString;
        }
        return arg2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void download(java.lang.String r28, java.lang.String r29, org.json.JSONArray r30, org.apache.cordova.CallbackContext r31) throws org.json.JSONException {
        /*
            r27 = this;
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "download "
            java.lang.StringBuilder r4 = r4.append(r8)
            r0 = r28
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r8 = " to "
            java.lang.StringBuilder r4 = r4.append(r8)
            r0 = r29
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            r0 = r27
            org.apache.cordova.CordovaWebView r3 = r0.webView
            org.apache.cordova.CordovaResourceApi r6 = r3.getResourceApi()
            r3 = 2
            r0 = r30
            boolean r11 = r0.optBoolean(r3)
            r3 = 3
            r0 = r30
            java.lang.String r15 = r0.getString(r3)
            r3 = 4
            r0 = r30
            org.json.JSONObject r12 = r0.optJSONObject(r3)
            android.net.Uri r3 = android.net.Uri.parse(r28)
            android.net.Uri r22 = r6.remapUri(r3)
            android.net.Uri r23 = android.net.Uri.parse(r29)
            java.lang.String r3 = r23.getScheme()
            if (r3 == 0) goto L_0x00ac
        L_0x0055:
            r0 = r23
            android.net.Uri r7 = r6.remapUri(r0)
            int r24 = org.apache.cordova.CordovaResourceApi.getUriType(r22)
            r3 = 6
            r0 = r24
            if (r0 != r3) goto L_0x00b8
            r10 = 1
        L_0x0065:
            if (r10 != 0) goto L_0x00ba
            r3 = 5
            r0 = r24
            if (r0 == r3) goto L_0x00ba
            r9 = 1
        L_0x006d:
            r3 = -1
            r0 = r24
            if (r0 != r3) goto L_0x00bc
            int r3 = INVALID_URL_ERR
            r6 = 0
            r4 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r8 = 0
            r4 = r28
            r5 = r29
            org.json.JSONObject r16 = createFileTransferError(r3, r4, r5, r6, r7, r8)
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "Unsupported URI: "
            java.lang.StringBuilder r4 = r4.append(r8)
            r0 = r22
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r4 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION
            r0 = r16
            r3.<init>((org.apache.cordova.PluginResult.Status) r4, (org.json.JSONObject) r0)
            r0 = r31
            r0.sendPluginResult(r3)
        L_0x00ab:
            return
        L_0x00ac:
            java.io.File r3 = new java.io.File
            r0 = r29
            r3.<init>(r0)
            android.net.Uri r23 = android.net.Uri.fromFile(r3)
            goto L_0x0055
        L_0x00b8:
            r10 = 0
            goto L_0x0065
        L_0x00ba:
            r9 = 0
            goto L_0x006d
        L_0x00bc:
            r21 = 0
            if (r9 == 0) goto L_0x00c5
            r3 = 1
            java.lang.Boolean r21 = java.lang.Boolean.valueOf(r3)
        L_0x00c5:
            if (r21 != 0) goto L_0x00f3
            r0 = r27
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            java.lang.Class r3 = r3.getClass()     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            java.lang.String r4 = "getWhitelist"
            r8 = 0
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            java.lang.reflect.Method r18 = r3.getMethod(r4, r8)     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            r0 = r27
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            r0 = r18
            java.lang.Object r25 = r0.invoke(r3, r4)     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            org.apache.cordova.Whitelist r25 = (org.apache.cordova.Whitelist) r25     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            r0 = r25
            r1 = r28
            boolean r3 = r0.isUrlWhiteListed(r1)     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
            java.lang.Boolean r21 = java.lang.Boolean.valueOf(r3)     // Catch:{ NoSuchMethodException -> 0x01c7, IllegalAccessException -> 0x01c4, InvocationTargetException -> 0x01c1 }
        L_0x00f3:
            if (r21 != 0) goto L_0x013a
            r0 = r27
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.Class r3 = r3.getClass()     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.String r4 = "getPluginManager"
            r8 = 0
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.reflect.Method r17 = r3.getMethod(r4, r8)     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r0 = r27
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r0 = r17
            java.lang.Object r19 = r0.invoke(r3, r4)     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            org.apache.cordova.PluginManager r19 = (org.apache.cordova.PluginManager) r19     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.Class r3 = r19.getClass()     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.String r4 = "shouldAllowRequest"
            r8 = 1
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r13 = 0
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r8[r13] = r14     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            java.lang.reflect.Method r20 = r3.getMethod(r4, r8)     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r4 = 0
            r3[r4] = r28     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r0 = r20
            r1 = r19
            java.lang.Object r3 = r0.invoke(r1, r3)     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r0 = r3
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x01be, IllegalAccessException -> 0x01bb, InvocationTargetException -> 0x01b9 }
            r21 = r0
        L_0x013a:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r0 = r21
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0186
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "Source URL is not in white list: '"
            java.lang.StringBuilder r4 = r4.append(r8)
            r0 = r28
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r8 = "'"
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r3, r4)
            int r3 = CONNECTION_ERR
            r6 = 0
            r4 = 401(0x191, float:5.62E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r8 = 0
            r4 = r28
            r5 = r29
            org.json.JSONObject r16 = createFileTransferError(r3, r4, r5, r6, r7, r8)
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r4 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION
            r0 = r16
            r3.<init>((org.apache.cordova.PluginResult.Status) r4, (org.json.JSONObject) r0)
            r0 = r31
            r0.sendPluginResult(r3)
            goto L_0x00ab
        L_0x0186:
            org.apache.cordova.filetransfer.FileTransfer$RequestContext r5 = new org.apache.cordova.filetransfer.FileTransfer$RequestContext
            r0 = r28
            r1 = r29
            r2 = r31
            r5.<init>(r0, r1, r2)
            java.util.HashMap<java.lang.String, org.apache.cordova.filetransfer.FileTransfer$RequestContext> r4 = activeRequests
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, org.apache.cordova.filetransfer.FileTransfer$RequestContext> r3 = activeRequests     // Catch:{ all -> 0x01b6 }
            r3.put(r15, r5)     // Catch:{ all -> 0x01b6 }
            monitor-exit(r4)     // Catch:{ all -> 0x01b6 }
            r0 = r27
            org.apache.cordova.CordovaInterface r3 = r0.f21cordova
            java.util.concurrent.ExecutorService r26 = r3.getThreadPool()
            org.apache.cordova.filetransfer.FileTransfer$4 r3 = new org.apache.cordova.filetransfer.FileTransfer$4
            r4 = r27
            r8 = r22
            r13 = r28
            r14 = r29
            r3.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r0 = r26
            r0.execute(r3)
            goto L_0x00ab
        L_0x01b6:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x01b6 }
            throw r3
        L_0x01b9:
            r3 = move-exception
            goto L_0x013a
        L_0x01bb:
            r3 = move-exception
            goto L_0x013a
        L_0x01be:
            r3 = move-exception
            goto L_0x013a
        L_0x01c1:
            r3 = move-exception
            goto L_0x00f3
        L_0x01c4:
            r3 = move-exception
            goto L_0x00f3
        L_0x01c7:
            r3 = move-exception
            goto L_0x00f3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.download(java.lang.String, java.lang.String, org.json.JSONArray, org.apache.cordova.CallbackContext):void");
    }

    private void abort(String objectId) {
        final RequestContext context;
        synchronized (activeRequests) {
            context = activeRequests.remove(objectId);
        }
        if (context != null) {
            this.f21cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    synchronized (context) {
                        File file = context.targetFile;
                        if (file != null) {
                            file.delete();
                        }
                        context.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, FileTransfer.createFileTransferError(FileTransfer.ABORTED_ERR, context.source, context.target, (String) null, -1, (Throwable) null)));
                        context.aborted = true;
                        if (context.connection != null) {
                            try {
                                context.connection.disconnect();
                            } catch (Exception e) {
                                Log.e(FileTransfer.LOG_TAG, "CB-8431 Catch workaround for fatal exception", e);
                            }
                        }
                    }
                }
            });
        }
    }
}

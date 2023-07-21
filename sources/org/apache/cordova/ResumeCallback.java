package org.apache.cordova;

public class ResumeCallback extends CallbackContext {
    private final String TAG = "CordovaResumeCallback";
    private PluginManager pluginManager;
    private String serviceName;

    public ResumeCallback(String serviceName2, PluginManager pluginManager2) {
        super("resumecallback", (CordovaWebView) null);
        this.serviceName = serviceName2;
        this.pluginManager = pluginManager2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r4.put("pluginServiceName", r9.serviceName);
        r4.put("pluginStatus", org.apache.cordova.PluginResult.StatusMessages[r10.getStatus()]);
        r2.put("action", "resume");
        r2.put("pendingResult", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0082, code lost:
        org.apache.cordova.LOG.m24e("CordovaResumeCallback", "Unable to create resume object for Activity Result");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r2 = new org.json.JSONObject();
        r4 = new org.json.JSONObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendPluginResult(org.apache.cordova.PluginResult r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r6 = r9.finished     // Catch:{ all -> 0x007e }
            if (r6 == 0) goto L_0x0029
            java.lang.String r6 = "CordovaResumeCallback"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x007e }
            r7.<init>()     // Catch:{ all -> 0x007e }
            java.lang.String r8 = r9.serviceName     // Catch:{ all -> 0x007e }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x007e }
            java.lang.String r8 = " attempted to send a second callback to ResumeCallback\nResult was: "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x007e }
            java.lang.String r8 = r10.getMessage()     // Catch:{ all -> 0x007e }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x007e }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x007e }
            org.apache.cordova.LOG.m33w((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x007e }
            monitor-exit(r9)     // Catch:{ all -> 0x007e }
        L_0x0028:
            return
        L_0x0029:
            r6 = 1
            r9.finished = r6     // Catch:{ all -> 0x007e }
            monitor-exit(r9)     // Catch:{ all -> 0x007e }
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            java.lang.String r6 = "pluginServiceName"
            java.lang.String r7 = r9.serviceName     // Catch:{ JSONException -> 0x0081 }
            r4.put(r6, r7)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r6 = "pluginStatus"
            java.lang.String[] r7 = org.apache.cordova.PluginResult.StatusMessages     // Catch:{ JSONException -> 0x0081 }
            int r8 = r10.getStatus()     // Catch:{ JSONException -> 0x0081 }
            r7 = r7[r8]     // Catch:{ JSONException -> 0x0081 }
            r4.put(r6, r7)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r6 = "action"
            java.lang.String r7 = "resume"
            r2.put(r6, r7)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r6 = "pendingResult"
            r2.put(r6, r4)     // Catch:{ JSONException -> 0x0081 }
        L_0x0057:
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
            r3.<init>((org.apache.cordova.PluginResult.Status) r6, (org.json.JSONObject) r2)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r5.add(r3)
            r5.add(r10)
            org.apache.cordova.PluginManager r6 = r9.pluginManager
            java.lang.String r7 = "CoreAndroid"
            org.apache.cordova.CordovaPlugin r0 = r6.getPlugin(r7)
            org.apache.cordova.CoreAndroid r0 = (org.apache.cordova.CoreAndroid) r0
            org.apache.cordova.PluginResult r6 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.OK
            r6.<init>((org.apache.cordova.PluginResult.Status) r7, (java.util.List<org.apache.cordova.PluginResult>) r5)
            r0.sendResumeEvent(r6)
            goto L_0x0028
        L_0x007e:
            r6 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x007e }
            throw r6
        L_0x0081:
            r1 = move-exception
            java.lang.String r6 = "CordovaResumeCallback"
            java.lang.String r7 = "Unable to create resume object for Activity Result"
            org.apache.cordova.LOG.m24e(r6, r7)
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.ResumeCallback.sendPluginResult(org.apache.cordova.PluginResult):void");
    }
}

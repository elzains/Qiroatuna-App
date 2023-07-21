package com.google.android.gms.internal;

import android.content.Context;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzie implements zzid {
    private final Context mContext;
    private final zzqh zztt;

    @zzme
    static class zza {
        private final String mValue;
        private final String zzAX;

        public zza(String str, String str2) {
            this.zzAX = str;
            this.mValue = str2;
        }

        public String getKey() {
            return this.zzAX;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @zzme
    static class zzb {
        private final String zzId;
        private final URL zzIe;
        private final ArrayList<zza> zzIf;
        private final String zzIg;

        public zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzId = str;
            this.zzIe = url;
            if (arrayList == null) {
                this.zzIf = new ArrayList<>();
            } else {
                this.zzIf = arrayList;
            }
            this.zzIg = str2;
        }

        public String zzgl() {
            return this.zzId;
        }

        public URL zzgm() {
            return this.zzIe;
        }

        public ArrayList<zza> zzgn() {
            return this.zzIf;
        }

        public String zzgo() {
            return this.zzIg;
        }
    }

    @zzme
    class zzc {
        private final zzd zzIh;
        private final boolean zzIi;
        private final String zzIj;

        public zzc(zzie zzie, boolean z, zzd zzd, String str) {
            this.zzIi = z;
            this.zzIh = zzd;
            this.zzIj = str;
        }

        public String getReason() {
            return this.zzIj;
        }

        public boolean isSuccess() {
            return this.zzIi;
        }

        public zzd zzgp() {
            return this.zzIh;
        }
    }

    @zzme
    static class zzd {
        private final String zzGr;
        private final String zzId;
        private final int zzIk;
        private final List<zza> zzIl;

        public zzd(String str, int i, List<zza> list, String str2) {
            this.zzId = str;
            this.zzIk = i;
            if (list == null) {
                this.zzIl = new ArrayList();
            } else {
                this.zzIl = list;
            }
            this.zzGr = str2;
        }

        public String getBody() {
            return this.zzGr;
        }

        public int getResponseCode() {
            return this.zzIk;
        }

        public String zzgl() {
            return this.zzId;
        }

        public Iterable<zza> zzgq() {
            return this.zzIl;
        }
    }

    public zzie(Context context, zzqh zzqh) {
        this.mContext = context;
        this.zztt = zzqh;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzie.zzc zza(com.google.android.gms.internal.zzie.zzb r10) {
        /*
            r9 = this;
            r1 = 0
            java.net.URL r0 = r10.zzgm()     // Catch:{ Exception -> 0x00f9, all -> 0x00f4 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Exception -> 0x00f9, all -> 0x00f4 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x00f9, all -> 0x00f4 }
            com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            android.content.Context r2 = r9.mContext     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            com.google.android.gms.internal.zzqh r3 = r9.zztt     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r3 = r3.zzba     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r4 = 0
            r1.zza((android.content.Context) r2, (java.lang.String) r3, (boolean) r4, (java.net.HttpURLConnection) r0)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.ArrayList r1 = r10.zzgn()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Iterator r2 = r1.iterator()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
        L_0x0021:
            boolean r1 = r2.hasNext()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r1 == 0) goto L_0x004c
            java.lang.Object r1 = r2.next()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            com.google.android.gms.internal.zzie$zza r1 = (com.google.android.gms.internal.zzie.zza) r1     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r3 = r1.getKey()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r1 = r1.getValue()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r0.addRequestProperty(r3, r1)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            goto L_0x0021
        L_0x0039:
            r1 = move-exception
            r2 = r0
        L_0x003b:
            com.google.android.gms.internal.zzie$zzc r0 = new com.google.android.gms.internal.zzie$zzc     // Catch:{ all -> 0x00f6 }
            r3 = 0
            r4 = 0
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f6 }
            r0.<init>(r9, r3, r4, r1)     // Catch:{ all -> 0x00f6 }
            if (r2 == 0) goto L_0x004b
            r2.disconnect()
        L_0x004b:
            return r0
        L_0x004c:
            java.lang.String r1 = r10.zzgo()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r1 != 0) goto L_0x0075
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r1 = r10.zzgo()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            byte[] r1 = r1.getBytes()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            int r2 = r1.length     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r0.setFixedLengthStreamingMode(r2)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r2.write(r1)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r2.close()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
        L_0x0075:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r4.<init>()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Map r1 = r0.getHeaderFields()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r1 == 0) goto L_0x00c7
            java.util.Map r1 = r0.getHeaderFields()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Set r1 = r1.entrySet()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Iterator r5 = r1.iterator()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
        L_0x008c:
            boolean r1 = r5.hasNext()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r1 == 0) goto L_0x00c7
            java.lang.Object r1 = r5.next()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.Object r2 = r1.getValue()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.List r2 = (java.util.List) r2     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.util.Iterator r6 = r2.iterator()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
        L_0x00a2:
            boolean r2 = r6.hasNext()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r2 == 0) goto L_0x008c
            java.lang.Object r2 = r6.next()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            com.google.android.gms.internal.zzie$zza r7 = new com.google.android.gms.internal.zzie$zza     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.Object r3 = r1.getKey()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r7.<init>(r3, r2)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r4.add(r7)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            goto L_0x00a2
        L_0x00bd:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x00c1:
            if (r1 == 0) goto L_0x00c6
            r1.disconnect()
        L_0x00c6:
            throw r0
        L_0x00c7:
            com.google.android.gms.internal.zzie$zzd r2 = new com.google.android.gms.internal.zzie$zzd     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r1 = r10.zzgl()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            int r3 = r0.getResponseCode()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            com.google.android.gms.internal.zzpo r5 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.io.InputStream r7 = r0.getInputStream()     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            java.lang.String r5 = r5.zza((java.io.InputStreamReader) r6)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r2.<init>(r1, r3, r4, r5)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            com.google.android.gms.internal.zzie$zzc r1 = new com.google.android.gms.internal.zzie$zzc     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            r3 = 1
            r4 = 0
            r1.<init>(r9, r3, r2, r4)     // Catch:{ Exception -> 0x0039, all -> 0x00bd }
            if (r0 == 0) goto L_0x00f1
            r0.disconnect()
        L_0x00f1:
            r0 = r1
            goto L_0x004b
        L_0x00f4:
            r0 = move-exception
            goto L_0x00c1
        L_0x00f6:
            r0 = move-exception
            r1 = r2
            goto L_0x00c1
        L_0x00f9:
            r0 = move-exception
            r2 = r1
            r1 = r0
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzie.zza(com.google.android.gms.internal.zzie$zzb):com.google.android.gms.internal.zzie$zzc");
    }

    /* access modifiers changed from: protected */
    public JSONObject zza(zzd zzd2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzd2.zzgl());
            if (zzd2.getBody() != null) {
                jSONObject.put("body", zzd2.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza next : zzd2.zzgq()) {
                jSONArray.put(new JSONObject().put("key", next.getKey()).put("value", next.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzd2.getResponseCode());
        } catch (JSONException e) {
            zzpk.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public void zza(final zzqw zzqw, final Map<String, String> map) {
        zzpn.zza((Runnable) new Runnable() {
            public void run() {
                zzpk.zzbf("Received Http request.");
                final JSONObject zzaa = zzie.this.zzaa((String) map.get("http_request"));
                if (zzaa == null) {
                    zzpk.m20e("Response should not be null.");
                } else {
                    zzpo.zzXC.post(new Runnable() {
                        public void run() {
                            zzqw.zzb("fetchHttpRequestCompleted", zzaa);
                            zzpk.zzbf("Dispatched http response.");
                        }
                    });
                }
            }
        });
    }

    public JSONObject zzaa(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            String str2 = "";
            try {
                str2 = jSONObject.optString("http_request_id");
                zzc zza2 = zza(zzb(jSONObject));
                if (zza2.isSuccess()) {
                    jSONObject2.put("response", zza(zza2.zzgp()));
                    jSONObject2.put("success", true);
                    return jSONObject2;
                }
                jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", zza2.getReason());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                    jSONObject2.put("success", false);
                    jSONObject2.put("reason", e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            zzpk.m20e("The request is not a valid JSON.");
            try {
                return new JSONObject().put("success", false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzb zzb(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", (String) null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            zzpk.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString("value")));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }
}

package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzw;
import com.rjfun.cordova.plugin.nativeaudio.NativeAudio;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zzic {
    public static final zzid zzHD = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
        }
    };
    public static final zzid zzHE = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = map.get("urls");
            if (TextUtils.isEmpty(str)) {
                zzpk.zzbh("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            HashMap hashMap = new HashMap();
            PackageManager packageManager = zzqw.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
            }
            zzqw.zza("openableURLs", (Map<String, ?>) hashMap);
        }
    };
    public static final zzid zzHF = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            PackageManager packageManager = zzqw.getContext().getPackageManager();
            try {
                try {
                    JSONArray jSONArray = new JSONObject(map.get("data")).getJSONArray("intents");
                    JSONObject jSONObject = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String optString = jSONObject2.optString("id");
                            String optString2 = jSONObject2.optString("u");
                            String optString3 = jSONObject2.optString("i");
                            String optString4 = jSONObject2.optString("m");
                            String optString5 = jSONObject2.optString("p");
                            String optString6 = jSONObject2.optString("c");
                            jSONObject2.optString("f");
                            jSONObject2.optString("e");
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                            try {
                                jSONObject.put(optString, packageManager.resolveActivity(intent, 65536) != null);
                            } catch (JSONException e) {
                                zzpk.zzb("Error constructing openable urls response.", e);
                            }
                        } catch (JSONException e2) {
                            zzpk.zzb("Error parsing the intent data.", e2);
                        }
                    }
                    zzqw.zzb("openableIntents", jSONObject);
                } catch (JSONException e3) {
                    zzqw.zzb("openableIntents", new JSONObject());
                }
            } catch (JSONException e4) {
                zzqw.zzb("openableIntents", new JSONObject());
            }
        }
    };
    public static final zzid zzHG = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            Uri uri;
            String str = map.get("u");
            if (str == null) {
                zzpk.zzbh("URL missing from click GMSG.");
                return;
            }
            Uri parse = Uri.parse(str);
            try {
                zzaw zzlx = zzqw.zzlx();
                if (zzlx != null && zzlx.zzc(parse)) {
                    uri = zzlx.zza(parse, zzqw.getContext(), zzqw.getView());
                    if (zzw.zzdl().zzjU() && TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
                        String zzF = zzw.zzdl().zzF(zzqw.getContext());
                        uri = zzw.zzcM().zza(uri, "fbs_aeid", zzF);
                        zzw.zzdl().zzf(zzqw.getContext(), zzF);
                    }
                    new zzpy(zzqw.getContext(), zzqw.zzly().zzba, uri.toString()).zziP();
                }
            } catch (zzax e) {
                String valueOf = String.valueOf(str);
                zzpk.zzbh(valueOf.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf) : new String("Unable to append parameter to URL: "));
            }
            uri = parse;
            String zzF2 = zzw.zzdl().zzF(zzqw.getContext());
            uri = zzw.zzcM().zza(uri, "fbs_aeid", zzF2);
            zzw.zzdl().zzf(zzqw.getContext(), zzF2);
            new zzpy(zzqw.getContext(), zzqw.zzly().zzba, uri.toString()).zziP();
        }
    };
    public static final zzid zzHH = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zze zzlt = zzqw.zzlt();
            if (zzlt != null) {
                zzlt.close();
                return;
            }
            zze zzlu = zzqw.zzlu();
            if (zzlu != null) {
                zzlu.close();
            } else {
                zzpk.zzbh("A GMSG tried to close something that wasn't an overlay.");
            }
        }
    };
    public static final zzid zzHI = new zzid() {
        private void zzd(zzqw zzqw) {
            zzpk.zzbg("Received support message, responding.");
            com.google.android.gms.ads.internal.zze zzby = zzqw.zzby();
            if (!(zzby == null || zzby.zzsO == null)) {
                zzqw.getContext();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", "checkSupport");
                jSONObject.put("supports", false);
                zzqw.zzb("appStreaming", jSONObject);
            } catch (Throwable th) {
                zzw.zzcQ().zza(th, "DefaultGmsgHandlers.processCheckSupportsMessage");
            }
        }

        public void zza(zzqw zzqw, Map<String, String> map) {
            if ("checkSupport".equals(map.get("action"))) {
                zzd(zzqw);
                return;
            }
            zze zzlt = zzqw.zzlt();
            if (zzlt != null) {
                zzlt.zzg(zzqw, map);
            }
        }
    };
    public static final zzid zzHJ = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzqw.zzL("1".equals(map.get("custom_close")));
        }
    };
    public static final zzid zzHK = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = map.get("u");
            if (str == null) {
                zzpk.zzbh("URL missing from httpTrack GMSG.");
            } else {
                new zzpy(zzqw.getContext(), zzqw.zzly().zzba, str).zziP();
            }
        }
    };
    public static final zzid zzHL = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String valueOf = String.valueOf(map.get("string"));
            zzpk.zzbg(valueOf.length() != 0 ? "Received log message: ".concat(valueOf) : new String("Received log message: "));
        }
    };
    public static final zzid zzHM = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzgy zzlL = zzqw.zzlL();
            if (zzlL != null) {
                zzlL.zzfX();
            }
        }
    };
    public static final zzid zzHN = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = map.get("tx");
            String str2 = map.get("ty");
            String str3 = map.get("td");
            try {
                int parseInt = Integer.parseInt(str);
                int parseInt2 = Integer.parseInt(str2);
                int parseInt3 = Integer.parseInt(str3);
                zzaw zzlx = zzqw.zzlx();
                if (zzlx != null) {
                    zzlx.zzT().zza(parseInt, parseInt2, parseInt3);
                }
            } catch (NumberFormatException e) {
                zzpk.zzbh("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final zzid zzHO = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (zzgd.zzDD.get().booleanValue()) {
                zzqw.zzM(!Boolean.parseBoolean(map.get("disabled")));
            }
        }
    };
    public static final zzid zzHP = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = map.get("action");
            if ("pause".equals(str)) {
                zzqw.zzbV();
            } else if ("resume".equals(str)) {
                zzqw.zzbW();
            }
        }
    };
    public static final zzid zzHQ = new zzio();
    public static final zzid zzHR = new zzip();
    public static final zzid zzHS = new zzij();
    public static final zzid zzHT = new zzit();
    public static final zzid zzHU = new zzib();
    public static final zzim zzHV = new zzim();
    public static final zzid zzHW = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                zzqw.zzlv().zzlU();
            } else if (map.keySet().contains(NativeAudio.STOP)) {
                zzqw.zzlv().zzlV();
            } else if (map.keySet().contains("cancel")) {
                zzqw.zzlv().zzlW();
            }
        }
    };
    public static final zzid zzHX = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                zzqw.zzN(true);
            }
            if (map.keySet().contains(NativeAudio.STOP)) {
                zzqw.zzN(false);
            }
        }
    };
    public static final zzid zzHY = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzqw.zza("locationReady", (Map<String, ?>) zzw.zzcM().zza((View) zzqw, (WindowManager) zzqw.getContext().getSystemService("window")));
            zzpk.zzbh("GET LOCATION COMPILED");
        }
    };
}

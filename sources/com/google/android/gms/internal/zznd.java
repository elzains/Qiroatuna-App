package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zznm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.cordova.globalization.Globalization;
import org.apache.cordova.networkinformation.NetworkManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zznd {
    private static final SimpleDateFormat zzTJ = new SimpleDateFormat("yyyyMMdd", Locale.US);

    private static Integer zzB(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0144 A[Catch:{ JSONException -> 0x023c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzmn zza(android.content.Context r48, com.google.android.gms.internal.zzmk r49, java.lang.String r50) {
        /*
            org.json.JSONObject r28 = new org.json.JSONObject     // Catch:{ JSONException -> 0x023c }
            r0 = r28
            r1 = r50
            r0.<init>(r1)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "ad_base_url"
            r5 = 0
            r0 = r28
            java.lang.String r6 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "ad_url"
            r5 = 0
            r0 = r28
            java.lang.String r7 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "ad_size"
            r5 = 0
            r0 = r28
            java.lang.String r19 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "ad_slot_size"
            r0 = r28
            r1 = r19
            java.lang.String r43 = r0.optString(r4, r1)     // Catch:{ JSONException -> 0x023c }
            if (r49 == 0) goto L_0x00cf
            r0 = r49
            int r4 = r0.zzRE     // Catch:{ JSONException -> 0x023c }
            if (r4 == 0) goto L_0x00cf
            r27 = 1
        L_0x0038:
            java.lang.String r4 = "ad_json"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            if (r5 != 0) goto L_0x004c
            java.lang.String r4 = "ad_html"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
        L_0x004c:
            if (r5 != 0) goto L_0x0057
            java.lang.String r4 = "body"
            r5 = 0
            r0 = r28
            java.lang.String r5 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
        L_0x0057:
            r20 = -1
            java.lang.String r4 = "debug_dialog"
            r8 = 0
            r0 = r28
            java.lang.String r22 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "debug_signals"
            r8 = 0
            r0 = r28
            java.lang.String r45 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "interstitial_timeout"
            r0 = r28
            boolean r4 = r0.has(r4)     // Catch:{ JSONException -> 0x023c }
            if (r4 == 0) goto L_0x00d3
            java.lang.String r4 = "interstitial_timeout"
            r0 = r28
            double r8 = r0.getDouble(r4)     // Catch:{ JSONException -> 0x023c }
            r10 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 * r10
            long r12 = (long) r8     // Catch:{ JSONException -> 0x023c }
        L_0x0084:
            java.lang.String r4 = "orientation"
            r8 = 0
            r0 = r28
            java.lang.String r4 = r0.optString(r4, r8)     // Catch:{ JSONException -> 0x023c }
            r18 = -1
            java.lang.String r8 = "portrait"
            boolean r8 = r8.equals(r4)     // Catch:{ JSONException -> 0x023c }
            if (r8 == 0) goto L_0x00d6
            com.google.android.gms.internal.zzpp r4 = com.google.android.gms.ads.internal.zzw.zzcO()     // Catch:{ JSONException -> 0x023c }
            int r18 = r4.zzkR()     // Catch:{ JSONException -> 0x023c }
        L_0x009f:
            r4 = 0
            boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x023c }
            if (r8 == 0) goto L_0x0271
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x023c }
            if (r8 != 0) goto L_0x0271
            r0 = r49
            com.google.android.gms.internal.zzqh r4 = r0.zzvn     // Catch:{ JSONException -> 0x023c }
            java.lang.String r6 = r4.zzba     // Catch:{ JSONException -> 0x023c }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r49
            r5 = r48
            com.google.android.gms.internal.zzmn r4 = com.google.android.gms.internal.zznc.zza(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r6 = r4.zzNJ     // Catch:{ JSONException -> 0x023c }
            java.lang.String r7 = r4.body     // Catch:{ JSONException -> 0x023c }
            long r0 = r4.zzSr     // Catch:{ JSONException -> 0x023c }
            r20 = r0
        L_0x00c6:
            if (r7 != 0) goto L_0x00e7
            com.google.android.gms.internal.zzmn r4 = new com.google.android.gms.internal.zzmn     // Catch:{ JSONException -> 0x023c }
            r5 = 0
            r4.<init>(r5)     // Catch:{ JSONException -> 0x023c }
        L_0x00ce:
            return r4
        L_0x00cf:
            r27 = 0
            goto L_0x0038
        L_0x00d3:
            r12 = -1
            goto L_0x0084
        L_0x00d6:
            java.lang.String r8 = "landscape"
            boolean r4 = r8.equals(r4)     // Catch:{ JSONException -> 0x023c }
            if (r4 == 0) goto L_0x009f
            com.google.android.gms.internal.zzpp r4 = com.google.android.gms.ads.internal.zzw.zzcO()     // Catch:{ JSONException -> 0x023c }
            int r18 = r4.zzkQ()     // Catch:{ JSONException -> 0x023c }
            goto L_0x009f
        L_0x00e7:
            java.lang.String r5 = "click_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x023c }
            if (r4 != 0) goto L_0x025c
            r8 = 0
        L_0x00f2:
            if (r5 == 0) goto L_0x00f8
            java.util.List r8 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r8)     // Catch:{ JSONException -> 0x023c }
        L_0x00f8:
            java.lang.String r5 = "impression_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x023c }
            if (r4 != 0) goto L_0x0260
            r9 = 0
        L_0x0103:
            if (r5 == 0) goto L_0x0109
            java.util.List r9 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r9)     // Catch:{ JSONException -> 0x023c }
        L_0x0109:
            java.lang.String r5 = "manual_impression_urls"
            r0 = r28
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ JSONException -> 0x023c }
            if (r4 != 0) goto L_0x0264
            r15 = 0
        L_0x0114:
            if (r5 == 0) goto L_0x011a
            java.util.List r15 = zza((org.json.JSONArray) r5, (java.util.List<java.lang.String>) r15)     // Catch:{ JSONException -> 0x023c }
        L_0x011a:
            if (r4 == 0) goto L_0x026e
            int r5 = r4.orientation     // Catch:{ JSONException -> 0x023c }
            r10 = -1
            if (r5 == r10) goto L_0x0125
            int r0 = r4.orientation     // Catch:{ JSONException -> 0x023c }
            r18 = r0
        L_0x0125:
            long r10 = r4.zzSm     // Catch:{ JSONException -> 0x023c }
            r16 = 0
            int r5 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r5 <= 0) goto L_0x026e
            long r10 = r4.zzSm     // Catch:{ JSONException -> 0x023c }
        L_0x012f:
            java.lang.String r4 = "active_view"
            r0 = r28
            java.lang.String r25 = r0.optString(r4)     // Catch:{ JSONException -> 0x023c }
            r24 = 0
            java.lang.String r4 = "ad_is_javascript"
            r5 = 0
            r0 = r28
            boolean r23 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            if (r23 == 0) goto L_0x014d
            java.lang.String r4 = "ad_passback_url"
            r5 = 0
            r0 = r28
            java.lang.String r24 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
        L_0x014d:
            java.lang.String r4 = "mediation"
            r5 = 0
            r0 = r28
            boolean r12 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "custom_render_allowed"
            r5 = 0
            r0 = r28
            boolean r26 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "content_url_opted_out"
            r5 = 1
            r0 = r28
            boolean r29 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "content_vertical_opted_out"
            r5 = 1
            r0 = r28
            boolean r46 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "prefetch"
            r5 = 0
            r0 = r28
            boolean r30 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "refresh_interval_milliseconds"
            r16 = -1
            r0 = r28
            r1 = r16
            long r16 = r0.optLong(r4, r1)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "mediation_config_cache_time_milliseconds"
            r32 = -1
            r0 = r28
            r1 = r32
            long r13 = r0.optLong(r4, r1)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "gws_query_id"
            java.lang.String r5 = ""
            r0 = r28
            java.lang.String r31 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "height"
            java.lang.String r5 = "fluid"
            java.lang.String r32 = ""
            r0 = r28
            r1 = r32
            java.lang.String r5 = r0.optString(r5, r1)     // Catch:{ JSONException -> 0x023c }
            boolean r32 = r4.equals(r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "native_express"
            r5 = 0
            r0 = r28
            boolean r33 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "video_start_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x023c }
            r5 = 0
            java.util.List r35 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "video_complete_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x023c }
            r5 = 0
            java.util.List r36 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "rewards"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x023c }
            com.google.android.gms.internal.zzoo r34 = com.google.android.gms.internal.zzoo.zza(r4)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "use_displayed_impression"
            r5 = 0
            r0 = r28
            boolean r37 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "auto_protection_configuration"
            r0 = r28
            org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch:{ JSONException -> 0x023c }
            com.google.android.gms.internal.zzmp r38 = com.google.android.gms.internal.zzmp.zzf(r4)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "set_cookie"
            java.lang.String r5 = ""
            r0 = r28
            java.lang.String r40 = r0.optString(r4, r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "remote_ping_urls"
            r0 = r28
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ JSONException -> 0x023c }
            r5 = 0
            java.util.List r41 = zza((org.json.JSONArray) r4, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "safe_browsing"
            r0 = r28
            org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch:{ JSONException -> 0x023c }
            com.google.android.gms.internal.zzor r44 = com.google.android.gms.internal.zzor.zzh(r4)     // Catch:{ JSONException -> 0x023c }
            java.lang.String r4 = "render_in_browser"
            r0 = r49
            boolean r5 = r0.zzKJ     // Catch:{ JSONException -> 0x023c }
            r0 = r28
            boolean r42 = r0.optBoolean(r4, r5)     // Catch:{ JSONException -> 0x023c }
            com.google.android.gms.internal.zzmn r4 = new com.google.android.gms.internal.zzmn     // Catch:{ JSONException -> 0x023c }
            r0 = r49
            boolean r0 = r0.zzRG     // Catch:{ JSONException -> 0x023c }
            r28 = r0
            r0 = r49
            boolean r0 = r0.zzRV     // Catch:{ JSONException -> 0x023c }
            r39 = r0
            r0 = r49
            boolean r0 = r0.zzSh     // Catch:{ JSONException -> 0x023c }
            r47 = r0
            r5 = r49
            r4.<init>(r5, r6, r7, r8, r9, r10, r12, r13, r15, r16, r18, r19, r20, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)     // Catch:{ JSONException -> 0x023c }
            goto L_0x00ce
        L_0x023c:
            r4 = move-exception
            java.lang.String r5 = "Could not parse the inline ad response: "
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r6 = r4.length()
            if (r6 == 0) goto L_0x0268
            java.lang.String r4 = r5.concat(r4)
        L_0x0251:
            com.google.android.gms.internal.zzpk.zzbh(r4)
            com.google.android.gms.internal.zzmn r4 = new com.google.android.gms.internal.zzmn
            r5 = 0
            r4.<init>(r5)
            goto L_0x00ce
        L_0x025c:
            java.util.List<java.lang.String> r8 = r4.zzKF     // Catch:{ JSONException -> 0x023c }
            goto L_0x00f2
        L_0x0260:
            java.util.List<java.lang.String> r9 = r4.zzKG     // Catch:{ JSONException -> 0x023c }
            goto L_0x0103
        L_0x0264:
            java.util.List<java.lang.String> r15 = r4.zzSp     // Catch:{ JSONException -> 0x023c }
            goto L_0x0114
        L_0x0268:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
            goto L_0x0251
        L_0x026e:
            r10 = r12
            goto L_0x012f
        L_0x0271:
            r7 = r5
            goto L_0x00c6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznd.zza(android.content.Context, com.google.android.gms.internal.zzmk, java.lang.String):com.google.android.gms.internal.zzmn");
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzna zzna) {
        zzmk zzmk = zzna.zzTi;
        Location location = zzna.zzzb;
        zzni zzni = zzna.zzTj;
        Bundle bundle = zzna.zzRF;
        JSONObject jSONObject = zzna.zzTk;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("extra_caps", zzgd.zzEe.get());
            if (zzna.zzRM.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", zzna.zzRM));
            }
            if (zzmk.zzRx != null) {
                hashMap.put("ad_pos", zzmk.zzRx);
            }
            zza((HashMap<String, Object>) hashMap, zzmk.zzRy);
            if (zzmk.zzvr.zzzA != null) {
                boolean z = false;
                boolean z2 = false;
                for (zzeg zzeg : zzmk.zzvr.zzzA) {
                    if (!zzeg.zzzC && !z2) {
                        hashMap.put("format", zzeg.zzzy);
                        z2 = true;
                    }
                    if (zzeg.zzzC && !z) {
                        hashMap.put("fluid", "height");
                        z = true;
                    }
                    if (z2 && z) {
                        break;
                    }
                }
            } else {
                hashMap.put("format", zzmk.zzvr.zzzy);
                if (zzmk.zzvr.zzzC) {
                    hashMap.put("fluid", "height");
                }
            }
            if (zzmk.zzvr.width == -1) {
                hashMap.put("smart_w", Globalization.FULL);
            }
            if (zzmk.zzvr.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (zzmk.zzvr.zzzA != null) {
                StringBuilder sb = new StringBuilder();
                boolean z3 = false;
                for (zzeg zzeg2 : zzmk.zzvr.zzzA) {
                    if (zzeg2.zzzC) {
                        z3 = true;
                    } else {
                        if (sb.length() != 0) {
                            sb.append("|");
                        }
                        sb.append(zzeg2.width == -1 ? (int) (((float) zzeg2.widthPixels) / zzni.zzxk) : zzeg2.width);
                        sb.append("x");
                        sb.append(zzeg2.height == -2 ? (int) (((float) zzeg2.heightPixels) / zzni.zzxk) : zzeg2.height);
                    }
                }
                if (z3) {
                    if (sb.length() != 0) {
                        sb.insert(0, "|");
                    }
                    sb.insert(0, "320x50");
                }
                hashMap.put("sz", sb);
            }
            if (zzmk.zzRE != 0) {
                hashMap.put("native_version", Integer.valueOf(zzmk.zzRE));
                hashMap.put("native_templates", zzmk.zzvK);
                hashMap.put("native_image_orientation", zzc(zzmk.zzvF));
                if (!zzmk.zzRN.isEmpty()) {
                    hashMap.put("native_custom_templates", zzmk.zzRN);
                }
            }
            if (zzmk.zzvr.zzzD) {
                hashMap.put("ene", true);
            }
            if (zzmk.zzvH != null) {
                hashMap.put("is_icon_ad", true);
                hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(zzmk.zzvH.zzzZ));
            }
            hashMap.put("slotname", zzmk.zzvl);
            hashMap.put("pn", zzmk.applicationInfo.packageName);
            if (zzmk.zzRz != null) {
                hashMap.put("vc", Integer.valueOf(zzmk.zzRz.versionCode));
            }
            hashMap.put("ms", zzna.zzRA);
            hashMap.put("seq_num", zzmk.zzRB);
            hashMap.put("session_id", zzmk.zzRC);
            hashMap.put("js", zzmk.zzvn.zzba);
            zza(hashMap, zzni, zzna.zzTg, zzmk.zzSa, zzna.zzTf);
            zza((HashMap<String, Object>) hashMap, zzna, context);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza((HashMap<String, Object>) hashMap, location);
            } else if (zzmk.zzRy.versionCode >= 2 && zzmk.zzRy.zzzb != null) {
                zza((HashMap<String, Object>) hashMap, zzmk.zzRy.zzzb);
            }
            if (zzmk.versionCode >= 2) {
                hashMap.put("quality_signals", zzmk.zzRD);
            }
            if (zzmk.versionCode >= 4 && zzmk.zzRG) {
                hashMap.put("forceHttps", Boolean.valueOf(zzmk.zzRG));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (zzmk.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(zzmk.zzxk));
                hashMap.put("sh", Integer.valueOf(zzmk.zzRI));
                hashMap.put("sw", Integer.valueOf(zzmk.zzRH));
            } else {
                hashMap.put("u_sd", Float.valueOf(zzni.zzxk));
                hashMap.put("sh", Integer.valueOf(zzni.zzRI));
                hashMap.put("sw", Integer.valueOf(zzni.zzRH));
            }
            if (zzmk.versionCode >= 6) {
                if (!TextUtils.isEmpty(zzmk.zzRJ)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(zzmk.zzRJ));
                    } catch (JSONException e) {
                        zzpk.zzc("Problem serializing view hierarchy to JSON", e);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(zzmk.zzRK));
            }
            if (zzmk.versionCode >= 7) {
                hashMap.put("request_id", zzmk.zzRL);
            }
            if (zzmk.versionCode >= 11 && zzmk.zzRP != null) {
                hashMap.put("capability", zzmk.zzRP.toBundle());
            }
            if (zzmk.versionCode >= 12 && !TextUtils.isEmpty(zzmk.zzRQ)) {
                hashMap.put("anchor", zzmk.zzRQ);
            }
            if (zzmk.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(zzmk.zzRR));
            }
            if (zzmk.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(zzmk.zzRX));
            }
            if (zzmk.versionCode >= 14 && zzmk.zzRS > 0) {
                hashMap.put("target_api", Integer.valueOf(zzmk.zzRS));
            }
            if (zzmk.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(zzmk.zzRT == -1 ? -1 : zzmk.zzRT));
            }
            if (zzmk.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(zzmk.zzRU));
            }
            if (zzmk.versionCode >= 18) {
                if (!TextUtils.isEmpty(zzmk.zzRY)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(zzmk.zzRY));
                    } catch (JSONException e2) {
                        zzpk.zzc("Problem creating json from app settings", e2);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(zzmk.zzKJ));
            }
            if (zzmk.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzmk.zzRZ));
            }
            zza(context, (HashMap<String, Object>) hashMap, zzmk.zzvn);
            hashMap.put("cache_state", jSONObject);
            if (zzmk.versionCode >= 19) {
                hashMap.put("gct", zzmk.zzSb);
            }
            if (zzmk.versionCode >= 21 && zzmk.zzSc) {
                hashMap.put("de", "1");
            }
            if (zzgd.zzDc.get().booleanValue()) {
                zza((HashMap<String, Object>) hashMap, zzmk);
            }
            if (zzmk.versionCode >= 22 && zzw.zzdl().zzjQ()) {
                hashMap.put("gmp_app_id", zzmk.zzSe);
                hashMap.put("fbs_aiid", zzmk.zzSf);
                hashMap.put("fbs_aeid", zzmk.zzSg);
            }
            if (zzpk.zzak(2)) {
                String valueOf = String.valueOf(zzw.zzcM().zzQ((Map<String, ?>) hashMap).toString(2));
                zzpk.m19v(valueOf.length() != 0 ? "Ad Request JSON: ".concat(valueOf) : new String("Ad Request JSON: "));
            }
            return zzw.zzcM().zzQ((Map<String, ?>) hashMap);
        } catch (JSONException e3) {
            String valueOf2 = String.valueOf(e3.getMessage());
            zzpk.zzbh(valueOf2.length() != 0 ? "Problem serializing ad request to JSON: ".concat(valueOf2) : new String("Problem serializing ad request to JSON: "));
            return null;
        }
    }

    private static void zza(Context context, HashMap<String, Object> hashMap, zzqh zzqh) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cl", "146496160");
        bundle2.putString("rapid_rc", "dev");
        bundle2.putString("rapid_rollup", "HEAD");
        bundle.putBundle("build_meta", bundle2);
        bundle.putString("mf", Boolean.toString(zzgd.zzEg.get().booleanValue()));
        bundle.putBoolean("instant_app", zzadg.zzbi(context).zzzw());
        bundle.putBoolean("lite", zzqh.zzYZ);
        hashMap.put("sdk_env", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put(Globalization.LONG, valueOf4);
        hashMap2.put(Globalization.TIME, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void zza(HashMap<String, Object> hashMap, zzec zzec) {
        String zzkF = zzpi.zzkF();
        if (zzkF != null) {
            hashMap.put("abf", zzkF);
        }
        if (zzec.zzyT != -1) {
            hashMap.put("cust_age", zzTJ.format(new Date(zzec.zzyT)));
        }
        if (zzec.extras != null) {
            hashMap.put("extras", zzec.extras);
        }
        if (zzec.zzyU != -1) {
            hashMap.put("cust_gender", Integer.valueOf(zzec.zzyU));
        }
        if (zzec.zzyV != null) {
            hashMap.put("kw", zzec.zzyV);
        }
        if (zzec.zzyX != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzec.zzyX));
        }
        if (zzec.zzyW) {
            hashMap.put("adtest", "on");
        }
        if (zzec.versionCode >= 2) {
            if (zzec.zzyY) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(zzec.zzyZ)) {
                hashMap.put("ppid", zzec.zzyZ);
            }
            if (zzec.zzza != null) {
                zza(hashMap, zzec.zzza);
            }
        }
        if (zzec.versionCode >= 3 && zzec.zzzc != null) {
            hashMap.put("url", zzec.zzzc);
        }
        if (zzec.versionCode >= 5) {
            if (zzec.zzze != null) {
                hashMap.put("custom_targeting", zzec.zzze);
            }
            if (zzec.zzzf != null) {
                hashMap.put("category_exclusions", zzec.zzzf);
            }
            if (zzec.zzzg != null) {
                hashMap.put("request_agent", zzec.zzzg);
            }
        }
        if (zzec.versionCode >= 6 && zzec.zzzh != null) {
            hashMap.put("request_pkg", zzec.zzzh);
        }
        if (zzec.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(zzec.zzzi));
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzfp zzfp) {
        String str;
        String str2 = null;
        if (Color.alpha(zzfp.zzAH) != 0) {
            hashMap.put("acolor", zzab(zzfp.zzAH));
        }
        if (Color.alpha(zzfp.backgroundColor) != 0) {
            hashMap.put("bgcolor", zzab(zzfp.backgroundColor));
        }
        if (!(Color.alpha(zzfp.zzAI) == 0 || Color.alpha(zzfp.zzAJ) == 0)) {
            hashMap.put("gradientto", zzab(zzfp.zzAI));
            hashMap.put("gradientfrom", zzab(zzfp.zzAJ));
        }
        if (Color.alpha(zzfp.zzAK) != 0) {
            hashMap.put("bcolor", zzab(zzfp.zzAK));
        }
        hashMap.put("bthick", Integer.toString(zzfp.zzAL));
        switch (zzfp.zzAM) {
            case 0:
                str = NetworkManager.TYPE_NONE;
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            hashMap.put("btype", str);
        }
        switch (zzfp.zzAN) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = Globalization.MEDIUM;
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            hashMap.put("callbuttoncolor", str2);
        }
        if (zzfp.zzAO != null) {
            hashMap.put("channel", zzfp.zzAO);
        }
        if (Color.alpha(zzfp.zzAP) != 0) {
            hashMap.put("dcolor", zzab(zzfp.zzAP));
        }
        if (zzfp.zzAQ != null) {
            hashMap.put("font", zzfp.zzAQ);
        }
        if (Color.alpha(zzfp.zzAR) != 0) {
            hashMap.put("hcolor", zzab(zzfp.zzAR));
        }
        hashMap.put("headersize", Integer.toString(zzfp.zzAS));
        if (zzfp.zzAT != null) {
            hashMap.put("q", zzfp.zzAT);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzmk zzmk) {
        boolean z = true;
        String str = zzmk.zzvr.zzzy;
        boolean z2 = str.equals("interstitial_mb") || str.equals("reward_mb");
        Bundle bundle = zzmk.zzSd;
        if (bundle == null) {
            z = false;
        }
        if (z2 && z) {
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("interstitial_pool", bundle);
            hashMap.put("counters", bundle2);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzna zzna, Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("doritos", zzna.zzTh);
        if (zzgd.zzCS.get().booleanValue()) {
            String str = null;
            boolean z = false;
            if (zzna.zzpR != null) {
                str = zzna.zzpR.getId();
                z = zzna.zzpR.isLimitAdTrackingEnabled();
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("rdid", str);
                bundle.putBoolean("is_lat", z);
                bundle.putString("idtype", "adid");
            } else {
                bundle.putString("pdid", zzel.zzeT().zzae(context));
                bundle.putString("pdidtype", "ssaid");
            }
        }
        hashMap.put("pii", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, zzni zzni, zznm.zza zza, Bundle bundle, Bundle bundle2) {
        hashMap.put("am", Integer.valueOf(zzni.zzUE));
        hashMap.put("cog", zzB(zzni.zzUF));
        hashMap.put("coh", zzB(zzni.zzUG));
        if (!TextUtils.isEmpty(zzni.zzUH)) {
            hashMap.put("carrier", zzni.zzUH);
        }
        hashMap.put("gl", zzni.zzUI);
        if (zzni.zzUJ) {
            hashMap.put("simulator", 1);
        }
        if (zzni.zzUK) {
            hashMap.put("is_sidewinder", 1);
        }
        hashMap.put("ma", zzB(zzni.zzUL));
        hashMap.put("sp", zzB(zzni.zzUM));
        hashMap.put("hl", zzni.zzUN);
        if (!TextUtils.isEmpty(zzni.zzUO)) {
            hashMap.put("mv", zzni.zzUO);
        }
        hashMap.put("muv", Integer.valueOf(zzni.zzUP));
        if (zzni.zzUQ != -2) {
            hashMap.put("cnt", Integer.valueOf(zzni.zzUQ));
        }
        hashMap.put("gnt", Integer.valueOf(zzni.zzUR));
        hashMap.put("pt", Integer.valueOf(zzni.zzUS));
        hashMap.put("rm", Integer.valueOf(zzni.zzUT));
        hashMap.put("riv", Integer.valueOf(zzni.zzUU));
        Bundle bundle3 = new Bundle();
        bundle3.putString("build", zzni.zzUZ);
        Bundle bundle4 = new Bundle();
        bundle4.putBoolean("is_charging", zzni.zzUW);
        bundle4.putDouble("battery_level", zzni.zzUV);
        bundle3.putBundle("battery", bundle4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("active_network_state", zzni.zzUY);
        bundle5.putBoolean("active_network_metered", zzni.zzUX);
        if (zza != null) {
            Bundle bundle6 = new Bundle();
            bundle6.putInt("predicted_latency_micros", zza.zzVf);
            bundle6.putLong("predicted_down_throughput_bps", zza.zzVg);
            bundle6.putLong("predicted_up_throughput_bps", zza.zzVh);
            bundle5.putBundle("predictions", bundle6);
        }
        bundle3.putBundle("network", bundle5);
        Bundle bundle7 = new Bundle();
        bundle7.putBoolean("is_browser_custom_tabs_capable", zzni.zzVa);
        bundle3.putBundle("browser", bundle7);
        if (bundle != null) {
            bundle3.putBundle("android_mem_info", zzg(bundle));
        }
        Bundle bundle8 = new Bundle();
        bundle8.putBundle("parental_controls", bundle2);
        bundle3.putBundle("play_store", bundle8);
        hashMap.put("device", bundle3);
    }

    private static String zzab(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(16777215 & i)});
    }

    public static JSONObject zzb(zzmn zzmn) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (zzmn.zzNJ != null) {
            jSONObject.put("ad_base_url", zzmn.zzNJ);
        }
        if (zzmn.zzSq != null) {
            jSONObject.put("ad_size", zzmn.zzSq);
        }
        jSONObject.put("native", zzmn.zzzB);
        if (zzmn.zzzB) {
            jSONObject.put("ad_json", zzmn.body);
        } else {
            jSONObject.put("ad_html", zzmn.body);
        }
        if (zzmn.zzSs != null) {
            jSONObject.put("debug_dialog", zzmn.zzSs);
        }
        if (zzmn.zzSJ != null) {
            jSONObject.put("debug_signals", zzmn.zzSJ);
        }
        if (zzmn.zzSm != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzmn.zzSm) / 1000.0d);
        }
        if (zzmn.orientation == zzw.zzcO().zzkR()) {
            jSONObject.put("orientation", "portrait");
        } else if (zzmn.orientation == zzw.zzcO().zzkQ()) {
            jSONObject.put("orientation", "landscape");
        }
        if (zzmn.zzKF != null) {
            jSONObject.put("click_urls", zzl(zzmn.zzKF));
        }
        if (zzmn.zzKG != null) {
            jSONObject.put("impression_urls", zzl(zzmn.zzKG));
        }
        if (zzmn.zzSp != null) {
            jSONObject.put("manual_impression_urls", zzl(zzmn.zzSp));
        }
        if (zzmn.zzSv != null) {
            jSONObject.put("active_view", zzmn.zzSv);
        }
        jSONObject.put("ad_is_javascript", zzmn.zzSt);
        if (zzmn.zzSu != null) {
            jSONObject.put("ad_passback_url", zzmn.zzSu);
        }
        jSONObject.put("mediation", zzmn.zzSn);
        jSONObject.put("custom_render_allowed", zzmn.zzSw);
        jSONObject.put("content_url_opted_out", zzmn.zzSx);
        jSONObject.put("content_vertical_opted_out", zzmn.zzSK);
        jSONObject.put("prefetch", zzmn.zzSy);
        if (zzmn.zzKL != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzmn.zzKL);
        }
        if (zzmn.zzSo != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzmn.zzSo);
        }
        if (!TextUtils.isEmpty(zzmn.zzSB)) {
            jSONObject.put("gws_query_id", zzmn.zzSB);
        }
        jSONObject.put("fluid", zzmn.zzzC ? "height" : "");
        jSONObject.put("native_express", zzmn.zzzD);
        if (zzmn.zzSD != null) {
            jSONObject.put("video_start_urls", zzl(zzmn.zzSD));
        }
        if (zzmn.zzSE != null) {
            jSONObject.put("video_complete_urls", zzl(zzmn.zzSE));
        }
        if (zzmn.zzSC != null) {
            jSONObject.put("rewards", zzmn.zzSC.zzjP());
        }
        jSONObject.put("use_displayed_impression", zzmn.zzSF);
        jSONObject.put("auto_protection_configuration", zzmn.zzSG);
        jSONObject.put("render_in_browser", zzmn.zzKJ);
        return jSONObject;
    }

    private static String zzc(zzhc zzhc) {
        switch (zzhc != null ? zzhc.zzHb : 0) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return "any";
        }
    }

    private static Bundle zzg(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        bundle2.putString("web_view_count", Integer.toString(bundle.getInt("web_view_count", 0)));
        Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    @Nullable
    static JSONArray zzl(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}

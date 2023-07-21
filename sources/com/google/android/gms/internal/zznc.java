package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzmt;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zzqp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zznc extends zzmt.zza {
    private static zznc zzTv;
    private static final Object zztX = new Object();
    private final Context mContext;
    private final zznb zzTw;
    private final zzfw zzTx;
    private final zzji zzTy;

    zznc(Context context, zzfw zzfw, zznb zznb) {
        this.mContext = context;
        this.zzTw = zznb;
        this.zzTx = zzfw;
        this.zzTy = new zzji(context.getApplicationContext() != null ? context.getApplicationContext() : context, zzqh.zzlk(), zzfw.zzfq(), new zzpt<zzjf>(this) {
            /* renamed from: zza */
            public void zzd(zzjf zzjf) {
                zzjf.zza("/log", zzic.zzHL);
            }
        }, new zzji.zzb());
    }

    private static zzmn zza(Context context, zzji zzji, zzfw zzfw, zznb zznb, zzmk zzmk) {
        Bundle bundle;
        zzqm zzqm;
        String str;
        String string;
        zzpk.zzbf("Starting ad request from service using: AFMA_getAd");
        zzgd.initialize(context);
        final zzgl zzgl = new zzgl(zzgd.zzBZ.get().booleanValue(), "load_ad", zzmk.zzvr.zzzy);
        if (zzmk.versionCode > 10 && zzmk.zzRO != -1) {
            zzgl.zza(zzgl.zzc(zzmk.zzRO), "cts");
        }
        zzgj zzfB = zzgl.zzfB();
        zzqm<Bundle> zzt = zznb.zzTt.zzt(context);
        Future<zznm.zza> zzB = zznb.zzTs.zzB(context);
        Future<String> zzaS = zznb.zzTn.zzaS(zzmk.zzRz.packageName);
        zzqm<String> zzg = zznb.zzTu.zzg(zzmk);
        Future<zzni> zzA = zzw.zzcV().zzA(context);
        Future zzqk = new zzqk(null);
        Bundle bundle2 = zzmk.zzRy.extras;
        Future zza = (!zzmk.zzRV || (bundle2 != null && bundle2.getString("_ad") != null)) ? zzqk : zznb.zzTq.zza(zzmk.applicationInfo);
        Future zzG = zzgd.zzCS.get().booleanValue() ? zznb.zzTu.zzG(context) : new zzqk(null);
        final Bundle bundle3 = (zzmk.versionCode < 4 || zzmk.zzRF == null) ? null : zzmk.zzRF;
        if (!zzgd.zzCp.get().booleanValue() || zznb.zzTl == null) {
            bundle = bundle3;
            zzqm = null;
        } else {
            if (bundle3 == null && zzgd.zzCq.get().booleanValue()) {
                zzpk.m19v("contentInfo is not present, but we'll still launch the app index task");
                bundle3 = new Bundle();
            }
            if (bundle3 != null) {
                final zznb zznb2 = zznb;
                final Context context2 = context;
                final zzmk zzmk2 = zzmk;
                bundle = bundle3;
                zzqm = zzpn.zza(new Callable<Void>() {
                    /* renamed from: zzbk */
                    public Void call() throws Exception {
                        String str = zzmk2.zzRz.packageName;
                        return null;
                    }
                });
            } else {
                bundle = bundle3;
                zzqm = null;
            }
        }
        if (zzw.zzcM().zze(context, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzpk.zzbf("Device is offline.");
        }
        String uuid = zzmk.versionCode >= 7 ? zzmk.zzRL : UUID.randomUUID().toString();
        final zzne zzne = new zzne(uuid, zzmk.applicationInfo.packageName);
        if (zzmk.zzRy.extras != null && (string = zzmk.zzRy.extras.getString("_ad")) != null) {
            return zznd.zza(context, zzmk, string);
        }
        List<String> zza2 = zznb.zzTo.zza(zzmk);
        if (zzqm != null) {
            try {
                zzpk.m19v("Waiting for app index fetching task.");
                zzqm.get(zzgd.zzCr.get().longValue(), TimeUnit.MILLISECONDS);
                zzpk.m19v("App index fetching task completed.");
            } catch (InterruptedException | ExecutionException e) {
                zzpk.zzc("Failed to fetch app index signal", e);
            } catch (TimeoutException e2) {
                zzpk.zzbf("Timed out waiting for app index fetching task");
            }
        }
        Bundle bundle4 = (Bundle) zza(zzt, zzgd.zzEX.get());
        zznm.zza zza3 = (zznm.zza) zza(zzB, zzgd.zzDH.get());
        Location location = (Location) zza(zza, zzgd.zzEF.get());
        AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zza(zzG, zzgd.zzCT.get());
        try {
            str = (String) zzg.get();
        } catch (Exception e3) {
            zzw.zzcQ().zza((Throwable) e3, "AdRequestServiceImpl.loadAdAsync.qs");
            zzpk.zzc("Error fetching qs signals. Continuing.", e3);
            str = null;
        }
        String str2 = null;
        try {
            str2 = zzaS.get();
        } catch (Exception e4) {
            zzw.zzcQ().zza((Throwable) e4, "AdRequestServiceImpl.loadAdAsync.ds");
            zzpk.zzc("Error fetching drt signals. Continuing.", e4);
        }
        try {
            JSONObject zza4 = zznd.zza(context, new zzna().zzf(zzmk).zza(zzA.get()).zza(zza3).zzc(location).zze(bundle4).zzaK(str).zzb(info).zzk(zza2).zzf(bundle).zzaL(str2).zzg(zznb.zzTm.zzj(context)));
            if (zza4 == null) {
                return new zzmn(0);
            }
            if (zzmk.versionCode < 7) {
                try {
                    zza4.put("request_id", uuid);
                } catch (JSONException e5) {
                }
            }
            final String jSONObject = zza4.toString();
            zzgl.zza(zzfB, "arc");
            final zzgj zzfB2 = zzgl.zzfB();
            final zzji zzji2 = zzji;
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzji.zzc zzgO = zzji.this.zzgO();
                    zzne.zzb(zzgO);
                    zzgl.zza(zzfB2, "rwc");
                    final zzgj zzfB = zzgl.zzfB();
                    zzgO.zza(new zzqp.zzc<zzjj>() {
                        /* renamed from: zzb */
                        public void zzd(zzjj zzjj) {
                            zzgl.zza(zzfB, "jsf");
                            zzgl.zzfC();
                            zzjj.zza("/invalidRequest", zzne.zzTN);
                            zzjj.zza("/loadAdURL", zzne.zzTO);
                            zzjj.zza("/loadAd", zzne.zzTP);
                            try {
                                zzjj.zzj("AFMA_getAd", jSONObject);
                            } catch (Exception e) {
                                zzpk.zzb("Error requesting an ad url", e);
                            }
                        }
                    }, new zzqp.zza(this) {
                        public void run() {
                        }
                    });
                }
            });
            try {
                zznh zznh = zzne.zzjw().get(10, TimeUnit.SECONDS);
                if (zznh == null) {
                    return new zzmn(0);
                }
                if (zznh.getErrorCode() != -2) {
                    zzmn zzmn = new zzmn(zznh.getErrorCode());
                    final zznb zznb3 = zznb;
                    final Context context3 = context;
                    final zzmk zzmk3 = zzmk;
                    zzpo.zzXC.post(new Runnable() {
                        public void run() {
                            zznb.this.zzTp.zza(r1, zzne, r2.zzvn);
                        }
                    });
                    return zzmn;
                }
                if (zzgl.zzfF() != null) {
                    zzgl.zza(zzgl.zzfF(), "rur");
                }
                zzmn zzmn2 = null;
                if (!TextUtils.isEmpty(zznh.zzjB())) {
                    zzmn2 = zznd.zza(context, zzmk, zznh.zzjB());
                }
                if (zzmn2 == null && !TextUtils.isEmpty(zznh.getUrl())) {
                    zzmn2 = zza(zzmk, context, zzmk.zzvn.zzba, zznh.getUrl(), str2, zznh, zzgl, zznb);
                }
                if (zzmn2 == null) {
                    zzmn2 = new zzmn(0);
                }
                zzgl.zza(zzfB, "tts");
                zzmn2.zzSA = zzgl.zzfD();
                final zznb zznb4 = zznb;
                final Context context4 = context;
                final zzmk zzmk4 = zzmk;
                zzpo.zzXC.post(new Runnable() {
                    public void run() {
                        zznb.this.zzTp.zza(r1, zzne, r2.zzvn);
                    }
                });
                return zzmn2;
            } catch (Exception e6) {
                return new zzmn(0);
            } finally {
                final zznb zznb5 = zznb;
                final Context context5 = context;
                final zzmk zzmk5 = zzmk;
                zzpo.zzXC.post(new Runnable() {
                    public void run() {
                        zznb.this.zzTp.zza(context5, zzne, zzmk5.zzvn);
                    }
                });
            }
        } catch (Throwable th) {
            zzw.zzcQ().zza(th, "AdRequestServiceImpl.loadAdAsync.di");
            zzpk.zzc("Error fetching device info. This is not recoverable.", th);
            return new zzmn(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a4, code lost:
        r6 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r4 = new java.io.InputStreamReader(r2.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r5 = com.google.android.gms.ads.internal.zzw.zzcM().zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        com.google.android.gms.common.util.zzp.zzb(r4);
        zza(r6, r12, r5, r9);
        r8.zzb(r6, r12, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c3, code lost:
        if (r19 == null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c5, code lost:
        r19.zza(r3, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        r3 = r8.zzj(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0110, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0111, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        com.google.android.gms.common.util.zzp.zzb(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0115, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0194, code lost:
        r3 = th;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:63:0x0112=Splitter:B:63:0x0112, B:54:0x0107=Splitter:B:54:0x0107} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzmn zza(com.google.android.gms.internal.zzmk r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.google.android.gms.internal.zznh r18, com.google.android.gms.internal.zzgl r19, com.google.android.gms.internal.zznb r20) {
        /*
            if (r19 == 0) goto L_0x00db
            com.google.android.gms.internal.zzgj r2 = r19.zzfB()
            r3 = r2
        L_0x0007:
            com.google.android.gms.internal.zznf r8 = new com.google.android.gms.internal.zznf     // Catch:{ IOException -> 0x00e6 }
            java.lang.String r2 = r18.zzjy()     // Catch:{ IOException -> 0x00e6 }
            r8.<init>(r13, r2)     // Catch:{ IOException -> 0x00e6 }
            java.lang.String r4 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r2 = java.lang.String.valueOf(r16)     // Catch:{ IOException -> 0x00e6 }
            int r5 = r2.length()     // Catch:{ IOException -> 0x00e6 }
            if (r5 == 0) goto L_0x00df
            java.lang.String r2 = r4.concat(r2)     // Catch:{ IOException -> 0x00e6 }
        L_0x0020:
            com.google.android.gms.internal.zzpk.zzbf(r2)     // Catch:{ IOException -> 0x00e6 }
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x00e6 }
            r0 = r16
            r4.<init>(r0)     // Catch:{ IOException -> 0x00e6 }
            r2 = 0
            com.google.android.gms.common.util.zze r5 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ IOException -> 0x00e6 }
            long r10 = r5.elapsedRealtime()     // Catch:{ IOException -> 0x00e6 }
            r6 = r2
            r7 = r4
        L_0x0035:
            java.net.URLConnection r2 = r7.openConnection()     // Catch:{ IOException -> 0x00e6 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x00e6 }
            com.google.android.gms.internal.zzpo r4 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x010b }
            r5 = 0
            r4.zza((android.content.Context) r14, (java.lang.String) r15, (boolean) r5, (java.net.HttpURLConnection) r2)     // Catch:{ all -> 0x010b }
            boolean r4 = android.text.TextUtils.isEmpty(r17)     // Catch:{ all -> 0x010b }
            if (r4 != 0) goto L_0x0056
            boolean r4 = r18.zzjA()     // Catch:{ all -> 0x010b }
            if (r4 == 0) goto L_0x0056
            java.lang.String r4 = "x-afma-drt-cookie"
            r0 = r17
            r2.addRequestProperty(r4, r0)     // Catch:{ all -> 0x010b }
        L_0x0056:
            java.lang.String r4 = r13.zzRW     // Catch:{ all -> 0x010b }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x010b }
            if (r5 != 0) goto L_0x0068
            java.lang.String r5 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.zzpk.zzbf(r5)     // Catch:{ all -> 0x010b }
            java.lang.String r5 = "Cookie"
            r2.addRequestProperty(r5, r4)     // Catch:{ all -> 0x010b }
        L_0x0068:
            if (r18 == 0) goto L_0x0094
            java.lang.String r4 = r18.zzjz()     // Catch:{ all -> 0x010b }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x010b }
            if (r4 != 0) goto L_0x0094
            r4 = 1
            r2.setDoOutput(r4)     // Catch:{ all -> 0x010b }
            java.lang.String r4 = r18.zzjz()     // Catch:{ all -> 0x010b }
            byte[] r9 = r4.getBytes()     // Catch:{ all -> 0x010b }
            int r4 = r9.length     // Catch:{ all -> 0x010b }
            r2.setFixedLengthStreamingMode(r4)     // Catch:{ all -> 0x010b }
            r5 = 0
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0105 }
            java.io.OutputStream r12 = r2.getOutputStream()     // Catch:{ all -> 0x0105 }
            r4.<init>(r12)     // Catch:{ all -> 0x0105 }
            r4.write(r9)     // Catch:{ all -> 0x0197 }
            com.google.android.gms.common.util.zzp.zzb(r4)     // Catch:{ all -> 0x010b }
        L_0x0094:
            int r9 = r2.getResponseCode()     // Catch:{ all -> 0x010b }
            java.util.Map r12 = r2.getHeaderFields()     // Catch:{ all -> 0x010b }
            r4 = 200(0xc8, float:2.8E-43)
            if (r9 < r4) goto L_0x0116
            r4 = 300(0x12c, float:4.2E-43)
            if (r9 >= r4) goto L_0x0116
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x010b }
            r5 = 0
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x0110 }
            java.io.InputStream r7 = r2.getInputStream()     // Catch:{ all -> 0x0110 }
            r4.<init>(r7)     // Catch:{ all -> 0x0110 }
            com.google.android.gms.internal.zzpo r5 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0194 }
            java.lang.String r5 = r5.zza((java.io.InputStreamReader) r4)     // Catch:{ all -> 0x0194 }
            com.google.android.gms.common.util.zzp.zzb(r4)     // Catch:{ all -> 0x010b }
            zza(r6, r12, r5, r9)     // Catch:{ all -> 0x010b }
            r8.zzb(r6, r12, r5)     // Catch:{ all -> 0x010b }
            if (r19 == 0) goto L_0x00d2
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x010b }
            r5 = 0
            java.lang.String r6 = "ufe"
            r4[r5] = r6     // Catch:{ all -> 0x010b }
            r0 = r19
            r0.zza(r3, r4)     // Catch:{ all -> 0x010b }
        L_0x00d2:
            com.google.android.gms.internal.zzmn r3 = r8.zzj(r10)     // Catch:{ all -> 0x010b }
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            r2 = r3
        L_0x00da:
            return r2
        L_0x00db:
            r2 = 0
            r3 = r2
            goto L_0x0007
        L_0x00df:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x00e6 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x00e6 }
            goto L_0x0020
        L_0x00e6:
            r2 = move-exception
            java.lang.String r3 = "Error while connecting to ad server: "
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r4 = r2.length()
            if (r4 == 0) goto L_0x018d
            java.lang.String r2 = r3.concat(r2)
        L_0x00fb:
            com.google.android.gms.internal.zzpk.zzbh(r2)
            com.google.android.gms.internal.zzmn r2 = new com.google.android.gms.internal.zzmn
            r3 = 2
            r2.<init>(r3)
            goto L_0x00da
        L_0x0105:
            r3 = move-exception
            r4 = r5
        L_0x0107:
            com.google.android.gms.common.util.zzp.zzb(r4)     // Catch:{ all -> 0x010b }
            throw r3     // Catch:{ all -> 0x010b }
        L_0x010b:
            r3 = move-exception
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            throw r3     // Catch:{ IOException -> 0x00e6 }
        L_0x0110:
            r3 = move-exception
            r4 = r5
        L_0x0112:
            com.google.android.gms.common.util.zzp.zzb(r4)     // Catch:{ all -> 0x010b }
            throw r3     // Catch:{ all -> 0x010b }
        L_0x0116:
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x010b }
            r5 = 0
            zza(r4, r12, r5, r9)     // Catch:{ all -> 0x010b }
            r4 = 300(0x12c, float:4.2E-43)
            if (r9 < r4) goto L_0x015d
            r4 = 400(0x190, float:5.6E-43)
            if (r9 >= r4) goto L_0x015d
            java.lang.String r4 = "Location"
            java.lang.String r4 = r2.getHeaderField(r4)     // Catch:{ all -> 0x010b }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x010b }
            if (r5 == 0) goto L_0x0142
            java.lang.String r3 = "No location header to follow redirect."
            com.google.android.gms.internal.zzpk.zzbh(r3)     // Catch:{ all -> 0x010b }
            com.google.android.gms.internal.zzmn r3 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x010b }
            r4 = 0
            r3.<init>(r4)     // Catch:{ all -> 0x010b }
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            r2 = r3
            goto L_0x00da
        L_0x0142:
            java.net.URL r5 = new java.net.URL     // Catch:{ all -> 0x010b }
            r5.<init>(r4)     // Catch:{ all -> 0x010b }
            int r4 = r6 + 1
            r6 = 5
            if (r4 <= r6) goto L_0x0181
            java.lang.String r3 = "Too many redirects."
            com.google.android.gms.internal.zzpk.zzbh(r3)     // Catch:{ all -> 0x010b }
            com.google.android.gms.internal.zzmn r3 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x010b }
            r4 = 0
            r3.<init>(r4)     // Catch:{ all -> 0x010b }
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            r2 = r3
            goto L_0x00da
        L_0x015d:
            r3 = 46
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010b }
            r4.<init>(r3)     // Catch:{ all -> 0x010b }
            java.lang.String r3 = "Received error HTTP response code: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x010b }
            java.lang.StringBuilder r3 = r3.append(r9)     // Catch:{ all -> 0x010b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x010b }
            com.google.android.gms.internal.zzpk.zzbh(r3)     // Catch:{ all -> 0x010b }
            com.google.android.gms.internal.zzmn r3 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x010b }
            r4 = 0
            r3.<init>(r4)     // Catch:{ all -> 0x010b }
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            r2 = r3
            goto L_0x00da
        L_0x0181:
            r8.zzk(r12)     // Catch:{ all -> 0x010b }
            r2.disconnect()     // Catch:{ IOException -> 0x00e6 }
            if (r20 == 0) goto L_0x0189
        L_0x0189:
            r6 = r4
            r7 = r5
            goto L_0x0035
        L_0x018d:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            goto L_0x00fb
        L_0x0194:
            r3 = move-exception
            goto L_0x0112
        L_0x0197:
            r3 = move-exception
            goto L_0x0107
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznc.zza(com.google.android.gms.internal.zzmk, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zznh, com.google.android.gms.internal.zzgl, com.google.android.gms.internal.zznb):com.google.android.gms.internal.zzmn");
    }

    public static zznc zza(Context context, zzfw zzfw, zznb zznb) {
        zznc zznc;
        synchronized (zztX) {
            if (zzTv == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzTv = new zznc(context, zzfw, zznb);
            }
            zznc = zzTv;
        }
        return zznc;
    }

    @Nullable
    private static <T> T zza(Future<T> future, Long l) {
        try {
            return future.get(l.longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzpk.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (RuntimeException | ExecutionException | TimeoutException e2) {
            zzpk.zzc("Exception caught while resolving future", e2);
            return null;
        }
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzpk.zzak(2)) {
            zzpk.m19v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String next : map.keySet()) {
                    zzpk.m19v(new StringBuilder(String.valueOf(next).length() + 5).append("    ").append(next).append(":").toString());
                    for (String valueOf : map.get(next)) {
                        String valueOf2 = String.valueOf(valueOf);
                        zzpk.m19v(valueOf2.length() != 0 ? "      ".concat(valueOf2) : new String("      "));
                    }
                }
            }
            zzpk.m19v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzpk.m19v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzpk.m19v("    null");
            }
            zzpk.m19v(new StringBuilder(34).append("  Response Code:\n    ").append(i).append("\n}").toString());
        }
    }

    public void zza(final zzmk zzmk, final zzmu zzmu) {
        zzw.zzcQ().zzc(this.mContext, zzmk.zzvn);
        zzpn.zza((Runnable) new Runnable() {
            public void run() {
                zzmn zzmn;
                try {
                    zzmn = zznc.this.zzd(zzmk);
                } catch (Exception e) {
                    zzw.zzcQ().zza((Throwable) e, "AdRequestServiceImpl.loadAdAsync");
                    zzpk.zzc("Could not fetch ad response due to an Exception.", e);
                    zzmn = null;
                }
                if (zzmn == null) {
                    zzmn = new zzmn(0);
                }
                try {
                    zzmu.zza(zzmn);
                } catch (RemoteException e2) {
                    zzpk.zzc("Fail to forward ad response.", e2);
                }
            }
        });
    }

    public zzmn zzd(zzmk zzmk) {
        return zza(this.mContext, this.zzTy, this.zzTx, this.zzTw, zzmk);
    }
}

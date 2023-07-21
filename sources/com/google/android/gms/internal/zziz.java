package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzni;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@zzme
public class zziz {
    private final LinkedList<zzja> zzJb = new LinkedList<>();
    @Nullable
    private zziw zzJc;
    private final Map<zzja, zzjb> zzyE = new HashMap();

    private static void zza(String str, zzja zzja) {
        if (zzpk.zzak(2)) {
            zzpk.m19v(String.format(str, new Object[]{zzja}));
        }
    }

    private String[] zzai(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private boolean zzaj(String str) {
        try {
            return Pattern.matches(zzgd.zzDj.get(), str);
        } catch (RuntimeException e) {
            zzw.zzcQ().zza((Throwable) e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    static String zzak(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            return matcher.matches() ? matcher.group(1) : str;
        } catch (RuntimeException e) {
            return str;
        }
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    private static void zzc(zzec zzec, String str) {
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzec.extras.putBoolean(str, true);
    }

    private String zzgw() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.zzJb.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((zzja) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    static Set<String> zzj(zzec zzec) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzec.extras.keySet());
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzec zzk(zzec zzec) {
        zzec zzn = zzn(zzec);
        zzc(zzn, "_skipMediation");
        return zzn;
    }

    static boolean zzl(zzec zzec) {
        return zzj(zzec).contains("_skipMediation");
    }

    static zzec zzm(zzec zzec) {
        zzec zzn = zzn(zzec);
        for (String str : zzgd.zzDf.get().split(",")) {
            zzc(zzn.zzzd, str);
            if (str.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zzc(zzn.extras, str.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzn;
    }

    static zzec zzn(zzec zzec) {
        Parcel obtain = Parcel.obtain();
        zzec.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzec createFromParcel = zzec.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        if (zzgd.zzCX.get().booleanValue()) {
            zzec.zzi(createFromParcel);
        }
        return createFromParcel;
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        while (this.zzJb.size() > 0) {
            zzja remove = this.zzJb.remove();
            zzjb zzjb = this.zzyE.get(remove);
            zza("Flushing interstitial queue for %s.", remove);
            while (zzjb.size() > 0) {
                zzjb.zzo((zzec) null).zzJh.zzcm();
            }
            this.zzyE.remove(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public void restore() {
        if (this.zzJc != null) {
            SharedPreferences sharedPreferences = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            try {
                HashMap hashMap = new HashMap();
                for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                    if (!((String) next.getKey()).equals("PoolKeys")) {
                        zzje zzal = zzje.zzal((String) next.getValue());
                        zzja zzja = new zzja(zzal.zzum, zzal.zzts, zzal.zzJf);
                        if (!this.zzyE.containsKey(zzja)) {
                            this.zzyE.put(zzja, new zzjb(zzal.zzum, zzal.zzts, zzal.zzJf));
                            hashMap.put(zzja.toString(), zzja);
                            zza("Restored interstitial queue for %s.", zzja);
                        }
                    }
                }
                for (String str : zzai(sharedPreferences.getString("PoolKeys", ""))) {
                    zzja zzja2 = (zzja) hashMap.get(str);
                    if (this.zzyE.containsKey(zzja2)) {
                        this.zzJb.add(zzja2);
                    }
                }
            } catch (IOException | RuntimeException e) {
                zzw.zzcQ().zza(e, "InterstitialAdPool.restore");
                zzpk.zzc("Malformed preferences value for InterstitialAdPool.", e);
                this.zzyE.clear();
                this.zzJb.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void save() {
        if (this.zzJc != null) {
            SharedPreferences.Editor edit = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Map.Entry next : this.zzyE.entrySet()) {
                zzja zzja = (zzja) next.getKey();
                zzjb zzjb = (zzjb) next.getValue();
                if (zzjb.zzgB()) {
                    edit.putString(zzja.toString(), new zzje(zzjb).zzgL());
                    zza("Saved interstitial queue for %s.", zzja);
                }
            }
            edit.putString("PoolKeys", zzgw());
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzjb.zza zza(zzec zzec, String str) {
        zzjb zzjb;
        if (zzaj(str)) {
            return null;
        }
        int i = new zzni.zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
        zzec zzm = zzm(zzec);
        String zzak = zzak(str);
        zzja zzja = new zzja(zzm, zzak, i);
        zzjb zzjb2 = this.zzyE.get(zzja);
        if (zzjb2 == null) {
            zza("Interstitial pool created at %s.", zzja);
            zzjb zzjb3 = new zzjb(zzm, zzak, i);
            this.zzyE.put(zzja, zzjb3);
            zzjb = zzjb3;
        } else {
            zzjb = zzjb2;
        }
        this.zzJb.remove(zzja);
        this.zzJb.add(zzja);
        zzjb.zzgA();
        while (this.zzJb.size() > zzgd.zzDg.get().intValue()) {
            zzja remove = this.zzJb.remove();
            zzjb zzjb4 = this.zzyE.get(remove);
            zza("Evicting interstitial queue for %s.", remove);
            while (zzjb4.size() > 0) {
                zzjb.zza zzo = zzjb4.zzo((zzec) null);
                if (zzo.zzJl) {
                    zzjc.zzgC().zzgE();
                }
                zzo.zzJh.zzcm();
            }
            this.zzyE.remove(remove);
        }
        while (zzjb.size() > 0) {
            zzjb.zza zzo2 = zzjb.zzo(zzm);
            if (!zzo2.zzJl || zzw.zzcS().currentTimeMillis() - zzo2.zzJk <= 1000 * ((long) zzgd.zzDi.get().intValue())) {
                String str2 = zzo2.zzJi != null ? " (inline) " : " ";
                zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), zzja);
                return zzo2;
            }
            zza("Expired interstitial at %s.", zzja);
            zzjc.zzgC().zzgD();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void zza(zziw zziw) {
        if (this.zzJc == null) {
            this.zzJc = zziw.zzgu();
            restore();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzec zzec, String str) {
        if (this.zzJc != null) {
            int i = new zzni.zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
            zzec zzm = zzm(zzec);
            String zzak = zzak(str);
            zzja zzja = new zzja(zzm, zzak, i);
            zzjb zzjb = this.zzyE.get(zzja);
            if (zzjb == null) {
                zza("Interstitial pool created at %s.", zzja);
                zzjb = new zzjb(zzm, zzak, i);
                this.zzyE.put(zzja, zzjb);
            }
            zzjb.zza(this.zzJc, zzec);
            zzjb.zzgA();
            zza("Inline entry added to the queue at %s.", zzja);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002e, code lost:
        r2 = r0.size();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzgv() {
        /*
            r9 = this;
            r8 = 2
            com.google.android.gms.internal.zziw r0 = r9.zzJc
            if (r0 != 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            java.util.Map<com.google.android.gms.internal.zzja, com.google.android.gms.internal.zzjb> r0 = r9.zzyE
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r4 = r0.iterator()
        L_0x0010:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0088
            java.lang.Object r0 = r4.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            com.google.android.gms.internal.zzja r1 = (com.google.android.gms.internal.zzja) r1
            java.lang.Object r0 = r0.getValue()
            com.google.android.gms.internal.zzjb r0 = (com.google.android.gms.internal.zzjb) r0
            boolean r2 = com.google.android.gms.internal.zzpk.zzak(r8)
            if (r2 == 0) goto L_0x0056
            int r2 = r0.size()
            int r3 = r0.zzgy()
            if (r3 >= r2) goto L_0x0056
            java.lang.String r5 = "Loading %s/%s pooled interstitials for %s."
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            int r3 = r2 - r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r6[r7] = r3
            r3 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6[r3] = r2
            r6[r8] = r1
            java.lang.String r2 = java.lang.String.format(r5, r6)
            com.google.android.gms.internal.zzpk.m19v(r2)
        L_0x0056:
            int r2 = r0.zzgz()
            int r2 = r2 + 0
            r3 = r2
        L_0x005d:
            int r5 = r0.size()
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzDh
            java.lang.Object r2 = r2.get()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r5 >= r2) goto L_0x0080
            java.lang.String r2 = "Pooling and loading one new interstitial for %s."
            zza((java.lang.String) r2, (com.google.android.gms.internal.zzja) r1)
            com.google.android.gms.internal.zziw r2 = r9.zzJc
            boolean r2 = r0.zzb((com.google.android.gms.internal.zziw) r2)
            if (r2 == 0) goto L_0x005d
            int r2 = r3 + 1
            r3 = r2
            goto L_0x005d
        L_0x0080:
            com.google.android.gms.internal.zzjc r0 = com.google.android.gms.internal.zzjc.zzgC()
            r0.zzE(r3)
            goto L_0x0010
        L_0x0088:
            r9.save()
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zziz.zzgv():void");
    }
}

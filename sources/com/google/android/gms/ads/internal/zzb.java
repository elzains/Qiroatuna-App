package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzd;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzg;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzadg;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzjc;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzmr;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpc;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzqm;
import com.google.android.gms.internal.zzqw;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;

@zzme
public abstract class zzb extends zza implements zzh, zzj, zzu, zzif, zzjs {
    protected transient boolean zzsA;
    protected final zzka zzsz;

    public zzb(Context context, zzeg zzeg, String str, zzka zzka, zzqh zzqh, zze zze) {
        this(new zzx(context, zzeg, str, zzqh), zzka, (zzt) null, zze);
    }

    protected zzb(zzx zzx, zzka zzka, @Nullable zzt zzt, zze zze) {
        super(zzx, zzt, zze);
        this.zzsz = zzka;
        this.zzsA = false;
    }

    private zzmk.zza zza(zzec zzec, Bundle bundle, zzpd zzpd) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.zzss.zzqn.getApplicationInfo();
        try {
            packageInfo = zzadg.zzbi(this.zzss.zzqn).getPackageInfo(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzss.zzqn.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzss.zzvo == null || this.zzss.zzvo.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzss.zzvo.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = this.zzss.zzvo.getWidth();
            int height = this.zzss.zzvo.getHeight();
            int i3 = 0;
            if (this.zzss.zzvo.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i);
            bundle2.putInt("y", i2);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i3);
        }
        String zzki = zzw.zzcQ().zzki();
        this.zzss.zzvu = new zzpc(zzki, this.zzss.zzvl);
        this.zzss.zzvu.zzs(zzec);
        String zza = zzw.zzcM().zza(this.zzss.zzqn, (View) this.zzss.zzvo, this.zzss.zzvr);
        long j = 0;
        if (this.zzss.zzvy != null) {
            try {
                j = this.zzss.zzvy.getValue();
            } catch (RemoteException e2) {
                zzpk.zzbh("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzw.zzcQ().zza(this.zzss.zzqn, this, zzki);
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= this.zzss.zzvE.size()) {
                break;
            }
            arrayList.add(this.zzss.zzvE.keyAt(i5));
            i4 = i5 + 1;
        }
        final boolean z = this.zzss.zzvz != null;
        final boolean z2 = this.zzss.zzvA != null && zzw.zzcQ().zzkz();
        final zzqm zza3 = zzpn.zza(new Callable<Boolean>() {
            /* renamed from: zzbX */
            public Boolean call() {
                return false;
            }
        });
        zzqm zza4 = zzpn.zza(new Callable<String>() {
            /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
                r0 = com.google.android.gms.ads.internal.zzw.zzcO().zzX(r3.zzsB.zzss.zzqn);
             */
            /* renamed from: zzbY */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String call() {
                /*
                    r3 = this;
                    java.lang.String r1 = ""
                    com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzFb
                    java.lang.Object r0 = r0.get()
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 == 0) goto L_0x0027
                    com.google.android.gms.internal.zzpp r0 = com.google.android.gms.ads.internal.zzw.zzcO()
                    com.google.android.gms.ads.internal.zzb r2 = com.google.android.gms.ads.internal.zzb.this
                    com.google.android.gms.ads.internal.zzx r2 = r2.zzss
                    android.content.Context r2 = r2.zzqn
                    android.webkit.CookieManager r0 = r0.zzX(r2)
                    if (r0 == 0) goto L_0x0027
                    java.lang.String r1 = "googleads.g.doubleclick.net"
                    java.lang.String r0 = r0.getCookie(r1)
                L_0x0026:
                    return r0
                L_0x0027:
                    r0 = r1
                    goto L_0x0026
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzb.C01842.call():java.lang.String");
            }
        });
        zzqm zza5 = zzpn.zza(new Callable<String>() {
            /* renamed from: zzbY */
            public String call() {
                return zzb.this.zzss.zzvm.zzT().zzb(zzb.this.zzss.zzqn);
            }
        });
        String str = null;
        if (zzpd != null) {
            str = zzpd.zzkd();
        }
        final zzqj zzqj = new zzqj();
        zza3.zzc(new Runnable(this) {
            public void run() {
                boolean z = false;
                try {
                    z = zza3.isDone() ? ((Boolean) zza3.get()).booleanValue() : false;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e2) {
                    zzpk.zzb("Error receiving app streaming support", e2);
                }
                zzqj.zzh(new zzmr(z, z2, z));
            }
        });
        return new zzmk.zza(bundle2, zzec, this.zzss.zzvr, this.zzss.zzvl, applicationInfo, packageInfo, zzki, zzw.zzcQ().getSessionId(), this.zzss.zzvn, zza2, this.zzss.zzvK, arrayList, bundle, zzw.zzcQ().zzkm(), displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.density, zza, j, uuid, zzgd.zzfs(), this.zzss.zzvk, this.zzss.zzvF, zzqj, this.zzss.zzdu(), zzw.zzcM().zzcq(), zzw.zzcM().zzcs(), zzw.zzcM().zzT(this.zzss.zzqn), zzw.zzcM().zzs(this.zzss.zzvo), this.zzss.zzqn instanceof Activity, zzw.zzcQ().zzkr(), zza4, str, zzw.zzcQ().zzkv(), zzw.zzdj().zzgr(), zzw.zzcM().zzkP(), zzw.zzcU().zzkY(), this.zzss.zzvH, zzw.zzcU().zzkZ(), zzjc.zzgC().asBundle(), zzw.zzcQ().zzm(this.zzss.zzqn, this.zzss.zzvl), zza5);
    }

    public String getMediationAdapterClassName() {
        if (this.zzss.zzvs == null) {
            return null;
        }
        return this.zzss.zzvs.zzLk;
    }

    public void onAdClicked() {
        if (this.zzss.zzvs == null) {
            zzpk.zzbh("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzss.zzvs.zzWc == null || this.zzss.zzvs.zzWc.zzKF == null)) {
            String zzF = zzw.zzdl().zzF(this.zzss.zzqn);
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, zza(zzF, this.zzss.zzvs.zzWc.zzKF));
            if (this.zzss.zzvs.zzWc.zzKF.size() > 0) {
                zzw.zzdl().zzf(this.zzss.zzqn, zzF);
            }
        }
        if (!(this.zzss.zzvs.zzLi == null || this.zzss.zzvs.zzLi.zzKs == null)) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, this.zzss.zzvs.zzLi.zzKs);
        }
        super.onAdClicked();
    }

    public void onPause() {
        this.zzsu.zzl(this.zzss.zzvs);
    }

    public void onResume() {
        this.zzsu.zzm(this.zzss.zzvs);
    }

    public void pause() {
        zzac.zzdj("pause must be called on the main UI thread.");
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null || !this.zzss.zzdq())) {
            zzw.zzcO().zzl(this.zzss.zzvs.zzNH);
        }
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLj == null)) {
            try {
                this.zzss.zzvs.zzLj.pause();
            } catch (RemoteException e) {
                zzpk.zzbh("Could not pause mediation adapter.");
            }
        }
        this.zzsu.zzl(this.zzss.zzvs);
        this.zzsr.pause();
    }

    public void recordImpression() {
        zza(this.zzss.zzvs, false);
    }

    public void resume() {
        zzac.zzdj("resume must be called on the main UI thread.");
        zzqw zzqw = null;
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null)) {
            zzqw = this.zzss.zzvs.zzNH;
        }
        if (zzqw != null && this.zzss.zzdq()) {
            zzw.zzcO().zzm(this.zzss.zzvs.zzNH);
        }
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLj == null)) {
            try {
                this.zzss.zzvs.zzLj.resume();
            } catch (RemoteException e) {
                zzpk.zzbh("Could not resume mediation adapter.");
            }
        }
        if (zzqw == null || !zzqw.zzlC()) {
            this.zzsr.resume();
        }
        this.zzsu.zzm(this.zzss.zzvs);
    }

    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }

    public void zza(zzle zzle) {
        zzac.zzdj("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzss.zzvz = zzle;
    }

    public void zza(zzli zzli, @Nullable String str) {
        zzac.zzdj("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzss.zzvL = new zzk(str);
        this.zzss.zzvA = zzli;
        if (!zzw.zzcQ().zzkl() && zzli != null) {
            new zzc(this.zzss.zzqn, this.zzss.zzvA, this.zzss.zzvL).zziP();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable zzpb zzpb, boolean z) {
        if (zzpb == null) {
            zzpk.zzbh("Ad state was null when trying to ping impression URLs.");
            return;
        }
        super.zzc(zzpb);
        if (!(zzpb.zzWc == null || zzpb.zzWc.zzKG == null)) {
            String zzF = zzw.zzdl().zzF(this.zzss.zzqn);
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, z, zza(zzF, zzpb.zzWc.zzKG));
            if (zzpb.zzWc.zzKG.size() > 0) {
                zzw.zzdl().zzg(this.zzss.zzqn, zzF);
            }
        }
        if (zzpb.zzLi != null && zzpb.zzLi.zzKt != null) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, z, zzpb.zzLi.zzKt);
        }
    }

    public void zza(String str, ArrayList<String> arrayList) {
        zzd zzd = new zzd(str, arrayList, this.zzss.zzqn, this.zzss.zzvn.zzba);
        if (this.zzss.zzvz == null) {
            zzpk.zzbh("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!zzel.zzeT().zzaf(this.zzss.zzqn)) {
                zzpk.zzbh("Google Play Service unavailable, cannot launch default purchase flow.");
            } else if (this.zzss.zzvA == null) {
                zzpk.zzbh("PlayStorePurchaseListener is not set.");
            } else if (this.zzss.zzvL == null) {
                zzpk.zzbh("PlayStorePurchaseVerifier is not initialized.");
            } else if (this.zzss.zzvP) {
                zzpk.zzbh("An in-app purchase request is already in progress, abort");
            } else {
                this.zzss.zzvP = true;
                try {
                    if (!this.zzss.zzvA.isValidPurchase(str)) {
                        this.zzss.zzvP = false;
                    } else {
                        zzw.zzda().zza(this.zzss.zzqn, this.zzss.zzvn.zzYY, new GInAppPurchaseManagerInfoParcel(this.zzss.zzqn, this.zzss.zzvL, (zzld) zzd, (zzj) this));
                    }
                } catch (RemoteException e) {
                    zzpk.zzbh("Could not start In-App purchase.");
                    this.zzss.zzvP = false;
                }
            }
        } else {
            try {
                this.zzss.zzvz.zza(zzd);
            } catch (RemoteException e2) {
                zzpk.zzbh("Could not start In-App purchase.");
            }
        }
    }

    public void zza(String str, boolean z, int i, final Intent intent, zzf zzf) {
        try {
            if (this.zzss.zzvA != null) {
                this.zzss.zzvA.zza(new zzg(this.zzss.zzqn, str, z, i, intent, zzf));
            }
        } catch (RemoteException e) {
            zzpk.zzbh("Fail to invoke PlayStorePurchaseListener.");
        }
        zzpo.zzXC.postDelayed(new Runnable() {
            public void run() {
                int zzd = zzw.zzda().zzd(intent);
                zzw.zzda();
                if (!(zzd != 0 || zzb.this.zzss.zzvs == null || zzb.this.zzss.zzvs.zzNH == null || zzb.this.zzss.zzvs.zzNH.zzlt() == null)) {
                    zzb.this.zzss.zzvs.zzNH.zzlt().close();
                }
                zzb.this.zzss.zzvP = false;
            }
        }, 500);
    }

    public boolean zza(zzec zzec, zzgl zzgl) {
        zzpd zzpd;
        if (!zzbM()) {
            return false;
        }
        Bundle zzV = zzw.zzcM().zzV(this.zzss.zzqn);
        this.zzsr.cancel();
        this.zzss.zzvO = 0;
        if (zzgd.zzEJ.get().booleanValue()) {
            zzpd = zzw.zzcQ().zzkw();
            zzw.zzdi().zza(this.zzss.zzqn, this.zzss.zzvn, this.zzss.zzvl, zzpd);
        } else {
            zzpd = null;
        }
        zzmk.zza zza = zza(zzec, zzV, zzpd);
        zzgl.zzh("seq_num", zza.zzRB);
        zzgl.zzh("request_id", zza.zzRL);
        zzgl.zzh("session_id", zza.zzRC);
        if (zza.zzRz != null) {
            zzgl.zzh("app_version", String.valueOf(zza.zzRz.versionCode));
        }
        this.zzss.zzvp = zzw.zzcI().zza(this.zzss.zzqn, zza, this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzec zzec, zzpb zzpb, boolean z) {
        if (!z && this.zzss.zzdq()) {
            if (zzpb.zzKL > 0) {
                this.zzsr.zza(zzec, zzpb.zzKL);
            } else if (zzpb.zzWc != null && zzpb.zzWc.zzKL > 0) {
                this.zzsr.zza(zzec, zzpb.zzWc.zzKL);
            } else if (!zzpb.zzSn && zzpb.errorCode == 2) {
                this.zzsr.zzh(zzec);
            }
        }
        return this.zzsr.zzcy();
    }

    /* access modifiers changed from: package-private */
    public boolean zza(zzpb zzpb) {
        zzec zzec;
        boolean z = false;
        if (this.zzst != null) {
            zzec = this.zzst;
            this.zzst = null;
        } else {
            zzec = zzpb.zzRy;
            if (zzec.extras != null) {
                z = zzec.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzec, zzpb, z);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzpb zzpb, zzpb zzpb2) {
        int i;
        int i2 = 0;
        if (!(zzpb == null || zzpb.zzLl == null)) {
            zzpb.zzLl.zza((zzjs) null);
        }
        if (zzpb2.zzLl != null) {
            zzpb2.zzLl.zza((zzjs) this);
        }
        if (zzpb2.zzWc != null) {
            i = zzpb2.zzWc.zzKS;
            i2 = zzpb2.zzWc.zzKT;
        } else {
            i = 0;
        }
        this.zzss.zzvM.zzk(i, i2);
        return true;
    }

    public void zzb(zzpb zzpb) {
        super.zzb(zzpb);
        if (zzpb.zzLi != null) {
            zzpk.zzbf("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzss.zzvo != null) {
                this.zzss.zzvo.zzdy();
            }
            zzpk.zzbf("Pinging network fill URLs.");
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, false, zzpb.zzLi.zzKu);
            if (!(zzpb.zzWc == null || zzpb.zzWc.zzKI == null || zzpb.zzWc.zzKI.size() <= 0)) {
                zzpk.zzbf("Pinging urls remotely");
                zzw.zzcM().zza(this.zzss.zzqn, zzpb.zzWc.zzKI);
            }
        } else {
            zzpk.zzbf("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzss.zzvo != null) {
                this.zzss.zzvo.zzdx();
            }
        }
        if (zzpb.errorCode == 3 && zzpb.zzWc != null && zzpb.zzWc.zzKH != null) {
            zzpk.zzbf("Pinging no fill URLs.");
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, false, zzpb.zzWc.zzKH);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzbM() {
        return zzw.zzcM().zze(this.zzss.zzqn, this.zzss.zzqn.getPackageName(), "android.permission.INTERNET") && zzw.zzcM().zzJ(this.zzss.zzqn);
    }

    public void zzbN() {
        this.zzsu.zzj(this.zzss.zzvs);
        this.zzsA = false;
        zzbG();
        this.zzss.zzvu.zzjX();
    }

    public void zzbO() {
        this.zzsA = true;
        zzbI();
    }

    public void zzbP() {
        onAdClicked();
    }

    public void zzbQ() {
        zzbN();
    }

    public void zzbR() {
        zzbD();
    }

    public void zzbS() {
        zzbO();
    }

    public void zzbT() {
        if (this.zzss.zzvs != null) {
            String str = this.zzss.zzvs.zzLk;
            zzpk.zzbh(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzss.zzvs, true);
        zzbJ();
    }

    public void zzbU() {
        recordImpression();
    }

    public void zzbV() {
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                zzb.this.zzsr.pause();
            }
        });
    }

    public void zzbW() {
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                zzb.this.zzsr.resume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean zzc(zzec zzec) {
        return super.zzc(zzec) && !this.zzsA;
    }
}

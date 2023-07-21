package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.p000v4.util.SimpleArrayMap;
import android.view.View;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzgs;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zzgw;
import com.google.android.gms.internal.zzgx;
import com.google.android.gms.internal.zzgy;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjt;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkb;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import java.util.List;

@zzme
public class zzs extends zzb {
    private zzqw zzug;

    public zzs(Context context, zze zze, zzeg zzeg, String str, zzka zzka, zzqh zzqh) {
        super(context, zzeg, str, zzka, zzqh, zze);
    }

    private static zzgv zza(zzke zzke) throws RemoteException {
        return new zzgv(zzke.getHeadline(), zzke.getImages(), zzke.getBody(), zzke.zzfQ() != null ? zzke.zzfQ() : null, zzke.getCallToAction(), zzke.getStarRating(), zzke.getStore(), zzke.getPrice(), (zzgs) null, zzke.getExtras(), zzke.zzbF(), (View) null);
    }

    private static zzgw zza(zzkf zzkf) throws RemoteException {
        return new zzgw(zzkf.getHeadline(), zzkf.getImages(), zzkf.getBody(), zzkf.zzfV() != null ? zzkf.zzfV() : null, zzkf.getCallToAction(), zzkf.getAdvertiser(), (zzgs) null, zzkf.getExtras(), zzkf.zzbF(), (View) null);
    }

    private void zza(final zzgv zzgv) {
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                try {
                    if (zzs.this.zzss.zzvB != null) {
                        zzs.this.zzss.zzvB.zza(zzgv);
                    }
                } catch (RemoteException e) {
                    zzpk.zzc("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zzgw zzgw) {
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                try {
                    if (zzs.this.zzss.zzvC != null) {
                        zzs.this.zzss.zzvC.zza(zzgw);
                    }
                } catch (RemoteException e) {
                    zzpk.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zzpb zzpb, final String str) {
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                try {
                    zzs.this.zzss.zzvE.get(str).zza((zzgx) zzpb.zzWi);
                } catch (RemoteException e) {
                    zzpk.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
                }
            }
        });
    }

    public String getAdUnitId() {
        return this.zzss.zzvl;
    }

    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public void zza(SimpleArrayMap<String, zzhs> simpleArrayMap) {
        zzac.zzdj("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzss.zzvE = simpleArrayMap;
    }

    public void zza(zzgp zzgp) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public void zza(zzgy zzgy) {
        if (this.zzug != null) {
            this.zzug.zzb(zzgy);
        }
    }

    public void zza(zzha zzha) {
        if (this.zzss.zzvs.zzWa != null) {
            zzw.zzcQ().zzkx().zza(this.zzss.zzvr, this.zzss.zzvs, zzha);
        }
    }

    public void zza(zzle zzle) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public void zza(final zzpb.zza zza, zzgl zzgl) {
        if (zza.zzvr != null) {
            this.zzss.zzvr = zza.zzvr;
        }
        if (zza.errorCode != -2) {
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzs.this.zzb(new zzpb(zza, (zzqw) null, (zzjq) null, (zzkb) null, (String) null, (zzjt) null, (zzha.zza) null, (String) null));
                }
            });
            return;
        }
        this.zzss.zzvO = 0;
        this.zzss.zzvq = zzw.zzcL().zza(this.zzss.zzqn, this, zza, this.zzss.zzvm, (zzqw) null, this.zzsz, this, zzgl);
        String valueOf = String.valueOf(this.zzss.zzvq.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
    }

    public boolean zza(zzec zzec, zzgl zzgl) {
        if (zzgd.zzEm.get().booleanValue() && zzgd.zzEn.get().booleanValue()) {
            zzlw zzlw = new zzlw(this.zzss.zzqn, this, this.zzss.zzvm, this.zzss.zzvn);
            zzlw.zziT();
            try {
                zzlw.zziU();
            } catch (Exception e) {
                zzpk.zzc("Initializing javascript failed", e);
                return false;
            }
        }
        return super.zza(zzec, zzgl);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzec zzec, zzpb zzpb, boolean z) {
        return this.zzsr.zzcy();
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzpb zzpb, zzpb zzpb2) {
        zzb((List<String>) null);
        if (!this.zzss.zzdq()) {
            throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
        }
        if (zzpb2.zzSn) {
            try {
                zzke zzhc = zzpb2.zzLj != null ? zzpb2.zzLj.zzhc() : null;
                zzkf zzhd = zzpb2.zzLj != null ? zzpb2.zzLj.zzhd() : null;
                if (zzhc == null || this.zzss.zzvB == null) {
                    if (zzhd != null) {
                        if (this.zzss.zzvC != null) {
                            zzgw zza = zza(zzhd);
                            zza.zzb(new zzgz(this.zzss.zzqn, this, this.zzss.zzvm, zzhd, (zzha.zza) zza));
                            zza(zza);
                        }
                    }
                    zzpk.zzbh("No matching mapper/listener for retrieved native ad template.");
                    zzh(0);
                    return false;
                }
                zzgv zza2 = zza(zzhc);
                zza2.zzb(new zzgz(this.zzss.zzqn, this, this.zzss.zzvm, zzhc, (zzha.zza) zza2));
                zza(zza2);
            } catch (RemoteException e) {
                zzpk.zzc("Failed to get native ad mapper", e);
            }
        } else {
            zzha.zza zza3 = zzpb2.zzWi;
            if ((zza3 instanceof zzgw) && this.zzss.zzvC != null) {
                zza((zzgw) zzpb2.zzWi);
            } else if ((zza3 instanceof zzgv) && this.zzss.zzvB != null) {
                zza((zzgv) zzpb2.zzWi);
            } else if (!(zza3 instanceof zzgx) || this.zzss.zzvE == null || this.zzss.zzvE.get(((zzgx) zza3).getCustomTemplateId()) == null) {
                zzpk.zzbh("No matching listener for retrieved native ad template.");
                zzh(0);
                return false;
            } else {
                zza(zzpb2, ((zzgx) zza3).getCustomTemplateId());
            }
        }
        return super.zza(zzpb, zzpb2);
    }

    public void zzb(SimpleArrayMap<String, zzhr> simpleArrayMap) {
        zzac.zzdj("setOnCustomClickListener must be called on the main UI thread.");
        this.zzss.zzvD = simpleArrayMap;
    }

    public void zzb(zzhc zzhc) {
        zzac.zzdj("setNativeAdOptions must be called on the main UI thread.");
        this.zzss.zzvF = zzhc;
    }

    public void zzb(zzhp zzhp) {
        zzac.zzdj("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzss.zzvB = zzhp;
    }

    public void zzb(zzhq zzhq) {
        zzac.zzdj("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzss.zzvC = zzhq;
    }

    public void zzb(@Nullable List<String> list) {
        zzac.zzdj("setNativeTemplates must be called on the main UI thread.");
        this.zzss.zzvK = list;
    }

    public void zzc(zzqw zzqw) {
        this.zzug = zzqw;
    }

    public void zzct() {
        if (this.zzss.zzvs == null || this.zzug == null) {
            zzpk.zzbh("Request to enable ActiveView before adState is available.");
        } else {
            zzw.zzcQ().zzkx().zza(this.zzss.zzvr, this.zzss.zzvs, this.zzug.getView(), (zzjj) this.zzug);
        }
    }

    public SimpleArrayMap<String, zzhs> zzcu() {
        zzac.zzdj("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzss.zzvE;
    }

    public void zzcv() {
        if (this.zzug != null) {
            this.zzug.destroy();
            this.zzug = null;
        }
    }

    public void zzcw() {
        if (this.zzug != null && this.zzug.zzlG() != null && this.zzss.zzvF != null && this.zzss.zzvF.zzHe != null) {
            this.zzug.zzlG().zzQ(this.zzss.zzvF.zzHe.zzAU);
        }
    }

    public boolean zzcx() {
        return this.zzss.zzvs != null && this.zzss.zzvs.zzSn && this.zzss.zzvs.zzWc != null && this.zzss.zzvs.zzWc.zzKR;
    }

    @Nullable
    public zzhr zzz(String str) {
        zzac.zzdj("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzss.zzvD.get(str);
    }
}

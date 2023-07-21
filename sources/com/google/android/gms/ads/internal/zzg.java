package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzfa;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import com.google.android.gms.internal.zzrb;

@zzme
public class zzg extends zzc implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private boolean zzsS;

    public class zza {
        public zza() {
        }

        public void onClick() {
            zzg.this.onAdClicked();
        }
    }

    public zzg(Context context, zzeg zzeg, String str, zzka zzka, zzqh zzqh, zze zze) {
        super(context, zzeg, str, zzka, zzqh, zze);
    }

    private zzeg zzb(zzpb.zza zza2) {
        AdSize zzeF;
        if (zza2.zzWm.zzzC) {
            return this.zzss.zzvr;
        }
        String str = zza2.zzWm.zzSq;
        if (str != null) {
            String[] split = str.split("[xX]");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            zzeF = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            zzeF = this.zzss.zzvr.zzeF();
        }
        return new zzeg(this.zzss.zzqn, zzeF);
    }

    private boolean zzb(@Nullable zzpb zzpb, zzpb zzpb2) {
        if (zzpb2.zzSn) {
            View zzg = zzp.zzg(zzpb2);
            if (zzg == null) {
                zzpk.zzbh("Could not get mediation view");
                return false;
            }
            View nextView = this.zzss.zzvo.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzqw) {
                    ((zzqw) nextView).destroy();
                }
                this.zzss.zzvo.removeView(nextView);
            }
            if (!zzp.zzh(zzpb2)) {
                try {
                    zzb(zzg);
                } catch (Throwable th) {
                    zzw.zzcQ().zza(th, "BannerAdManager.swapViews");
                    zzpk.zzc("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            }
        } else if (!(zzpb2.zzWe == null || zzpb2.zzNH == null)) {
            zzpb2.zzNH.zza(zzpb2.zzWe);
            this.zzss.zzvo.removeAllViews();
            this.zzss.zzvo.setMinimumWidth(zzpb2.zzWe.widthPixels);
            this.zzss.zzvo.setMinimumHeight(zzpb2.zzWe.heightPixels);
            zzb(zzpb2.zzNH.getView());
        }
        if (this.zzss.zzvo.getChildCount() > 1) {
            this.zzss.zzvo.showNext();
        }
        if (zzpb != null) {
            View nextView2 = this.zzss.zzvo.getNextView();
            if (nextView2 instanceof zzqw) {
                ((zzqw) nextView2).zza(this.zzss.zzqn, this.zzss.zzvr, this.zzsn);
            } else if (nextView2 != null) {
                this.zzss.zzvo.removeView(nextView2);
            }
            this.zzss.zzdp();
        }
        this.zzss.zzvo.setVisibility(0);
        return true;
    }

    private void zze(final zzpb zzpb) {
        zzt.zzzg();
        if (this.zzss.zzdq()) {
            if (zzpb.zzNH != null) {
                if (zzpb.zzWa != null) {
                    this.zzsu.zza(this.zzss.zzvr, zzpb);
                }
                final zzcy zzcy = new zzcy(this.zzss.zzqn, zzpb.zzNH.getView());
                if (zzw.zzdl().zzjR()) {
                    zzcy.zza((zzcy.zzb) new zzov(this.zzss.zzqn, this.zzss.zzvl));
                }
                if (zzpb.zzdD()) {
                    zzcy.zza((zzcy.zzb) zzpb.zzNH);
                } else {
                    zzpb.zzNH.zzlv().zza((zzqx.zzc) new zzqx.zzc(this) {
                        public void zzcf() {
                            zzcy.zza((zzcy.zzb) zzpb.zzNH);
                        }
                    });
                }
            }
        } else if (this.zzss.zzvN != null && zzpb.zzWa != null) {
            this.zzsu.zza(this.zzss.zzvr, zzpb, this.zzss.zzvN);
        }
    }

    public void onGlobalLayout() {
        zzf(this.zzss.zzvs);
    }

    public void onScrollChanged() {
        zzf(this.zzss.zzvs);
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzac.zzdj("setManualImpressionsEnabled must be called from the main thread.");
        this.zzsS = z;
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    /* access modifiers changed from: protected */
    public zzqw zza(zzpb.zza zza2, @Nullable zzf zzf, @Nullable zzot zzot) {
        if (this.zzss.zzvr.zzzA == null && this.zzss.zzvr.zzzC) {
            this.zzss.zzvr = zzb(zza2);
        }
        return super.zza(zza2, zzf, zzot);
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable zzpb zzpb, boolean z) {
        super.zza(zzpb, z);
        if (zzp.zzh(zzpb)) {
            zzp.zza(zzpb, new zza());
        }
    }

    public boolean zza(@Nullable zzpb zzpb, final zzpb zzpb2) {
        zzrb zzrb;
        if (!super.zza(zzpb, zzpb2)) {
            return false;
        }
        if (!this.zzss.zzdq() || zzb(zzpb, zzpb2)) {
            if (zzpb2.zzSF) {
                zzf(zzpb2);
                zzw.zzdk().zza((View) this.zzss.zzvo, (ViewTreeObserver.OnGlobalLayoutListener) this);
                zzw.zzdk().zza((View) this.zzss.zzvo, (ViewTreeObserver.OnScrollChangedListener) this);
                if (!zzpb2.zzWb) {
                    final C01951 r2 = new Runnable() {
                        public void run() {
                            zzg.this.zzf(zzg.this.zzss.zzvs);
                        }
                    };
                    zzqx zzlv = zzpb2.zzNH != null ? zzpb2.zzNH.zzlv() : null;
                    if (zzlv != null) {
                        zzlv.zza((zzqx.zze) new zzqx.zze(this) {
                            public void zzce() {
                                if (!zzpb2.zzWb) {
                                    zzw.zzcM();
                                    zzpo.zzb(r2);
                                }
                            }
                        });
                    }
                }
            } else if (!this.zzss.zzdr() || zzgd.zzEh.get().booleanValue()) {
                zza(zzpb2, false);
            }
            if (zzpb2.zzNH != null) {
                zzrb = zzpb2.zzNH.zzlG();
                zzqx zzlv2 = zzpb2.zzNH.zzlv();
                if (zzlv2 != null) {
                    zzlv2.zzlT();
                }
            } else {
                zzrb = null;
            }
            if (!(this.zzss.zzvG == null || zzrb == null)) {
                zzrb.zzQ(this.zzss.zzvG.zzAU);
            }
            zze(zzpb2);
            return true;
        }
        zzh(0);
        return false;
    }

    public boolean zzb(zzec zzec) {
        return super.zzb(zze(zzec));
    }

    @Nullable
    public zzfa zzbF() {
        zzac.zzdj("getVideoController must be called from the main thread.");
        if (this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null) {
            return null;
        }
        return this.zzss.zzvs.zzNH.zzlG();
    }

    /* access modifiers changed from: protected */
    public boolean zzbM() {
        boolean z = true;
        if (!zzw.zzcM().zze(this.zzss.zzqn, this.zzss.zzqn.getPackageName(), "android.permission.INTERNET")) {
            zzel.zzeT().zza(this.zzss.zzvo, this.zzss.zzvr, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        if (!zzw.zzcM().zzJ(this.zzss.zzqn)) {
            zzel.zzeT().zza(this.zzss.zzvo, this.zzss.zzvr, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzss.zzvo != null) {
            this.zzss.zzvo.setVisibility(0);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public zzec zze(zzec zzec) {
        if (zzec.zzyY == this.zzsS) {
            return zzec;
        }
        return new zzec(zzec.versionCode, zzec.zzyT, zzec.extras, zzec.zzyU, zzec.zzyV, zzec.zzyW, zzec.zzyX, zzec.zzyY || this.zzsS, zzec.zzyZ, zzec.zzza, zzec.zzzb, zzec.zzzc, zzec.zzzd, zzec.zzze, zzec.zzzf, zzec.zzzg, zzec.zzzh, zzec.zzzi);
    }

    /* access modifiers changed from: package-private */
    public void zzf(@Nullable zzpb zzpb) {
        if (zzpb != null && !zzpb.zzWb && this.zzss.zzvo != null && zzw.zzcM().zza((View) this.zzss.zzvo, this.zzss.zzqn) && this.zzss.zzvo.getGlobalVisibleRect(new Rect(), (Point) null)) {
            if (!(zzpb == null || zzpb.zzNH == null || zzpb.zzNH.zzlv() == null)) {
                zzpb.zzNH.zzlv().zza((zzqx.zze) null);
            }
            zza(zzpb, false);
            zzpb.zzWb = true;
        }
    }
}

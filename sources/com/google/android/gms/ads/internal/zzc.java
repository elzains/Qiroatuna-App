package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjt;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkb;
import com.google.android.gms.internal.zzkx;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import java.util.Map;

@zzme
public abstract class zzc extends zzb implements zzi, zzkx {
    public zzc(Context context, zzeg zzeg, String str, zzka zzka, zzqh zzqh, zze zze) {
        super(context, zzeg, str, zzka, zzqh, zze);
    }

    /* access modifiers changed from: protected */
    public zzqw zza(zzpb.zza zza, @Nullable zzf zzf, @Nullable zzot zzot) {
        zzqw zzqw = null;
        View nextView = this.zzss.zzvo.getNextView();
        if (nextView instanceof zzqw) {
            zzqw = (zzqw) nextView;
            if (zzgd.zzCI.get().booleanValue()) {
                zzpk.zzbf("Reusing webview...");
                zzqw.zza(this.zzss.zzqn, this.zzss.zzvr, this.zzsn);
            } else {
                zzqw.destroy();
                zzqw = null;
            }
        }
        if (zzqw == null) {
            if (nextView != null) {
                this.zzss.zzvo.removeView(nextView);
            }
            zzqw = zzw.zzcN().zza(this.zzss.zzqn, this.zzss.zzvr, false, false, this.zzss.zzvm, this.zzss.zzvn, this.zzsn, this, this.zzsv);
            if (this.zzss.zzvr.zzzA == null) {
                zzb(zzqw.getView());
            }
        }
        zzqw zzqw2 = zzqw;
        zzqw2.zzlv().zza(this, this, this, this, false, this, (zzih) null, zzf, this, zzot);
        zza((zzjj) zzqw2);
        zzqw2.zzbj(zza.zzTi.zzRL);
        return zzqw2;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzbI();
    }

    public void zza(zzgp zzgp) {
        zzac.zzdj("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzss.zzvI = zzgp;
    }

    /* access modifiers changed from: protected */
    public void zza(zzjj zzjj) {
        zzjj.zza("/trackActiveViewUnit", (zzid) new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                if (zzc.this.zzss.zzvs != null) {
                    zzc.this.zzsu.zza(zzc.this.zzss.zzvr, zzc.this.zzss.zzvs, zzqw.getView(), (zzjj) zzqw);
                } else {
                    zzpk.zzbh("Request to enable ActiveView before adState is available.");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(final zzpb.zza zza, final zzgl zzgl) {
        if (zza.errorCode != -2) {
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzc.this.zzb(new zzpb(zza, (zzqw) null, (zzjq) null, (zzkb) null, (String) null, (zzjt) null, (zzha.zza) null, (String) null));
                }
            });
            return;
        }
        if (zza.zzvr != null) {
            this.zzss.zzvr = zza.zzvr;
        }
        if (!zza.zzWm.zzSn || zza.zzWm.zzzD) {
            zzpo.zzXC.post(new Runnable((zzot) null) {
                public void run() {
                    if (zza.zzWm.zzSw && zzc.this.zzss.zzvI != null) {
                        String str = null;
                        if (zza.zzWm.zzNJ != null) {
                            str = zzw.zzcM().zzaX(zza.zzWm.zzNJ);
                        }
                        zzgm zzgm = new zzgm(zzc.this, str, zza.zzWm.body);
                        zzc.this.zzss.zzvO = 1;
                        try {
                            zzc.this.zzsq = false;
                            zzc.this.zzss.zzvI.zza(zzgm);
                            return;
                        } catch (RemoteException e) {
                            zzpk.zzc("Could not call the onCustomRenderedAdLoadedListener.", e);
                            zzc.this.zzsq = true;
                        }
                    }
                    final zzf zzf = new zzf(zzc.this.zzss.zzqn, zza);
                    zzqw zza = zzc.this.zza(zza, zzf, null);
                    zza.setOnTouchListener(new View.OnTouchListener(this) {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            zzf.recordClick();
                            return false;
                        }
                    });
                    zza.setOnClickListener(new View.OnClickListener(this) {
                        public void onClick(View view) {
                            zzf.recordClick();
                        }
                    });
                    zzc.this.zzss.zzvO = 0;
                    zzc.this.zzss.zzvq = zzw.zzcL().zza(zzc.this.zzss.zzqn, zzc.this, zza, zzc.this.zzss.zzvm, zza, zzc.this.zzsz, zzc.this, zzgl);
                }
            });
            return;
        }
        this.zzss.zzvO = 0;
        this.zzss.zzvq = zzw.zzcL().zza(this.zzss.zzqn, this, zza, this.zzss.zzvm, (zzqw) null, this.zzsz, this, zzgl);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzpb zzpb, zzpb zzpb2) {
        if (this.zzss.zzdq() && this.zzss.zzvo != null) {
            this.zzss.zzvo.zzdw().zzbb(zzpb2.zzSs);
        }
        return super.zza(zzpb, zzpb2);
    }

    public void zzbZ() {
        onAdClicked();
    }

    public void zzc(View view) {
        this.zzss.zzvN = view;
        zzb(new zzpb(this.zzss.zzvt, (zzqw) null, (zzjq) null, (zzkb) null, (String) null, (zzjt) null, (zzha.zza) null, (String) null));
    }

    public void zzca() {
        recordImpression();
        zzbE();
    }

    public void zzcb() {
        zzbG();
    }
}

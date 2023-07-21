package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqx;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzly {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzs zzGJ;
    private final zzaw zzGP;
    private final zzpb.zza zzPR;
    private ViewTreeObserver.OnGlobalLayoutListener zzQV;
    private ViewTreeObserver.OnScrollChangedListener zzQW;
    private final Object zzrJ = new Object();
    private final zzgl zzsn;
    private int zzvR = -1;
    private int zzvS = -1;
    private zzpz zzvT;

    public zzly(Context context, zzaw zzaw, zzpb.zza zza, zzgl zzgl, zzs zzs) {
        this.mContext = context;
        this.zzGP = zzaw;
        this.zzPR = zza;
        this.zzsn = zzgl;
        this.zzGJ = zzs;
        this.zzvT = new zzpz(200);
    }

    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener zza(final WeakReference<zzqw> weakReference) {
        if (this.zzQV == null) {
            this.zzQV = new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    zzly.this.zza((WeakReference<zzqw>) weakReference, false);
                }
            };
        }
        return this.zzQV;
    }

    /* access modifiers changed from: private */
    public void zza(WeakReference<zzqw> weakReference, boolean z) {
        zzqw zzqw;
        if (weakReference != null && (zzqw = (zzqw) weakReference.get()) != null && zzqw.getView() != null) {
            if (!z || this.zzvT.tryAcquire()) {
                int[] iArr = new int[2];
                zzqw.getView().getLocationOnScreen(iArr);
                int zzc = zzel.zzeT().zzc(this.mContext, iArr[0]);
                int zzc2 = zzel.zzeT().zzc(this.mContext, iArr[1]);
                synchronized (this.zzrJ) {
                    if (!(this.zzvR == zzc && this.zzvS == zzc2)) {
                        this.zzvR = zzc;
                        this.zzvS = zzc2;
                        zzqw.zzlv().zza(this.zzvR, this.zzvS, !z);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public ViewTreeObserver.OnScrollChangedListener zzb(final WeakReference<zzqw> weakReference) {
        if (this.zzQW == null) {
            this.zzQW = new ViewTreeObserver.OnScrollChangedListener() {
                public void onScrollChanged() {
                    zzly.this.zza((WeakReference<zzqw>) weakReference, true);
                }
            };
        }
        return this.zzQW;
    }

    /* access modifiers changed from: private */
    public void zzj(zzqw zzqw) {
        zzqx zzlv = zzqw.zzlv();
        zzlv.zza("/video", zzic.zzHQ);
        zzlv.zza("/videoMeta", zzic.zzHR);
        zzlv.zza("/precache", zzic.zzHT);
        zzlv.zza("/delayPageLoaded", zzic.zzHW);
        zzlv.zza("/instrument", zzic.zzHU);
        zzlv.zza("/log", zzic.zzHL);
        zzlv.zza("/videoClicked", zzic.zzHM);
        zzlv.zza("/trackActiveViewUnit", (zzid) new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                zzly.this.zzGJ.zzct();
            }
        });
    }

    public zzqm<zzqw> zze(final JSONObject jSONObject) {
        final zzqj zzqj = new zzqj();
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    final zzqw zzjj = zzly.this.zzjj();
                    zzly.this.zzGJ.zzc(zzjj);
                    WeakReference weakReference = new WeakReference(zzjj);
                    zzjj.zzlv().zza(zzly.this.zza((WeakReference<zzqw>) weakReference), zzly.this.zzb(weakReference));
                    zzly.this.zzj(zzjj);
                    zzjj.zzlv().zza((zzqx.zzb) new zzqx.zzb() {
                        public void zzk(zzqw zzqw) {
                            zzjj.zza("google.afma.nativeAds.renderVideo", jSONObject);
                        }
                    });
                    zzjj.zzlv().zza((zzqx.zza) new zzqx.zza() {
                        public void zza(zzqw zzqw, boolean z) {
                            zzly.this.zzGJ.zzcw();
                            zzqj.zzh(zzqw);
                        }
                    });
                    zzjj.loadUrl(zzgd.zzEl.get());
                } catch (Exception e) {
                    zzpk.zzc("Exception occurred while getting video view", e);
                    zzqj.zzh(null);
                }
            }
        });
        return zzqj;
    }

    /* access modifiers changed from: package-private */
    public zzqw zzjj() {
        return zzw.zzcN().zza(this.mContext, zzeg.zzk(this.mContext), false, false, this.zzGP, this.zzPR.zzTi.zzvn, this.zzsn, (zzu) null, this.zzGJ.zzby());
    }
}

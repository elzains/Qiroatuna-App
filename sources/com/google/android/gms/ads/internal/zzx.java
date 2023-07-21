package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.p000v4.util.SimpleArrayMap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ViewSwitcher;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.internal.zzaw;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpc;
import com.google.android.gms.internal.zzph;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@zzme
public final class zzx implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    public final Context zzqn;
    boolean zztK;
    @Nullable
    zzli zzvA;
    @Nullable
    zzhp zzvB;
    @Nullable
    zzhq zzvC;
    SimpleArrayMap<String, zzhr> zzvD;
    SimpleArrayMap<String, zzhs> zzvE;
    zzhc zzvF;
    @Nullable
    zzft zzvG;
    @Nullable
    zzfc zzvH;
    @Nullable
    zzgp zzvI;
    @Nullable
    zznw zzvJ;
    @Nullable
    List<String> zzvK;
    @Nullable
    zzk zzvL;
    @Nullable
    public zzph zzvM;
    @Nullable
    View zzvN;
    public int zzvO;
    boolean zzvP;
    private HashSet<zzpc> zzvQ;
    private int zzvR;
    private int zzvS;
    private zzpz zzvT;
    private boolean zzvU;
    private boolean zzvV;
    private boolean zzvW;
    final String zzvk;
    public String zzvl;
    final zzaw zzvm;
    public final zzqh zzvn;
    @Nullable
    zza zzvo;
    @Nullable
    public zzpj zzvp;
    @Nullable
    public zzpq zzvq;
    public zzeg zzvr;
    @Nullable
    public zzpb zzvs;
    public zzpb.zza zzvt;
    @Nullable
    public zzpc zzvu;
    @Nullable
    zzeo zzvv;
    @Nullable
    zzep zzvw;
    @Nullable
    zzev zzvx;
    @Nullable
    zzex zzvy;
    @Nullable
    zzle zzvz;

    public static class zza extends ViewSwitcher {
        private final zzpr zzvX;
        @Nullable
        private final zzqd zzvY;
        private boolean zzvZ = true;

        public zza(Context context, String str, String str2, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
            super(context);
            this.zzvX = new zzpr(context);
            this.zzvX.setAdUnitId(str);
            this.zzvX.zzba(str2);
            if (context instanceof Activity) {
                this.zzvY = new zzqd((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
            } else {
                this.zzvY = new zzqd((Activity) null, this, onGlobalLayoutListener, onScrollChangedListener);
            }
            this.zzvY.zzle();
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.zzvY != null) {
                this.zzvY.onAttachedToWindow();
            }
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.zzvY != null) {
                this.zzvY.onDetachedFromWindow();
            }
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!this.zzvZ) {
                return false;
            }
            this.zzvX.zzg(motionEvent);
            return false;
        }

        public void removeAllViews() {
            ArrayList<zzqw> arrayList = new ArrayList<>();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= getChildCount()) {
                    break;
                }
                View childAt = getChildAt(i2);
                if (childAt != null && (childAt instanceof zzqw)) {
                    arrayList.add((zzqw) childAt);
                }
                i = i2 + 1;
            }
            super.removeAllViews();
            for (zzqw destroy : arrayList) {
                destroy.destroy();
            }
        }

        public void zzds() {
            zzpk.m19v("Disable position monitoring on adFrame.");
            if (this.zzvY != null) {
                this.zzvY.zzlf();
            }
        }

        public zzpr zzdw() {
            return this.zzvX;
        }

        public void zzdx() {
            zzpk.m19v("Enable debug gesture detector on adFrame.");
            this.zzvZ = true;
        }

        public void zzdy() {
            zzpk.m19v("Disable debug gesture detector on adFrame.");
            this.zzvZ = false;
        }
    }

    public zzx(Context context, zzeg zzeg, String str, zzqh zzqh) {
        this(context, zzeg, str, zzqh, (zzaw) null);
    }

    zzx(Context context, zzeg zzeg, String str, zzqh zzqh, zzaw zzaw) {
        this.zzvM = null;
        this.zzvN = null;
        this.zzvO = 0;
        this.zzvP = false;
        this.zztK = false;
        this.zzvQ = null;
        this.zzvR = -1;
        this.zzvS = -1;
        this.zzvU = true;
        this.zzvV = true;
        this.zzvW = false;
        zzgd.initialize(context);
        if (zzw.zzcQ().zzkk() != null) {
            List<String> zzft = zzgd.zzft();
            if (zzqh.zzYW != 0) {
                zzft.add(Integer.toString(zzqh.zzYW));
            }
            zzw.zzcQ().zzkk().zzc(zzft);
        }
        this.zzvk = UUID.randomUUID().toString();
        if (zzeg.zzzz || zzeg.zzzB) {
            this.zzvo = null;
        } else {
            this.zzvo = new zza(context, str, zzqh.zzba, this, this);
            this.zzvo.setMinimumWidth(zzeg.widthPixels);
            this.zzvo.setMinimumHeight(zzeg.heightPixels);
            this.zzvo.setVisibility(4);
        }
        this.zzvr = zzeg;
        this.zzvl = str;
        this.zzqn = context;
        this.zzvn = zzqh;
        this.zzvm = zzaw == null ? new zzaw(new zzj(this)) : zzaw;
        this.zzvT = new zzpz(200);
        this.zzvE = new SimpleArrayMap<>();
    }

    private void zzdt() {
        View findViewById;
        if (this.zzvo != null && (findViewById = this.zzvo.getRootView().findViewById(16908290)) != null) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            this.zzvo.getGlobalVisibleRect(rect);
            findViewById.getGlobalVisibleRect(rect2);
            if (rect.top != rect2.top) {
                this.zzvU = false;
            }
            if (rect.bottom != rect2.bottom) {
                this.zzvV = false;
            }
        }
    }

    private void zzh(boolean z) {
        boolean z2 = true;
        if (this.zzvo != null && this.zzvs != null && this.zzvs.zzNH != null && this.zzvs.zzNH.zzlv() != null) {
            if (!z || this.zzvT.tryAcquire()) {
                if (this.zzvs.zzNH.zzlv().zzdD()) {
                    int[] iArr = new int[2];
                    this.zzvo.getLocationOnScreen(iArr);
                    int zzc = zzel.zzeT().zzc(this.zzqn, iArr[0]);
                    int zzc2 = zzel.zzeT().zzc(this.zzqn, iArr[1]);
                    if (!(zzc == this.zzvR && zzc2 == this.zzvS)) {
                        this.zzvR = zzc;
                        this.zzvS = zzc2;
                        zzqx zzlv = this.zzvs.zzNH.zzlv();
                        int i = this.zzvR;
                        int i2 = this.zzvS;
                        if (z) {
                            z2 = false;
                        }
                        zzlv.zza(i, i2, z2);
                    }
                }
                zzdt();
            }
        }
    }

    public void destroy() {
        zzds();
        this.zzvw = null;
        this.zzvx = null;
        this.zzvA = null;
        this.zzvz = null;
        this.zzvI = null;
        this.zzvy = null;
        zzi(false);
        if (this.zzvo != null) {
            this.zzvo.removeAllViews();
        }
        zzdn();
        zzdp();
        this.zzvs = null;
    }

    public void onGlobalLayout() {
        zzh(false);
    }

    public void onScrollChanged() {
        zzh(true);
        this.zzvW = true;
    }

    public void zza(HashSet<zzpc> hashSet) {
        this.zzvQ = hashSet;
    }

    public HashSet<zzpc> zzdm() {
        return this.zzvQ;
    }

    public void zzdn() {
        if (this.zzvs != null && this.zzvs.zzNH != null) {
            this.zzvs.zzNH.destroy();
        }
    }

    public void zzdo() {
        if (this.zzvs != null && this.zzvs.zzNH != null) {
            this.zzvs.zzNH.stopLoading();
        }
    }

    public void zzdp() {
        if (this.zzvs != null && this.zzvs.zzLj != null) {
            try {
                this.zzvs.zzLj.destroy();
            } catch (RemoteException e) {
                zzpk.zzbh("Could not destroy mediation adapter.");
            }
        }
    }

    public boolean zzdq() {
        return this.zzvO == 0;
    }

    public boolean zzdr() {
        return this.zzvO == 1;
    }

    public void zzds() {
        if (this.zzvo != null) {
            this.zzvo.zzds();
        }
    }

    public String zzdu() {
        return (!this.zzvU || !this.zzvV) ? this.zzvU ? this.zzvW ? "top-scrollable" : "top-locked" : this.zzvV ? this.zzvW ? "bottom-scrollable" : "bottom-locked" : "" : "";
    }

    public void zzdv() {
        if (this.zzvu != null) {
            if (this.zzvs != null) {
                this.zzvu.zzm(this.zzvs.zzWg);
                this.zzvu.zzn(this.zzvs.zzWh);
                this.zzvu.zzE(this.zzvs.zzSn);
            }
            this.zzvu.zzD(this.zzvr.zzzz);
        }
    }

    public void zzi(boolean z) {
        if (this.zzvO == 0) {
            zzdo();
        }
        if (this.zzvp != null) {
            this.zzvp.cancel();
        }
        if (this.zzvq != null) {
            this.zzvq.cancel();
        }
        if (z) {
            this.zzvs = null;
        }
    }
}

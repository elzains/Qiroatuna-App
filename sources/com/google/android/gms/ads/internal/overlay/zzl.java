package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqw;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.globalization.Globalization;

@zzme
public class zzl extends FrameLayout implements zzi {
    private final zzqw zzIs;
    private String zzIx;
    private final FrameLayout zzNT;
    private final zzgl zzNU;
    private final zzaa zzNV;
    private final long zzNW;
    @Nullable
    private zzj zzNX;
    private boolean zzNY;
    private boolean zzNZ;
    private boolean zzOa;
    private boolean zzOb;
    private long zzOc;
    private long zzOd;
    private Bitmap zzOe;
    private ImageView zzOf;
    private boolean zzOg;

    public zzl(Context context, zzqw zzqw, int i, boolean z, zzgl zzgl) {
        super(context);
        this.zzIs = zzqw;
        this.zzNU = zzgl;
        this.zzNT = new FrameLayout(context);
        addView(this.zzNT, new FrameLayout.LayoutParams(-1, -1));
        zzc.zzt(zzqw.zzby());
        this.zzNX = zzqw.zzby().zzsN.zza(context, zzqw, i, z, zzgl);
        if (this.zzNX != null) {
            this.zzNT.addView(this.zzNX, new FrameLayout.LayoutParams(-1, -1, 17));
            if (zzgd.zzBG.get().booleanValue()) {
                zzic();
            }
        }
        this.zzOf = new ImageView(context);
        this.zzNW = zzgd.zzBK.get().longValue();
        this.zzOb = zzgd.zzBI.get().booleanValue();
        if (this.zzNU != null) {
            this.zzNU.zzh("spinner_used", this.zzOb ? "1" : "0");
        }
        this.zzNV = new zzaa(this);
        if (this.zzNX != null) {
            this.zzNX.zza(this);
        }
        if (this.zzNX == null) {
            zzl("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    /* access modifiers changed from: private */
    public void zza(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        int length = strArr.length;
        int i = 0;
        String str2 = null;
        while (i < length) {
            String str3 = strArr[i];
            if (str2 != null) {
                hashMap.put(str2, str3);
                str3 = null;
            }
            i++;
            str2 = str3;
        }
        this.zzIs.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    private void zzh(int i, int i2) {
        if (this.zzOb) {
            int max = Math.max(i / zzgd.zzBJ.get().intValue(), 1);
            int max2 = Math.max(i2 / zzgd.zzBJ.get().intValue(), 1);
            if (this.zzOe == null || this.zzOe.getWidth() != max || this.zzOe.getHeight() != max2) {
                this.zzOe = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzOg = false;
            }
        }
    }

    public static void zzi(zzqw zzqw) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzqw.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    @TargetApi(14)
    private void zzie() {
        if (this.zzOe != null) {
            long elapsedRealtime = zzw.zzcS().elapsedRealtime();
            if (this.zzNX.getBitmap(this.zzOe) != null) {
                this.zzOg = true;
            }
            long elapsedRealtime2 = zzw.zzcS().elapsedRealtime() - elapsedRealtime;
            if (zzpk.zzkI()) {
                zzpk.m19v(new StringBuilder(46).append("Spinner frame grab took ").append(elapsedRealtime2).append("ms").toString());
            }
            if (elapsedRealtime2 > this.zzNW) {
                zzpk.zzbh("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzOb = false;
                this.zzOe = null;
                if (this.zzNU != null) {
                    this.zzNU.zzh("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    private void zzif() {
        if (this.zzOg && this.zzOe != null && !zzih()) {
            this.zzOf.setImageBitmap(this.zzOe);
            this.zzOf.invalidate();
            this.zzNT.addView(this.zzOf, new FrameLayout.LayoutParams(-1, -1));
            this.zzNT.bringChildToFront(this.zzOf);
        }
    }

    private void zzig() {
        if (zzih()) {
            this.zzNT.removeView(this.zzOf);
        }
    }

    private boolean zzih() {
        return this.zzOf.getParent() != null;
    }

    private void zzii() {
        if (this.zzIs.zzlr() != null && !this.zzNZ) {
            this.zzOa = (this.zzIs.zzlr().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzOa) {
                this.zzIs.zzlr().getWindow().addFlags(128);
                this.zzNZ = true;
            }
        }
    }

    private void zzij() {
        if (this.zzIs.zzlr() != null && this.zzNZ && !this.zzOa) {
            this.zzIs.zzlr().getWindow().clearFlags(128);
            this.zzNZ = false;
        }
    }

    public void destroy() {
        this.zzNV.pause();
        if (this.zzNX != null) {
            this.zzNX.stop();
        }
        zzij();
    }

    public void onPaused() {
        zza("pause", new String[0]);
        zzij();
        this.zzNY = false;
    }

    public void pause() {
        if (this.zzNX != null) {
            this.zzNX.pause();
        }
    }

    public void play() {
        if (this.zzNX != null) {
            this.zzNX.play();
        }
    }

    public void seekTo(int i) {
        if (this.zzNX != null) {
            this.zzNX.seekTo(i);
        }
    }

    public void zza(float f, float f2) {
        if (this.zzNX != null) {
            this.zzNX.zza(f, f2);
        }
    }

    public void zzaC(String str) {
        this.zzIx = str;
    }

    public void zzb(float f) {
        if (this.zzNX != null) {
            this.zzNX.zzb(f);
        }
    }

    public void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzNT.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    @TargetApi(14)
    public void zzf(MotionEvent motionEvent) {
        if (this.zzNX != null) {
            this.zzNX.dispatchTouchEvent(motionEvent);
        }
    }

    public void zzg(int i, int i2) {
        zzh(i, i2);
    }

    public void zzhT() {
        this.zzNV.resume();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzl.this.zza("surfaceCreated", new String[0]);
            }
        });
    }

    public void zzhU() {
        if (this.zzNX != null && this.zzOd == 0) {
            zza("canplaythrough", "duration", String.valueOf(((float) this.zzNX.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzNX.getVideoWidth()), "videoHeight", String.valueOf(this.zzNX.getVideoHeight()));
        }
    }

    public void zzhV() {
        zzii();
        this.zzNY = true;
    }

    public void zzhW() {
        zza("ended", new String[0]);
        zzij();
    }

    public void zzhX() {
        zzif();
        this.zzNV.pause();
        this.zzOd = this.zzOc;
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzl.this.zza("surfaceDestroyed", new String[0]);
            }
        });
    }

    public void zzhY() {
        if (this.zzNY) {
            zzig();
        }
        zzie();
    }

    public void zzhZ() {
        if (this.zzNX != null) {
            this.zzNX.zzhZ();
        }
    }

    public void zzia() {
        if (this.zzNX != null) {
            this.zzNX.zzia();
        }
    }

    public void zzib() {
        if (this.zzNX != null) {
            if (!TextUtils.isEmpty(this.zzIx)) {
                this.zzNX.setVideoPath(this.zzIx);
            } else {
                zza("no_src", new String[0]);
            }
        }
    }

    @TargetApi(14)
    public void zzic() {
        if (this.zzNX != null) {
            TextView textView = new TextView(this.zzNX.getContext());
            String valueOf = String.valueOf(this.zzNX.zzhy());
            textView.setText(valueOf.length() != 0 ? "AdMob - ".concat(valueOf) : new String("AdMob - "));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzNT.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzNT.bringChildToFront(textView);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzid() {
        if (this.zzNX != null) {
            long currentPosition = (long) this.zzNX.getCurrentPosition();
            if (this.zzOc != currentPosition && currentPosition > 0) {
                zza("timeupdate", Globalization.TIME, String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzOc = currentPosition;
            }
        }
    }

    public void zzl(String str, @Nullable String str2) {
        zza("error", "what", str, "extra", str2);
    }
}

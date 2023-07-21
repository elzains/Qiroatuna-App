package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.p000v4.util.SimpleArrayMap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzhn;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzgx extends zzhn.zza implements zzha.zza {
    private zzha zzGA;
    private final String zzGD;
    private final SimpleArrayMap<String, zzgu> zzGE;
    private final SimpleArrayMap<String, String> zzGF;
    private final zzgs zzGx;
    @Nullable
    private zzfa zzGy;
    @Nullable
    private View zzGz;
    private final Object zzrJ = new Object();

    public zzgx(String str, SimpleArrayMap<String, zzgu> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, zzgs zzgs, zzfa zzfa, View view) {
        this.zzGD = str;
        this.zzGE = simpleArrayMap;
        this.zzGF = simpleArrayMap2;
        this.zzGx = zzgs;
        this.zzGy = zzfa;
        this.zzGz = view;
    }

    public void destroy() {
        this.zzGA = null;
        this.zzGy = null;
        this.zzGz = null;
    }

    public List<String> getAvailableAssetNames() {
        int i = 0;
        String[] strArr = new String[(this.zzGE.size() + this.zzGF.size())];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzGE.size(); i3++) {
            strArr[i2] = this.zzGE.keyAt(i3);
            i2++;
        }
        while (i < this.zzGF.size()) {
            strArr[i2] = this.zzGF.keyAt(i);
            i++;
            i2++;
        }
        return Arrays.asList(strArr);
    }

    public String getCustomTemplateId() {
        return this.zzGD;
    }

    public void performClick(String str) {
        synchronized (this.zzrJ) {
            if (this.zzGA == null) {
                zzqf.m20e("Attempt to call performClick before ad initialized.");
            } else {
                this.zzGA.zza((View) null, str, (JSONObject) null, (Map<String, WeakReference<View>>) null, (View) null);
            }
        }
    }

    public void recordImpression() {
        synchronized (this.zzrJ) {
            if (this.zzGA == null) {
                zzqf.m20e("Attempt to perform recordImpression before ad initialized.");
            } else {
                this.zzGA.zzb((View) null, (Map<String, WeakReference<View>>) null);
            }
        }
    }

    public String zzY(String str) {
        return this.zzGF.get(str);
    }

    public zzhf zzZ(String str) {
        return this.zzGE.get(str);
    }

    public void zzb(zzha zzha) {
        synchronized (this.zzrJ) {
            this.zzGA = zzha;
        }
    }

    public zzfa zzbF() {
        return this.zzGy;
    }

    public String zzfS() {
        return "3";
    }

    public zzgs zzfT() {
        return this.zzGx;
    }

    public View zzfU() {
        return this.zzGz;
    }

    public IObjectWrapper zzfW() {
        return zzd.zzA(this.zzGA.getContext().getApplicationContext());
    }

    public boolean zzj(IObjectWrapper iObjectWrapper) {
        if (this.zzGA == null) {
            zzqf.m20e("Attempt to call renderVideoInMediaView before ad initialized.");
            return false;
        } else if (this.zzGz == null) {
            return false;
        } else {
            C03651 r1 = new zzgy() {
                public void zzc(MotionEvent motionEvent) {
                }

                public void zzfX() {
                    zzgx.this.performClick(NativeCustomTemplateAd.ASSET_NAME_VIDEO);
                }
            };
            this.zzGA.zza((View) (FrameLayout) zzd.zzF(iObjectWrapper), (zzgy) r1);
            return true;
        }
    }
}

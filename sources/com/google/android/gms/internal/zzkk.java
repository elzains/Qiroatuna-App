package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzkf;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzkk extends zzkf.zza {
    private final NativeContentAdMapper zzLH;

    public zzkk(NativeContentAdMapper nativeContentAdMapper) {
        this.zzLH = nativeContentAdMapper;
    }

    public String getAdvertiser() {
        return this.zzLH.getAdvertiser();
    }

    public String getBody() {
        return this.zzLH.getBody();
    }

    public String getCallToAction() {
        return this.zzLH.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzLH.getExtras();
    }

    public String getHeadline() {
        return this.zzLH.getHeadline();
    }

    public List getImages() {
        List<NativeAd.Image> images = this.zzLH.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image next : images) {
            arrayList.add(new zzgu(next.getDrawable(), next.getUri(), next.getScale()));
        }
        return arrayList;
    }

    public boolean getOverrideClickHandling() {
        return this.zzLH.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzLH.getOverrideImpressionRecording();
    }

    public void recordImpression() {
        this.zzLH.recordImpression();
    }

    public zzfa zzbF() {
        if (this.zzLH.getVideoController() != null) {
            return this.zzLH.getVideoController().zzbs();
        }
        return null;
    }

    public zzhf zzfV() {
        NativeAd.Image logo = this.zzLH.getLogo();
        if (logo != null) {
            return new zzgu(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    public IObjectWrapper zzhh() {
        View adChoicesContent = this.zzLH.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return zzd.zzA(adChoicesContent);
    }

    public void zzl(IObjectWrapper iObjectWrapper) {
        this.zzLH.handleClick((View) zzd.zzF(iObjectWrapper));
    }

    public void zzm(IObjectWrapper iObjectWrapper) {
        this.zzLH.trackView((View) zzd.zzF(iObjectWrapper));
    }

    public void zzn(IObjectWrapper iObjectWrapper) {
        this.zzLH.untrackView((View) zzd.zzF(iObjectWrapper));
    }
}

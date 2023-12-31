package com.google.android.gms.ads.formats;

import android.os.Bundle;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public abstract class NativeContentAd extends NativeAd {

    public interface OnContentAdLoadedListener {
        void onContentAdLoaded(NativeContentAd nativeContentAd);
    }

    public abstract void destroy();

    public abstract CharSequence getAdvertiser();

    public abstract CharSequence getBody();

    public abstract CharSequence getCallToAction();

    public abstract Bundle getExtras();

    public abstract CharSequence getHeadline();

    public abstract List<NativeAd.Image> getImages();

    public abstract NativeAd.Image getLogo();

    public abstract VideoController getVideoController();
}

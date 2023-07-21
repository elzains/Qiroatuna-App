package com.google.android.gms.ads.formats;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zzme;

@zzme
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzrT;
    private final int zzrU;
    private final boolean zzrV;
    private final int zzrW;
    private final VideoOptions zzrX;

    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzrT = false;
        /* access modifiers changed from: private */
        public int zzrU = 0;
        /* access modifiers changed from: private */
        public boolean zzrV = false;
        /* access modifiers changed from: private */
        public int zzrW = 1;
        /* access modifiers changed from: private */
        public VideoOptions zzrX;

        public NativeAdOptions build() {
            return new NativeAdOptions(this);
        }

        public Builder setAdChoicesPlacement(@AdChoicesPlacement int i) {
            this.zzrW = i;
            return this;
        }

        public Builder setImageOrientation(int i) {
            this.zzrU = i;
            return this;
        }

        public Builder setRequestMultipleImages(boolean z) {
            this.zzrV = z;
            return this;
        }

        public Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzrT = z;
            return this;
        }

        public Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzrX = videoOptions;
            return this;
        }
    }

    private NativeAdOptions(Builder builder) {
        this.zzrT = builder.zzrT;
        this.zzrU = builder.zzrU;
        this.zzrV = builder.zzrV;
        this.zzrW = builder.zzrW;
        this.zzrX = builder.zzrX;
    }

    public int getAdChoicesPlacement() {
        return this.zzrW;
    }

    public int getImageOrientation() {
        return this.zzrU;
    }

    @Nullable
    public VideoOptions getVideoOptions() {
        return this.zzrX;
    }

    public boolean shouldRequestMultipleImages() {
        return this.zzrV;
    }

    public boolean shouldReturnUrlsForImageAssets() {
        return this.zzrT;
    }
}

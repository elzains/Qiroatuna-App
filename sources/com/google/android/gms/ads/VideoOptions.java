package com.google.android.gms.ads;

import com.google.android.gms.internal.zzme;

@zzme
public final class VideoOptions {
    private final boolean zzrM;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzrM = false;

        public VideoOptions build() {
            return new VideoOptions(this);
        }

        public Builder setStartMuted(boolean z) {
            this.zzrM = z;
            return this;
        }
    }

    private VideoOptions(Builder builder) {
        this.zzrM = builder.zzrM;
    }

    public boolean getStartMuted() {
        return this.zzrM;
    }
}

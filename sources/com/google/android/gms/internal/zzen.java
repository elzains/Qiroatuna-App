package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@zzme
public class zzen extends AdListener {
    private final Object lock = new Object();
    private AdListener zzzY;

    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzzY != null) {
                this.zzzY.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            if (this.zzzY != null) {
                this.zzzY.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzzY != null) {
                this.zzzY.onAdLeftApplication();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzzY != null) {
                this.zzzY.onAdLoaded();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzzY != null) {
                this.zzzY.onAdOpened();
            }
        }
    }

    public void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzzY = adListener;
        }
    }
}

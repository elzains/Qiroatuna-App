package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.zzfh;
import com.google.android.gms.internal.zzfi;

public class MobileAds {

    public static final class Settings {
        private final zzfi zzrI = new zzfi();

        @Deprecated
        public String getTrackingId() {
            return null;
        }

        @Deprecated
        public boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public Settings setTrackingId(String str) {
            return this;
        }

        /* access modifiers changed from: package-private */
        public zzfi zzbr() {
            return this.zzrI;
        }
    }

    private MobileAds() {
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzfh.zzfk().getRewardedVideoAdInstance(context);
    }

    public static void initialize(Context context) {
        initialize(context, (String) null, (Settings) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(Context context, String str) {
        initialize(context, str, (Settings) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzfh.zzfk().zza(context, str, settings == null ? null : settings.zzbr());
    }

    public static void openDebugMenu(Context context, String str) {
        zzfh.zzfk().openDebugMenu(context, str);
    }

    public static void setAppMuted(boolean z) {
        zzfh.zzfk().setAppMuted(z);
    }

    public static void setAppVolume(float f) {
        zzfh.zzfk().setAppVolume(f);
    }
}

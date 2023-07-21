package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzadg;

public class zze {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzg.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zze zzayo = new zze();

    zze() {
    }

    static String zzA(@Nullable Context context, @Nullable String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(zzadg.zzbi(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sb.toString();
    }

    public static zze zzuY() {
        return zzayo;
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, (String) null);
    }

    public String getErrorString(int i) {
        return zzg.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zzg.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zzg.isGooglePlayServicesAvailable(context);
        if (zzg.zzd(context, isGooglePlayServicesAvailable)) {
            return 18;
        }
        return isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zzg.isUserRecoverableError(i);
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        Intent zzb = zzb(context, i, str);
        if (zzb == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, zzb, 268435456);
    }

    public int zzaC(Context context) {
        return zzg.zzaC(context);
    }

    public void zzaE(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzg.zzaq(context);
    }

    public void zzaF(Context context) {
        zzg.zzaF(context);
    }

    @Nullable
    public Intent zzb(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !zzj.zzba(context)) ? zzp.zzD("com.google.android.gms", zzA(context, str)) : zzp.zzyb();
            case 3:
                return zzp.zzdp("com.google.android.gms");
            default:
                return null;
        }
    }

    @Nullable
    @Deprecated
    public Intent zzcw(int i) {
        return zzb((Context) null, i, (String) null);
    }

    public boolean zzd(Context context, int i) {
        return zzg.zzd(context, i);
    }

    public boolean zzz(Context context, String str) {
        return zzg.zzz(context, str);
    }
}

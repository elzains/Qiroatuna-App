package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.C0156R;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzm;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzy;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzadg;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzg {
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 10298000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static boolean zzayA = false;
    static final AtomicBoolean zzayB = new AtomicBoolean();
    private static final AtomicBoolean zzayC = new AtomicBoolean();
    public static boolean zzayx = false;
    public static boolean zzayy = false;
    static boolean zzayz = false;

    zzg() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.zzuY().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getOpenSourceSoftwareLicenseInfo(android.content.Context r4) {
        /*
            r1 = 0
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            java.lang.String r2 = "android.resource"
            android.net.Uri$Builder r0 = r0.scheme(r2)
            java.lang.String r2 = "com.google.android.gms"
            android.net.Uri$Builder r0 = r0.authority(r2)
            java.lang.String r2 = "raw"
            android.net.Uri$Builder r0 = r0.appendPath(r2)
            java.lang.String r2 = "oss_notice"
            android.net.Uri$Builder r0 = r0.appendPath(r2)
            android.net.Uri r0 = r0.build()
            android.content.ContentResolver r2 = r4.getContentResolver()     // Catch:{ Exception -> 0x004e }
            java.io.InputStream r2 = r2.openInputStream(r0)     // Catch:{ Exception -> 0x004e }
            java.util.Scanner r0 = new java.util.Scanner     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            r0.<init>(r2)     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            java.lang.String r3 = "\\A"
            java.util.Scanner r0 = r0.useDelimiter(r3)     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            java.lang.String r0 = r0.next()     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x003e:
            return r0
        L_0x003f:
            r0 = move-exception
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x0045:
            r0 = r1
            goto L_0x003e
        L_0x0047:
            r0 = move-exception
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x004d:
            throw r0     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            r0 = move-exception
            r0 = r1
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzg.getOpenSourceSoftwareLicenseInfo(android.content.Context):java.lang.String");
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(C0156R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            zzaH(context);
        }
        boolean z = !zzj.zzba(context) && !zzj.zzbc(context);
        PackageInfo packageInfo = null;
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzh zzaN = zzh.zzaN(context);
            if (z) {
                zzf.zza zza = zzaN.zza(packageInfo, zzf.zzd.zzayw);
                if (zza == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                    return 9;
                }
                if (zzaN.zza(packageInfo2, zza) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                    return 9;
                }
            } else if (zzaN.zza(packageInfo2, zzf.zzd.zzayw) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (zzm.zzdp(packageInfo2.versionCode) < zzm.zzdp(GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                Log.w("GooglePlayServicesUtil", new StringBuilder(77).append("Google Play services out of date.  Requires ").append(GOOGLE_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(packageInfo2.versionCode).toString());
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static int zzaC(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static void zzaF(Context context) {
        if (!zzayB.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException e) {
            }
        }
    }

    private static void zzaH(Context context) {
        if (!zzayC.get()) {
            int zzaW = zzz.zzaW(context);
            if (zzaW == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzaW != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                int i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                String valueOf = String.valueOf("com.google.android.gms.version");
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 290).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(i).append(" but found ").append(zzaW).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(valueOf).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
    }

    public static boolean zzaI(Context context) {
        zzaL(context);
        return zzayz;
    }

    public static boolean zzaJ(Context context) {
        return zzaI(context) || !zzvd();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = ((android.os.UserManager) r3.getSystemService("user")).getApplicationRestrictions(r3.getPackageName());
     */
    @android.annotation.TargetApi(18)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzaK(android.content.Context r3) {
        /*
            boolean r0 = com.google.android.gms.common.util.zzt.zzzk()
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "user"
            java.lang.Object r0 = r3.getSystemService(r0)
            android.os.UserManager r0 = (android.os.UserManager) r0
            java.lang.String r1 = r3.getPackageName()
            android.os.Bundle r0 = r0.getApplicationRestrictions(r1)
            if (r0 == 0) goto L_0x0028
            java.lang.String r1 = "true"
            java.lang.String r2 = "restricted_profile"
            java.lang.String r0 = r0.getString(r2)
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0028
            r0 = 1
        L_0x0027:
            return r0
        L_0x0028:
            r0 = 0
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzg.zzaK(android.content.Context):boolean");
    }

    private static void zzaL(Context context) {
        if (!zzayA) {
            zzaM(context);
        }
    }

    private static void zzaM(Context context) {
        try {
            PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo("com.google.android.gms", 64);
            if (packageInfo != null) {
                if (zzh.zzaN(context).zza(packageInfo, zzf.zzd.zzayw[1]) != null) {
                    zzayz = true;
                }
            }
            zzayz = false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
        } finally {
            zzayA = true;
        }
    }

    @Deprecated
    public static void zzaq(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = zze.zzuY().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent zzb = zze.zzuY().zzb(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", new StringBuilder(57).append("GooglePlayServices not available due to error ").append(isGooglePlayServicesAvailable).toString());
            if (zzb == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zzb);
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean zzc(Context context, int i, String str) {
        return zzy.zzc(context, i, str);
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zzz(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        if (i == 9) {
            return zzz(context, "com.android.vending");
        }
        return false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        return zzy.zzf(context, i);
    }

    @Deprecated
    public static boolean zzvd() {
        return zzj.zzzd();
    }

    @TargetApi(21)
    static boolean zzz(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (zzt.zzzo()) {
            for (PackageInstaller.SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !zzaK(context);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}

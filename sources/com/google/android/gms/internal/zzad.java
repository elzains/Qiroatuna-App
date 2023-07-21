package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import java.io.File;

public class zzad {
    public static zzm zza(Context context) {
        return zza(context, (zzz) null);
    }

    public static zzm zza(Context context, zzz zzz) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (zzz == null) {
            zzz = Build.VERSION.SDK_INT >= 9 ? new zzaa() : new zzx(AndroidHttpClient.newInstance(str));
        }
        zzm zzm = new zzm(new zzw(file), new zzu(zzz));
        zzm.start();
        return zzm;
    }
}

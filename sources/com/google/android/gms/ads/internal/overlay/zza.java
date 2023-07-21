package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;

@zzme
public class zza {
    public boolean zza(Context context, Intent intent, zzq zzq) {
        try {
            String valueOf = String.valueOf(intent.toURI());
            zzpk.m19v(valueOf.length() != 0 ? "Launching an intent: ".concat(valueOf) : new String("Launching an intent: "));
            zzw.zzcM().zzb(context, intent);
            if (zzq != null) {
                zzq.zzbD();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzpk.zzbh(e.getMessage());
            return false;
        }
    }

    public boolean zza(Context context, zzc zzc, zzq zzq) {
        int i;
        if (zzc == null) {
            zzpk.zzbh("No intent data for launcher overlay.");
            return false;
        }
        zzgd.initialize(context);
        if (zzc.intent != null) {
            return zza(context, zzc.intent, zzq);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(zzc.url)) {
            zzpk.zzbh("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty(zzc.mimeType)) {
            intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
        } else {
            intent.setData(Uri.parse(zzc.url));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(zzc.packageName)) {
            intent.setPackage(zzc.packageName);
        }
        if (!TextUtils.isEmpty(zzc.zzMH)) {
            String[] split = zzc.zzMH.split("/", 2);
            if (split.length < 2) {
                String valueOf = String.valueOf(zzc.zzMH);
                zzpk.zzbh(valueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(valueOf) : new String("Could not parse component name from open GMSG: "));
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        String str = zzc.zzMI;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                zzpk.zzbh("Could not parse intent flags.");
                i = 0;
            }
            intent.addFlags(i);
        }
        if (zzgd.zzFk.get().booleanValue()) {
            intent.addFlags(268435456);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        }
        return zza(context, intent, zzq);
    }
}

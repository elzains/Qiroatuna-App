package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzadg;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzme
public class zzd extends zzld.zza {
    private Context mContext;
    private ArrayList<String> zzPA;
    private String zzPz;
    private String zzwd;

    public zzd(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.zzPz = str;
        this.zzPA = arrayList;
        this.zzwd = str2;
        this.mContext = context;
    }

    public String getProductId() {
        return this.zzPz;
    }

    public void recordPlayBillingResolution(int i) {
        if (i == 0) {
            zziK();
        }
        Map<String, String> zziJ = zziJ();
        zziJ.put("google_play_status", String.valueOf(i));
        zziJ.put("sku", this.zzPz);
        zziJ.put("status", String.valueOf(zzP(i)));
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = this.zzPA.iterator();
        while (it.hasNext()) {
            linkedList.add(zzw.zzcM().zzb(it.next(), zziJ));
        }
        zzw.zzcM().zza(this.mContext, this.zzwd, (List<String>) linkedList);
    }

    public void recordResolution(int i) {
        if (i == 1) {
            zziK();
        }
        Map<String, String> zziJ = zziJ();
        zziJ.put("status", String.valueOf(i));
        zziJ.put("sku", this.zzPz);
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = this.zzPA.iterator();
        while (it.hasNext()) {
            linkedList.add(zzw.zzcM().zzb(it.next(), zziJ));
        }
        zzw.zzcM().zza(this.mContext, this.zzwd, (List<String>) linkedList);
    }

    /* access modifiers changed from: protected */
    public int zzP(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        return i == 4 ? 3 : 0;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zziJ() {
        String packageName = this.mContext.getPackageName();
        String str = "";
        try {
            str = zzadg.zzbi(this.mContext).getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            zzpk.zzc("Error to retrieve app version", e);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - zzw.zzcQ().zzkj().zzkE();
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", zzw.zzcQ().getSessionId());
        hashMap.put("appid", packageName);
        hashMap.put("osversion", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("sdkversion", this.zzwd);
        hashMap.put("appversion", str);
        hashMap.put("timestamp", String.valueOf(elapsedRealtime));
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void zziK() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke((Object) null, new Object[]{this.mContext, this.zzPz, "", true});
        } catch (ClassNotFoundException e) {
            zzpk.zzbh("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            zzpk.zzbh("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Exception e3) {
            zzpk.zzc("Fail to report a conversion.", e3);
        }
    }
}

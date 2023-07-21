package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzqf;

public class InAppPurchaseActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private zzlf zzaaX;

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (this.zzaaX != null) {
                this.zzaaX.onActivityResult(i, i2, intent);
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onActivityResult to in-app purchase manager:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzaaX = zzel.zzeU().zzb((Activity) this);
        if (this.zzaaX == null) {
            zzqf.zzbh("Could not create in-app purchase manager.");
            finish();
            return;
        }
        try {
            this.zzaaX.onCreate();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onCreate to in-app purchase manager:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.zzaaX != null) {
                this.zzaaX.onDestroy();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onDestroy to in-app purchase manager:", e);
        }
        super.onDestroy();
    }
}

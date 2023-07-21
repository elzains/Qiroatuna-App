package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzme
public class zzlm implements InAppPurchase {
    private final zzld zzPC;

    public zzlm(zzld zzld) {
        this.zzPC = zzld;
    }

    public String getProductId() {
        try {
            return this.zzPC.getProductId();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getProductId to InAppPurchase", e);
            return null;
        }
    }

    public void recordPlayBillingResolution(int i) {
        try {
            this.zzPC.recordPlayBillingResolution(i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward recordPlayBillingResolution to InAppPurchase", e);
        }
    }

    public void recordResolution(int i) {
        try {
            this.zzPC.recordResolution(i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward recordResolution to InAppPurchase", e);
        }
    }
}

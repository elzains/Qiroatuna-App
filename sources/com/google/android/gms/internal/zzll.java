package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zzme
public class zzll implements InAppPurchaseResult {
    private final zzlh zzPP;

    public zzll(zzlh zzlh) {
        this.zzPP = zzlh;
    }

    public void finishPurchase() {
        try {
            this.zzPP.finishPurchase();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward finishPurchase to InAppPurchaseResult", e);
        }
    }

    public String getProductId() {
        try {
            return this.zzPP.getProductId();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getProductId to InAppPurchaseResult", e);
            return null;
        }
    }

    public Intent getPurchaseData() {
        try {
            return this.zzPP.getPurchaseData();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return null;
        }
    }

    public int getResultCode() {
        try {
            return this.zzPP.getResultCode();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return 0;
        }
    }

    public boolean isVerified() {
        try {
            return this.zzPP.isVerified();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward isVerified to InAppPurchaseResult", e);
            return false;
        }
    }
}

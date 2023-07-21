package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzme;

@zzme
public final class GInAppPurchaseManagerInfoParcel extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<GInAppPurchaseManagerInfoParcel> CREATOR = new zza();
    public final zzld zzPn;
    public final Context zzPo;
    public final zzj zzPp;
    public final zzk zzvL;

    public GInAppPurchaseManagerInfoParcel(Context context, zzk zzk, zzld zzld, zzj zzj) {
        this.zzPo = context;
        this.zzvL = zzk;
        this.zzPn = zzld;
        this.zzPp = zzj;
    }

    GInAppPurchaseManagerInfoParcel(IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.zzvL = (zzk) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder));
        this.zzPn = (zzld) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder2));
        this.zzPo = (Context) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder3));
        this.zzPp = (zzj) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder4));
    }

    public static void zza(Intent intent, GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", gInAppPurchaseManagerInfoParcel);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }

    public static GInAppPurchaseManagerInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(GInAppPurchaseManagerInfoParcel.class.getClassLoader());
            return (GInAppPurchaseManagerInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public IBinder zziE() {
        return zzd.zzA(this.zzPp).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zziF() {
        return zzd.zzA(this.zzvL).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zziG() {
        return zzd.zzA(this.zzPn).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zziH() {
        return zzd.zzA(this.zzPo).asBinder();
    }
}

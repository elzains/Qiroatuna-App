package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzn;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;

@zzme
public final class AdOverlayInfoParcel extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new zzg();
    public final int orientation;
    public final String url;
    public final zzc zzNE;
    public final zzdx zzNF;
    public final zzh zzNG;
    public final zzqw zzNH;
    public final zzhz zzNI;
    public final String zzNJ;
    public final boolean zzNK;
    public final String zzNL;
    public final zzq zzNM;
    public final int zzNN;
    public final zzif zzNO;
    public final String zzNP;
    public final zzn zzNQ;
    public final zzqh zzvn;

    AdOverlayInfoParcel(zzc zzc, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i, int i2, String str3, zzqh zzqh, IBinder iBinder6, String str4, zzn zzn) {
        this.zzNE = zzc;
        this.zzNF = (zzdx) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder));
        this.zzNG = (zzh) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder2));
        this.zzNH = (zzqw) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder3));
        this.zzNI = (zzhz) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder4));
        this.zzNJ = str;
        this.zzNK = z;
        this.zzNL = str2;
        this.zzNM = (zzq) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder5));
        this.orientation = i;
        this.zzNN = i2;
        this.url = str3;
        this.zzvn = zzqh;
        this.zzNO = (zzif) zzd.zzF(IObjectWrapper.zza.zzcd(iBinder6));
        this.zzNP = str4;
        this.zzNQ = zzn;
    }

    public AdOverlayInfoParcel(zzc zzc, zzdx zzdx, zzh zzh, zzq zzq, zzqh zzqh) {
        this.zzNE = zzc;
        this.zzNF = zzdx;
        this.zzNG = zzh;
        this.zzNH = null;
        this.zzNI = null;
        this.zzNJ = null;
        this.zzNK = false;
        this.zzNL = null;
        this.zzNM = zzq;
        this.orientation = -1;
        this.zzNN = 4;
        this.url = null;
        this.zzvn = zzqh;
        this.zzNO = null;
        this.zzNP = null;
        this.zzNQ = null;
    }

    public AdOverlayInfoParcel(zzdx zzdx, zzh zzh, zzq zzq, zzqw zzqw, int i, zzqh zzqh, String str, zzn zzn) {
        this.zzNE = null;
        this.zzNF = zzdx;
        this.zzNG = zzh;
        this.zzNH = zzqw;
        this.zzNI = null;
        this.zzNJ = null;
        this.zzNK = false;
        this.zzNL = null;
        this.zzNM = zzq;
        this.orientation = i;
        this.zzNN = 1;
        this.url = null;
        this.zzvn = zzqh;
        this.zzNO = null;
        this.zzNP = str;
        this.zzNQ = zzn;
    }

    public AdOverlayInfoParcel(zzdx zzdx, zzh zzh, zzq zzq, zzqw zzqw, boolean z, int i, zzqh zzqh) {
        this.zzNE = null;
        this.zzNF = zzdx;
        this.zzNG = zzh;
        this.zzNH = zzqw;
        this.zzNI = null;
        this.zzNJ = null;
        this.zzNK = z;
        this.zzNL = null;
        this.zzNM = zzq;
        this.orientation = i;
        this.zzNN = 2;
        this.url = null;
        this.zzvn = zzqh;
        this.zzNO = null;
        this.zzNP = null;
        this.zzNQ = null;
    }

    public AdOverlayInfoParcel(zzdx zzdx, zzh zzh, zzhz zzhz, zzq zzq, zzqw zzqw, boolean z, int i, String str, zzqh zzqh, zzif zzif) {
        this.zzNE = null;
        this.zzNF = zzdx;
        this.zzNG = zzh;
        this.zzNH = zzqw;
        this.zzNI = zzhz;
        this.zzNJ = null;
        this.zzNK = z;
        this.zzNL = null;
        this.zzNM = zzq;
        this.orientation = i;
        this.zzNN = 3;
        this.url = str;
        this.zzvn = zzqh;
        this.zzNO = zzif;
        this.zzNP = null;
        this.zzNQ = null;
    }

    public AdOverlayInfoParcel(zzdx zzdx, zzh zzh, zzhz zzhz, zzq zzq, zzqw zzqw, boolean z, int i, String str, String str2, zzqh zzqh, zzif zzif) {
        this.zzNE = null;
        this.zzNF = zzdx;
        this.zzNG = zzh;
        this.zzNH = zzqw;
        this.zzNI = zzhz;
        this.zzNJ = str2;
        this.zzNK = z;
        this.zzNL = str;
        this.zzNM = zzq;
        this.orientation = i;
        this.zzNN = 3;
        this.url = null;
        this.zzvn = zzqh;
        this.zzNO = zzif;
        this.zzNP = null;
        this.zzNQ = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzb(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhN() {
        return zzd.zzA(this.zzNF).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhO() {
        return zzd.zzA(this.zzNG).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhP() {
        return zzd.zzA(this.zzNH).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhQ() {
        return zzd.zzA(this.zzNI).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhR() {
        return zzd.zzA(this.zzNO).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhS() {
        return zzd.zzA(this.zzNM).asBinder();
    }
}

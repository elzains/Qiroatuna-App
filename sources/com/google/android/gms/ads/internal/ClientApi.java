package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzhe;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzjd;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zznr;
import com.google.android.gms.internal.zznu;
import com.google.android.gms.internal.zzqh;

@Keep
@zzme
@DynamiteApi
public class ClientApi extends zzew.zza {
    public zzer createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzka zzka, int i) {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        return new zzl(context, str, zzka, new zzqh(10298000, i, true, zzw.zzcM().zzU(context)), zze.zzcc());
    }

    public zzkz createAdOverlay(IObjectWrapper iObjectWrapper) {
        return new zze((Activity) zzd.zzF(iObjectWrapper));
    }

    public zzet createBannerAdManager(IObjectWrapper iObjectWrapper, zzeg zzeg, String str, zzka zzka, int i) throws RemoteException {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        return new zzg(context, zzeg, str, zzka, new zzqh(10298000, i, true, zzw.zzcM().zzU(context)), zze.zzcc());
    }

    public zzlf createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return new com.google.android.gms.ads.internal.purchase.zze((Activity) zzd.zzF(iObjectWrapper));
    }

    public zzet createInterstitialAdManager(IObjectWrapper iObjectWrapper, zzeg zzeg, String str, zzka zzka, int i) throws RemoteException {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        zzgd.initialize(context);
        zzqh zzqh = new zzqh(10298000, i, true, zzw.zzcM().zzU(context));
        boolean equals = "reward_mb".equals(zzeg.zzzy);
        if ((!equals && zzgd.zzDc.get().booleanValue()) || (equals && zzgd.zzDd.get().booleanValue())) {
            return new zzjd(context, str, zzka, zzqh, zze.zzcc());
        }
        return new zzm(context, zzeg, str, zzka, zzqh, zze.zzcc());
    }

    public zzhh createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzhe((FrameLayout) zzd.zzF(iObjectWrapper), (FrameLayout) zzd.zzF(iObjectWrapper2));
    }

    public zznu createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzka zzka, int i) {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        return new zznr(context, zze.zzcc(), zzka, new zzqh(10298000, i, true, zzw.zzcM().zzU(context)));
    }

    public zzet createSearchAdManager(IObjectWrapper iObjectWrapper, zzeg zzeg, String str, int i) throws RemoteException {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        return new zzv(context, zzeg, str, new zzqh(10298000, i, true, zzw.zzcM().zzU(context)));
    }

    @Nullable
    public zzey getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public zzey getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        return zzq.zza(context, new zzqh(10298000, i, true, zzw.zzcM().zzU(context)));
    }
}

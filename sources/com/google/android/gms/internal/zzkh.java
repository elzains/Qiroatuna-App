package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzkb;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzme
public final class zzkh extends zzkb.zza {
    private final MediationAdapter zzLC;
    private zzki zzLD;

    public zzkh(MediationAdapter mediationAdapter) {
        this.zzLC = mediationAdapter;
    }

    private Bundle zza(String str, zzec zzec, String str2) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzqf.zzbh(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zzLC instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzec != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzec.zzyX);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzqf.zzc("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    public void destroy() throws RemoteException {
        try {
            this.zzLC.onDestroy();
        } catch (Throwable th) {
            zzqf.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        if (this.zzLC instanceof zzrj) {
            return ((zzrj) this.zzLC).getInterstitialAdapterInfo();
        }
        String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
        zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationInterstitialAdapter: "));
        return new Bundle();
    }

    public IObjectWrapper getView() throws RemoteException {
        if (!(this.zzLC instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zzd.zzA(((MediationBannerAdapter) this.zzLC).getBannerView());
        } catch (Throwable th) {
            zzqf.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public boolean isInitialized() throws RemoteException {
        if (!(this.zzLC instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zzLC).isInitialized();
        } catch (Throwable th) {
            zzqf.zzc("Could not check if adapter is initialized.", th);
            throw new RemoteException();
        }
    }

    public void pause() throws RemoteException {
        try {
            this.zzLC.onPause();
        } catch (Throwable th) {
            zzqf.zzc("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    public void resume() throws RemoteException {
        try {
            this.zzLC.onResume();
        } catch (Throwable th) {
            zzqf.zzc("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    public void showInterstitial() throws RemoteException {
        if (!(this.zzLC instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzLC).showInterstitial();
        } catch (Throwable th) {
            zzqf.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public void showVideo() throws RemoteException {
        if (!(this.zzLC instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zzLC).showVideo();
        } catch (Throwable th) {
            zzqf.zzc("Could not show rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException {
        zza(iObjectWrapper, zzec, str, (String) null, zzkc);
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException {
        Bundle bundle;
        zzkg zzkg;
        if (!(this.zzLC instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzLC;
            Bundle zza = zza(str2, zzec, (String) null);
            if (zzec != null) {
                zzkg zzkg2 = new zzkg(zzec.zzyT == -1 ? null : new Date(zzec.zzyT), zzec.zzyU, zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzzb, zzec.zzyW, zzec.zzyX, zzec.zzzi);
                if (zzec.zzzd != null) {
                    bundle = zzec.zzzd.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                    zzkg = zzkg2;
                } else {
                    bundle = null;
                    zzkg = zzkg2;
                }
            } else {
                bundle = null;
                zzkg = null;
            }
            mediationRewardedVideoAdAdapter.initialize((Context) zzd.zzF(iObjectWrapper), zzkg, str, new zzon(zzom), zza, bundle);
        } catch (Throwable th) {
            zzqf.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
        if (!(this.zzLC instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzLC;
            mediationInterstitialAdapter.requestInterstitialAd((Context) zzd.zzF(iObjectWrapper), new zzki(zzkc), zza(str, zzec, str2), new zzkg(zzec.zzyT == -1 ? null : new Date(zzec.zzyT), zzec.zzyU, zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzzb, zzec.zzyW, zzec.zzyX, zzec.zzzi), zzec.zzzd != null ? zzec.zzzd.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzqf.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) throws RemoteException {
        if (!(this.zzLC instanceof MediationNativeAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationNativeAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationNativeAdapter: "));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzLC;
            zzkl zzkl = new zzkl(zzec.zzyT == -1 ? null : new Date(zzec.zzyT), zzec.zzyU, zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzzb, zzec.zzyW, zzec.zzyX, zzhc, list, zzec.zzzi);
            Bundle bundle = zzec.zzzd != null ? zzec.zzzd.getBundle(mediationNativeAdapter.getClass().getName()) : null;
            this.zzLD = new zzki(zzkc);
            mediationNativeAdapter.requestNativeAd((Context) zzd.zzF(iObjectWrapper), this.zzLD, zza(str, zzec, str2), zzkl, bundle);
        } catch (Throwable th) {
            zzqf.zzc("Could not request native ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException {
        zza(iObjectWrapper, zzeg, zzec, str, (String) null, zzkc);
    }

    public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
        if (!(this.zzLC instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzLC;
            mediationBannerAdapter.requestBannerAd((Context) zzd.zzF(iObjectWrapper), new zzki(zzkc), zza(str, zzec, str2), zza.zza(zzeg.width, zzeg.height, zzeg.zzzy), new zzkg(zzec.zzyT == -1 ? null : new Date(zzec.zzyT), zzec.zzyU, zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzzb, zzec.zzyW, zzec.zzyX, zzec.zzzi), zzec.zzzd != null ? zzec.zzzd.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzqf.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) throws RemoteException {
        if (!(this.zzLC instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzLC;
            ArrayList arrayList = new ArrayList();
            for (String zza : list) {
                arrayList.add(zza(zza, (zzec) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) zzd.zzF(iObjectWrapper), new zzon(zzom), arrayList);
        } catch (Throwable th) {
            zzqf.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzec zzec, String str, String str2) throws RemoteException {
        if (!(this.zzLC instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzLC;
            mediationRewardedVideoAdAdapter.loadAd(new zzkg(zzec.zzyT == -1 ? null : new Date(zzec.zzyT), zzec.zzyU, zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzzb, zzec.zzyW, zzec.zzyX, zzec.zzzi), zza(str, zzec, str2), zzec.zzzd != null ? zzec.zzzd.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzqf.zzc("Could not load rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zzd(zzec zzec, String str) throws RemoteException {
        zza(zzec, str, (String) null);
    }

    public zzke zzhc() {
        NativeAdMapper zzhi = this.zzLD.zzhi();
        if (zzhi instanceof NativeAppInstallAdMapper) {
            return new zzkj((NativeAppInstallAdMapper) zzhi);
        }
        return null;
    }

    public zzkf zzhd() {
        NativeAdMapper zzhi = this.zzLD.zzhi();
        if (zzhi instanceof NativeContentAdMapper) {
            return new zzkk((NativeContentAdMapper) zzhi);
        }
        return null;
    }

    public Bundle zzhe() {
        if (this.zzLC instanceof zzri) {
            return ((zzri) this.zzLC).zzhe();
        }
        String valueOf = String.valueOf(this.zzLC.getClass().getCanonicalName());
        zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationBannerAdapter: "));
        return new Bundle();
    }

    public Bundle zzhf() {
        return new Bundle();
    }

    public boolean zzhg() {
        return this.zzLC instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzLC).onContextChanged((Context) zzd.zzF(iObjectWrapper));
        } catch (Throwable th) {
            zzqf.zza("Could not inform adapter of changed context", th);
        }
    }
}

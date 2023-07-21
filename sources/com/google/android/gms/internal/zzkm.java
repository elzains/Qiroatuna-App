package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzkb;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzme
public final class zzkm<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzkb.zza {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzLI;
    private final NETWORK_EXTRAS zzLJ;

    public zzkm(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzLI = mediationAdapter;
        this.zzLJ = network_extras;
    }

    private SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Throwable th) {
                zzqf.zzc("Could not get MediationServerParameters.", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class<SERVER_PARAMETERS> serverParametersType = this.zzLI.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.load(hashMap);
        return server_parameters;
    }

    public void destroy() throws RemoteException {
        try {
            this.zzLI.destroy();
        } catch (Throwable th) {
            zzqf.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public IObjectWrapper getView() throws RemoteException {
        if (!(this.zzLI instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLI.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zzd.zzA(((MediationBannerAdapter) this.zzLI).getBannerView());
        } catch (Throwable th) {
            zzqf.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        return true;
    }

    public void pause() throws RemoteException {
        throw new RemoteException();
    }

    public void resume() throws RemoteException {
        throw new RemoteException();
    }

    public void showInterstitial() throws RemoteException {
        if (!(this.zzLI instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLI.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzLI).showInterstitial();
        } catch (Throwable th) {
            zzqf.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public void showVideo() {
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException {
        zza(iObjectWrapper, zzec, str, (String) null, zzkc);
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException {
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
        if (!(this.zzLI instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLI.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzLI).requestInterstitialAd(new zzkn(zzkc), (Activity) zzd.zzF(iObjectWrapper), zza(str, zzec.zzyX, str2), zzko.zzr(zzec), this.zzLJ);
        } catch (Throwable th) {
            zzqf.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) {
    }

    public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException {
        zza(iObjectWrapper, zzeg, zzec, str, (String) null, zzkc);
    }

    public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
        if (!(this.zzLI instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLI.getClass().getCanonicalName());
            zzqf.zzbh(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzqf.zzbf("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.zzLI).requestBannerAd(new zzkn(zzkc), (Activity) zzd.zzF(iObjectWrapper), zza(str, zzec.zzyX, str2), zzko.zzc(zzeg), zzko.zzr(zzec), this.zzLJ);
        } catch (Throwable th) {
            zzqf.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) {
    }

    public void zza(zzec zzec, String str, String str2) {
    }

    public void zzd(zzec zzec, String str) {
    }

    public zzke zzhc() {
        return null;
    }

    public zzkf zzhd() {
        return null;
    }

    public Bundle zzhe() {
        return new Bundle();
    }

    public Bundle zzhf() {
        return new Bundle();
    }

    public boolean zzhg() {
        return false;
    }

    public void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}

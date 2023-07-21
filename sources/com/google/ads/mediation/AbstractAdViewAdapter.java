package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqf;
import com.google.android.gms.internal.zzrj;
import java.util.Date;
import java.util.Set;

@zzme
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter, zzrj {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    protected AdView zzcD;
    protected InterstitialAd zzcE;
    private AdLoader zzcF;
    private Context zzcG;
    /* access modifiers changed from: private */
    public InterstitialAd zzcH;
    /* access modifiers changed from: private */
    public MediationRewardedVideoAdListener zzcI;
    final RewardedVideoAdListener zzcJ = new RewardedVideoAdListener() {
        public void onRewarded(RewardItem rewardItem) {
            AbstractAdViewAdapter.this.zzcI.onRewarded(AbstractAdViewAdapter.this, rewardItem);
        }

        public void onRewardedVideoAdClosed() {
            AbstractAdViewAdapter.this.zzcI.onAdClosed(AbstractAdViewAdapter.this);
            InterstitialAd unused = AbstractAdViewAdapter.this.zzcH = null;
        }

        public void onRewardedVideoAdFailedToLoad(int i) {
            AbstractAdViewAdapter.this.zzcI.onAdFailedToLoad(AbstractAdViewAdapter.this, i);
        }

        public void onRewardedVideoAdLeftApplication() {
            AbstractAdViewAdapter.this.zzcI.onAdLeftApplication(AbstractAdViewAdapter.this);
        }

        public void onRewardedVideoAdLoaded() {
            AbstractAdViewAdapter.this.zzcI.onAdLoaded(AbstractAdViewAdapter.this);
        }

        public void onRewardedVideoAdOpened() {
            AbstractAdViewAdapter.this.zzcI.onAdOpened(AbstractAdViewAdapter.this);
        }

        public void onRewardedVideoStarted() {
            AbstractAdViewAdapter.this.zzcI.onVideoStarted(AbstractAdViewAdapter.this);
        }
    };

    static class zza extends NativeAppInstallAdMapper {
        private final NativeAppInstallAd zzcL;

        public zza(NativeAppInstallAd nativeAppInstallAd) {
            this.zzcL = nativeAppInstallAd;
            setHeadline(nativeAppInstallAd.getHeadline().toString());
            setImages(nativeAppInstallAd.getImages());
            setBody(nativeAppInstallAd.getBody().toString());
            setIcon(nativeAppInstallAd.getIcon());
            setCallToAction(nativeAppInstallAd.getCallToAction().toString());
            if (nativeAppInstallAd.getStarRating() != null) {
                setStarRating(nativeAppInstallAd.getStarRating().doubleValue());
            }
            if (nativeAppInstallAd.getStore() != null) {
                setStore(nativeAppInstallAd.getStore().toString());
            }
            if (nativeAppInstallAd.getPrice() != null) {
                setPrice(nativeAppInstallAd.getPrice().toString());
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeAppInstallAd.getVideoController());
        }

        public void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zzcL);
            }
        }
    }

    static class zzb extends NativeContentAdMapper {
        private final NativeContentAd zzcM;

        public zzb(NativeContentAd nativeContentAd) {
            this.zzcM = nativeContentAd;
            setHeadline(nativeContentAd.getHeadline().toString());
            setImages(nativeContentAd.getImages());
            setBody(nativeContentAd.getBody().toString());
            if (nativeContentAd.getLogo() != null) {
                setLogo(nativeContentAd.getLogo());
            }
            setCallToAction(nativeContentAd.getCallToAction().toString());
            setAdvertiser(nativeContentAd.getAdvertiser().toString());
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeContentAd.getVideoController());
        }

        public void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zzcM);
            }
        }
    }

    static final class zzc extends AdListener implements zzdx {
        final AbstractAdViewAdapter zzcN;
        final MediationBannerListener zzcO;

        public zzc(AbstractAdViewAdapter abstractAdViewAdapter, MediationBannerListener mediationBannerListener) {
            this.zzcN = abstractAdViewAdapter;
            this.zzcO = mediationBannerListener;
        }

        public void onAdClicked() {
            this.zzcO.onAdClicked(this.zzcN);
        }

        public void onAdClosed() {
            this.zzcO.onAdClosed(this.zzcN);
        }

        public void onAdFailedToLoad(int i) {
            this.zzcO.onAdFailedToLoad(this.zzcN, i);
        }

        public void onAdLeftApplication() {
            this.zzcO.onAdLeftApplication(this.zzcN);
        }

        public void onAdLoaded() {
            this.zzcO.onAdLoaded(this.zzcN);
        }

        public void onAdOpened() {
            this.zzcO.onAdOpened(this.zzcN);
        }
    }

    static final class zzd extends AdListener implements zzdx {
        final AbstractAdViewAdapter zzcN;
        final MediationInterstitialListener zzcP;

        public zzd(AbstractAdViewAdapter abstractAdViewAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzcN = abstractAdViewAdapter;
            this.zzcP = mediationInterstitialListener;
        }

        public void onAdClicked() {
            this.zzcP.onAdClicked(this.zzcN);
        }

        public void onAdClosed() {
            this.zzcP.onAdClosed(this.zzcN);
        }

        public void onAdFailedToLoad(int i) {
            this.zzcP.onAdFailedToLoad(this.zzcN, i);
        }

        public void onAdLeftApplication() {
            this.zzcP.onAdLeftApplication(this.zzcN);
        }

        public void onAdLoaded() {
            this.zzcP.onAdLoaded(this.zzcN);
        }

        public void onAdOpened() {
            this.zzcP.onAdOpened(this.zzcN);
        }
    }

    static final class zze extends AdListener implements NativeAppInstallAd.OnAppInstallAdLoadedListener, NativeContentAd.OnContentAdLoadedListener, zzdx {
        final AbstractAdViewAdapter zzcN;
        final MediationNativeListener zzcQ;

        public zze(AbstractAdViewAdapter abstractAdViewAdapter, MediationNativeListener mediationNativeListener) {
            this.zzcN = abstractAdViewAdapter;
            this.zzcQ = mediationNativeListener;
        }

        public void onAdClicked() {
            this.zzcQ.onAdClicked(this.zzcN);
        }

        public void onAdClosed() {
            this.zzcQ.onAdClosed(this.zzcN);
        }

        public void onAdFailedToLoad(int i) {
            this.zzcQ.onAdFailedToLoad(this.zzcN, i);
        }

        public void onAdLeftApplication() {
            this.zzcQ.onAdLeftApplication(this.zzcN);
        }

        public void onAdLoaded() {
        }

        public void onAdOpened() {
            this.zzcQ.onAdOpened(this.zzcN);
        }

        public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
            this.zzcQ.onAdLoaded(this.zzcN, new zza(nativeAppInstallAd));
        }

        public void onContentAdLoaded(NativeContentAd nativeContentAd) {
            this.zzcQ.onAdLoaded(this.zzcN, new zzb(nativeContentAd));
        }
    }

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.zzcD;
    }

    public Bundle getInterstitialAdapterInfo() {
        return new MediationAdapter.zza().zzam(1).zzmm();
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.zzcG = context.getApplicationContext();
        this.zzcI = mediationRewardedVideoAdListener;
        this.zzcI.onInitializationSucceeded(this);
    }

    public boolean isInitialized() {
        return this.zzcI != null;
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.zzcG == null || this.zzcI == null) {
            zzqf.m20e("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        this.zzcH = new InterstitialAd(this.zzcG);
        this.zzcH.zzd(true);
        this.zzcH.setAdUnitId(getAdUnitId(bundle));
        this.zzcH.setRewardedVideoAdListener(this.zzcJ);
        this.zzcH.loadAd(zza(this.zzcG, mediationAdRequest, bundle2, bundle));
    }

    public void onDestroy() {
        if (this.zzcD != null) {
            this.zzcD.destroy();
            this.zzcD = null;
        }
        if (this.zzcE != null) {
            this.zzcE = null;
        }
        if (this.zzcF != null) {
            this.zzcF = null;
        }
        if (this.zzcH != null) {
            this.zzcH = null;
        }
    }

    public void onPause() {
        if (this.zzcD != null) {
            this.zzcD.pause();
        }
    }

    public void onResume() {
        if (this.zzcD != null) {
            this.zzcD.resume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzcD = new AdView(context);
        this.zzcD.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzcD.setAdUnitId(getAdUnitId(bundle));
        this.zzcD.setAdListener(new zzc(this, mediationBannerListener));
        this.zzcD.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzcE = new InterstitialAd(context);
        this.zzcE.setAdUnitId(getAdUnitId(bundle));
        this.zzcE.setAdListener(new zzd(this, mediationInterstitialListener));
        this.zzcE.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        zze zze2 = new zze(this, mediationNativeListener);
        AdLoader.Builder withAdListener = zza(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(zze2);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(zze2);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(zze2);
        }
        this.zzcF = withAdListener.build();
        this.zzcF.loadAd(zza(context, nativeMediationAdRequest, bundle2, bundle));
    }

    public void showInterstitial() {
        this.zzcE.show();
    }

    public void showVideo() {
        this.zzcH.show();
    }

    /* access modifiers changed from: protected */
    public abstract Bundle zza(Bundle bundle, Bundle bundle2);

    /* access modifiers changed from: package-private */
    public AdLoader.Builder zza(Context context, String str) {
        return new AdLoader.Builder(context, str);
    }

    /* access modifiers changed from: package-private */
    public AdRequest zza(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            for (String addKeyword : keywords) {
                builder.addKeyword(addKeyword);
            }
        }
        Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            builder.addTestDevice(zzel.zzeT().zzad(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, zza(bundle, bundle2));
        return builder.build();
    }
}

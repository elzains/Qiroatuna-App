package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.zzgr;

@zzme
public class zzkp implements MediationInterstitialAdapter {
    private Uri mUri;
    /* access modifiers changed from: private */
    public Activity zzLO;
    /* access modifiers changed from: private */
    public zzgr zzLP;
    /* access modifiers changed from: private */
    public MediationInterstitialListener zzLQ;

    public static boolean zzr(Context context) {
        return zzgr.zzo(context);
    }

    public void onDestroy() {
        zzqf.zzbf("Destroying AdMobCustomTabsAdapter adapter.");
        try {
            this.zzLP.zzd(this.zzLO);
        } catch (Exception e) {
            zzqf.zzb("Exception while unbinding from CustomTabsService.", e);
        }
    }

    public void onPause() {
        zzqf.zzbf("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public void onResume() {
        zzqf.zzbf("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzLQ = mediationInterstitialListener;
        if (this.zzLQ == null) {
            zzqf.zzbh("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzqf.zzbh("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzLQ.onAdFailedToLoad(this, 0);
        } else if (!zzr(context)) {
            zzqf.zzbh("Default browser does not support custom tabs. Bailing out.");
            this.zzLQ.onAdFailedToLoad(this, 0);
        } else {
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzqf.zzbh("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzLQ.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzLO = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzLP = new zzgr();
            this.zzLP.zza((zzgr.zza) new zzgr.zza(this) {
                public void zzfJ() {
                    zzqf.zzbf("Hinting CustomTabsService for the load of the new url.");
                }

                public void zzfK() {
                    zzqf.zzbf("Disconnecting from CustomTabs service.");
                }
            });
            this.zzLP.zze(this.zzLO);
            this.zzLQ.onAdLoaded(this);
        }
    }

    public void showInterstitial() {
        CustomTabsIntent build = new CustomTabsIntent.Builder(this.zzLP.zzfH()).build();
        build.intent.setData(this.mUri);
        final AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(new zzc(build.intent), (zzdx) null, new zzh() {
            public void onPause() {
                zzqf.zzbf("AdMobCustomTabsAdapter overlay is paused.");
            }

            public void onResume() {
                zzqf.zzbf("AdMobCustomTabsAdapter overlay is resumed.");
            }

            public void zzbN() {
                zzqf.zzbf("AdMobCustomTabsAdapter overlay is closed.");
                zzkp.this.zzLQ.onAdClosed(zzkp.this);
                try {
                    zzkp.this.zzLP.zzd(zzkp.this.zzLO);
                } catch (Exception e) {
                    zzqf.zzb("Exception while unbinding from CustomTabsService.", e);
                }
            }

            public void zzbO() {
                zzqf.zzbf("Opening AdMobCustomTabsAdapter overlay.");
                zzkp.this.zzLQ.onAdOpened(zzkp.this);
            }
        }, (zzq) null, new zzqh(0, 0, false));
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzw.zzcK().zza(zzkp.this.zzLO, adOverlayInfoParcel);
            }
        });
        zzw.zzcQ().zzH(false);
    }
}

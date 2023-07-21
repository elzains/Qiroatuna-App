package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzhf;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzhk extends NativeAppInstallAd {
    private final VideoController zzAl = new VideoController();
    private final zzhj zzHr;
    private final List<NativeAd.Image> zzHs = new ArrayList();
    private final zzhg zzHt;

    public zzhk(zzhj zzhj) {
        zzhg zzhg;
        this.zzHr = zzhj;
        try {
            List<Object> images = this.zzHr.getImages();
            if (images != null) {
                for (Object zze : images) {
                    zzhf zze2 = zze(zze);
                    if (zze2 != null) {
                        this.zzHs.add(new zzhg(zze2));
                    }
                }
            }
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get image.", e);
        }
        try {
            zzhf zzfQ = this.zzHr.zzfQ();
            if (zzfQ != null) {
                zzhg = new zzhg(zzfQ);
                this.zzHt = zzhg;
            }
        } catch (RemoteException e2) {
            zzqf.zzb("Failed to get icon.", e2);
        }
        zzhg = null;
        this.zzHt = zzhg;
    }

    public void destroy() {
        try {
            this.zzHr.destroy();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzHr.getBody();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzHr.getCallToAction();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzHr.getExtras();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzHr.getHeadline();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public NativeAd.Image getIcon() {
        return this.zzHt;
    }

    public List<NativeAd.Image> getImages() {
        return this.zzHs;
    }

    public CharSequence getPrice() {
        try {
            return this.zzHr.getPrice();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get price.", e);
            return null;
        }
    }

    public Double getStarRating() {
        try {
            double starRating = this.zzHr.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get star rating.", e);
            return null;
        }
    }

    public CharSequence getStore() {
        try {
            return this.zzHr.getStore();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get store", e);
            return null;
        }
    }

    public VideoController getVideoController() {
        try {
            if (this.zzHr.zzbF() != null) {
                this.zzAl.zza(this.zzHr.zzbF());
            }
        } catch (RemoteException e) {
            zzqf.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzAl;
    }

    /* access modifiers changed from: package-private */
    public zzhf zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzhf.zza.zzB((IBinder) obj);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfR */
    public IObjectWrapper zzbu() {
        try {
            return this.zzHr.zzfR();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}

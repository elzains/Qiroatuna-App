package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;

@zzme
public class zzhg extends NativeAd.Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzGo;
    private final zzhf zzHq;

    public zzhg(zzhf zzhf) {
        Drawable drawable;
        Uri uri = null;
        this.zzHq = zzhf;
        try {
            IObjectWrapper zzfP = this.zzHq.zzfP();
            if (zzfP != null) {
                drawable = (Drawable) zzd.zzF(zzfP);
                this.mDrawable = drawable;
                uri = this.zzHq.getUri();
                this.mUri = uri;
                double d = 1.0d;
                d = this.zzHq.getScale();
                this.zzGo = d;
            }
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get drawable.", e);
        }
        drawable = null;
        this.mDrawable = drawable;
        try {
            uri = this.zzHq.getUri();
        } catch (RemoteException e2) {
            zzqf.zzb("Failed to get uri.", e2);
        }
        this.mUri = uri;
        double d2 = 1.0d;
        try {
            d2 = this.zzHq.getScale();
        } catch (RemoteException e3) {
            zzqf.zzb("Failed to get scale.", e3);
        }
        this.zzGo = d2;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public double getScale() {
        return this.zzGo;
    }

    public Uri getUri() {
        return this.mUri;
    }
}

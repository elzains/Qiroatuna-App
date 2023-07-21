package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzhf;

@zzme
public class zzgu extends zzhf.zza {
    private final Uri mUri;
    private final Drawable zzGn;
    private final double zzGo;

    public zzgu(Drawable drawable, Uri uri, double d) {
        this.zzGn = drawable;
        this.mUri = uri;
        this.zzGo = d;
    }

    public double getScale() {
        return this.zzGo;
    }

    public Uri getUri() throws RemoteException {
        return this.mUri;
    }

    public IObjectWrapper zzfP() throws RemoteException {
        return zzd.zzA(this.zzGn);
    }
}

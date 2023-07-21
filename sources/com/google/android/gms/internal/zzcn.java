package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzco;
import com.google.android.gms.internal.zzcp;

public final class zzcn extends zzf<zzcp> {
    private static final zzcn zzrS = new zzcn();

    private zzcn() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = zzrS.zzc(r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzco zzb(java.lang.String r1, android.content.Context r2, boolean r3) {
        /*
            com.google.android.gms.common.zze r0 = com.google.android.gms.common.zze.zzuY()
            int r0 = r0.isGooglePlayServicesAvailable(r2)
            if (r0 != 0) goto L_0x0012
            com.google.android.gms.internal.zzcn r0 = zzrS
            com.google.android.gms.internal.zzco r0 = r0.zzc(r1, r2, r3)
            if (r0 != 0) goto L_0x0017
        L_0x0012:
            com.google.android.gms.internal.zzcm r0 = new com.google.android.gms.internal.zzcm
            r0.<init>(r1, r2, r3)
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcn.zzb(java.lang.String, android.content.Context, boolean):com.google.android.gms.internal.zzco");
    }

    private zzco zzc(String str, Context context, boolean z) {
        IBinder zzb;
        IObjectWrapper zzA = zzd.zzA(context);
        if (z) {
            try {
                zzb = ((zzcp) zzbl(context)).zza(str, zzA);
            } catch (RemoteException | zzf.zza e) {
                return null;
            }
        } else {
            zzb = ((zzcp) zzbl(context)).zzb(str, zzA);
        }
        return zzco.zza.zzd(zzb);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzb */
    public zzcp zzc(IBinder iBinder) {
        return zzcp.zza.zze(iBinder);
    }
}

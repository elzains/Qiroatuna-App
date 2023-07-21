package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;
import com.google.android.gms.internal.zzaqd;

public class zzaqc {
    private zzaqd zzaXk = null;
    private boolean zztZ = false;

    public void initialize(Context context) {
        synchronized (this) {
            if (!this.zztZ) {
                try {
                    this.zzaXk = zzaqd.zza.asInterface(DynamiteModule.zza(context, DynamiteModule.zzaRY, ModuleDescriptor.MODULE_ID).zzdT("com.google.android.gms.flags.impl.FlagProviderImpl"));
                    this.zzaXk.init(zzd.zzA(context));
                    this.zztZ = true;
                } catch (RemoteException | DynamiteModule.zza e) {
                    Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
                }
                return;
            }
            return;
        }
    }

    public <T> T zzb(zzaqa<T> zzaqa) {
        synchronized (this) {
            if (this.zztZ) {
                return zzaqa.zza(this.zzaXk);
            }
            T zzfr = zzaqa.zzfr();
            return zzfr;
        }
    }
}

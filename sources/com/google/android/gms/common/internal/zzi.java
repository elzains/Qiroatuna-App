package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.internal.zzabf;

public abstract class zzi implements DialogInterface.OnClickListener {
    public static zzi zza(final Activity activity, final Intent intent, final int i) {
        return new zzi() {
            public void zzxT() {
                if (intent != null) {
                    activity.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzi zza(@NonNull final Fragment fragment, final Intent intent, final int i) {
        return new zzi() {
            public void zzxT() {
                if (intent != null) {
                    fragment.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzi zza(@NonNull final zzabf zzabf, final Intent intent, final int i) {
        return new zzi() {
            public void zzxT() {
                if (intent != null) {
                    zzabf.startActivityForResult(intent, i);
                }
            }
        };
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zzxT();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzxT();
}

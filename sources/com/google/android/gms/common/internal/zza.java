package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzg;

public class zza extends zzr.zza {
    int zzaEV;

    public static Account zza(zzr zzr) {
        Account account = null;
        if (zzr != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = zzr.getAccount();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public boolean equals(Object obj) {
        Account account = null;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        return account.equals(account);
    }

    public Account getAccount() {
        int callingUid = Binder.getCallingUid();
        if (callingUid != this.zzaEV) {
            if (zzg.zzf((Context) null, callingUid)) {
                this.zzaEV = callingUid;
            } else {
                throw new SecurityException("Caller is not GooglePlayServices");
            }
        }
        return null;
    }
}

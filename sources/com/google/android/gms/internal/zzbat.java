package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbaq;

public class zzbat extends zzl<zzbaq> implements zzbai {
    private final zzg zzaAL;
    private Integer zzaFD;
    private final Bundle zzbEl;
    private final boolean zzbEw;

    public zzbat(Context context, Looper looper, boolean z, zzg zzg, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzbEw = z;
        this.zzaAL = zzg;
        this.zzbEl = bundle;
        this.zzaFD = zzg.zzxS();
    }

    public zzbat(Context context, Looper looper, boolean z, zzg zzg, zzbaj zzbaj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, zzg, zza(zzg), connectionCallbacks, onConnectionFailedListener);
    }

    private zzad zzPR() {
        Account zzxB = this.zzaAL.zzxB();
        GoogleSignInAccount googleSignInAccount = null;
        if ("<<default account>>".equals(zzxB.name)) {
            googleSignInAccount = zzn.zzas(getContext()).zzrB();
        }
        return new zzad(zzxB, this.zzaFD.intValue(), googleSignInAccount);
    }

    public static Bundle zza(zzg zzg) {
        zzbaj zzxR = zzg.zzxR();
        Integer zzxS = zzg.zzxS();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzg.getAccount());
        if (zzxS != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzxS.intValue());
        }
        if (zzxR != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzxR.zzPJ());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzxR.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzxR.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzxR.zzrl());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzxR.zzrm());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzxR.zzPK());
            if (zzxR.zzPL() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzxR.zzPL().longValue());
            }
            if (zzxR.zzPM() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzxR.zzPM().longValue());
            }
        }
        return bundle;
    }

    public void connect() {
        zza((zzf.C0768zzf) new zzf.zzi());
    }

    public void zzPI() {
        try {
            ((zzbaq) zzxD()).zznv(this.zzaFD.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void zza(zzr zzr, boolean z) {
        try {
            ((zzbaq) zzxD()).zza(zzr, this.zzaFD.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzbap zzbap) {
        zzac.zzb(zzbap, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zzbaq) zzxD()).zza(new zzbau(zzPR()), zzbap);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzbap.zzb(new zzbaw(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfg */
    public zzbaq zzh(IBinder iBinder) {
        return zzbaq.zza.zzff(iBinder);
    }

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        if (!getContext().getPackageName().equals(this.zzaAL.zzxO())) {
            this.zzbEl.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzaAL.zzxO());
        }
        return this.zzbEl;
    }

    public boolean zzrd() {
        return this.zzbEw;
    }
}

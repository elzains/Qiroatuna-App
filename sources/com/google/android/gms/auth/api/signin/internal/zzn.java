package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzn {
    private static final Lock zzakO = new ReentrantLock();
    private static zzn zzakP;
    private final Lock zzakQ = new ReentrantLock();
    private final SharedPreferences zzakR;

    zzn(Context context) {
        this.zzakR = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzn zzas(Context context) {
        zzac.zzw(context);
        zzakO.lock();
        try {
            if (zzakP == null) {
                zzakP = new zzn(context.getApplicationContext());
            }
            return zzakP;
        } finally {
            zzakO.unlock();
        }
    }

    private String zzy(String str, String str2) {
        String valueOf = String.valueOf(":");
        return new StringBuilder(String.valueOf(str).length() + String.valueOf(valueOf).length() + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    /* access modifiers changed from: package-private */
    public void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzac.zzw(googleSignInAccount);
        zzac.zzw(googleSignInOptions);
        String zzrf = googleSignInAccount.zzrf();
        zzx(zzy("googleSignInAccount", zzrf), googleSignInAccount.zzrh());
        zzx(zzy("googleSignInOptions", zzrf), googleSignInOptions.zzrg());
    }

    public void zzb(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzac.zzw(googleSignInAccount);
        zzac.zzw(googleSignInOptions);
        zzx("defaultGoogleSignInAccount", googleSignInAccount.zzrf());
        zza(googleSignInAccount, googleSignInOptions);
    }

    /* access modifiers changed from: package-private */
    public GoogleSignInOptions zzcA(String str) {
        String zzcB;
        if (TextUtils.isEmpty(str) || (zzcB = zzcB(zzy("googleSignInOptions", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInOptions.zzcx(zzcB);
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String zzcB(String str) {
        this.zzakQ.lock();
        try {
            return this.zzakR.getString(str, (String) null);
        } finally {
            this.zzakQ.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzcC(String str) {
        if (!TextUtils.isEmpty(str)) {
            zzcD(zzy("googleSignInAccount", str));
            zzcD(zzy("googleSignInOptions", str));
        }
    }

    /* access modifiers changed from: protected */
    public void zzcD(String str) {
        this.zzakQ.lock();
        try {
            this.zzakR.edit().remove(str).apply();
        } finally {
            this.zzakQ.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public GoogleSignInAccount zzcz(String str) {
        String zzcB;
        if (TextUtils.isEmpty(str) || (zzcB = zzcB(zzy("googleSignInAccount", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.zzcv(zzcB);
        } catch (JSONException e) {
            return null;
        }
    }

    public GoogleSignInAccount zzrB() {
        return zzcz(zzcB("defaultGoogleSignInAccount"));
    }

    public GoogleSignInOptions zzrC() {
        return zzcA(zzcB("defaultGoogleSignInAccount"));
    }

    public void zzrD() {
        String zzcB = zzcB("defaultGoogleSignInAccount");
        zzcD("defaultGoogleSignInAccount");
        zzcC(zzcB);
    }

    /* access modifiers changed from: protected */
    public void zzx(String str, String str2) {
        this.zzakQ.lock();
        try {
            this.zzakR.edit().putString(str, str2).apply();
        } finally {
            this.zzakQ.unlock();
        }
    }
}

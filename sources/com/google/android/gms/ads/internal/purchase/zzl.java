package com.google.android.gms.ads.internal.purchase;

import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@zzme
public class zzl {
    public static boolean zza(PublicKey publicKey, String str, String str2) {
        try {
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(str.getBytes());
            if (instance.verify(Base64.decode(str2, 0))) {
                return true;
            }
            zzpk.m20e("Signature verification failed.");
            return false;
        } catch (NoSuchAlgorithmException e) {
            zzpk.m20e("NoSuchAlgorithmException.");
            return false;
        } catch (InvalidKeyException e2) {
            zzpk.m20e("Invalid key specification.");
            return false;
        } catch (SignatureException e3) {
            zzpk.m20e("Signature exception.");
            return false;
        }
    }

    public static PublicKey zzaG(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            zzpk.m20e("Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }

    public static boolean zzb(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            return zza(zzaG(str), str2, str3);
        }
        zzpk.m20e("Purchase verification failed: missing data.");
        return false;
    }
}

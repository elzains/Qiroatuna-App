package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

@zzme
public class zzd {
    public static Object[] zza(String str, zzec zzec, String str2, int i, @Nullable zzeg zzeg) {
        HashSet hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            if (zzeg != null) {
                arrayList.add(zzeg.zzzy);
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzec.zzyT));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(zzb(zzec.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzec.zzyU));
        }
        if (hashSet.contains("keywords")) {
            if (zzec.zzyV != null) {
                arrayList.add(zzec.zzyV.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzec.zzyW));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzec.zzyX));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzec.zzyY));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzec.zzyZ);
        }
        if (hashSet.contains("location")) {
            if (zzec.zzzb != null) {
                arrayList.add(zzec.zzzb.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzec.zzzc);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zzb(zzec.zzzd));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zzb(zzec.zzze));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzec.zzzf != null) {
                arrayList.add(zzec.zzzf.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzec.zzzg);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzec.zzzh);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzec.zzzi));
        }
        return arrayList.toArray();
    }

    private static String zzb(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            sb.append(obj == null ? "null" : obj instanceof Bundle ? zzb((Bundle) obj) : obj.toString());
        }
        return sb.toString();
    }
}

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbaj;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzg {
    private final Set<Scope> zzaFA;
    private final Map<Api<?>, zza> zzaFB;
    private final zzbaj zzaFC;
    private Integer zzaFD;
    private final Account zzahh;
    private final String zzaiq;
    private final Set<Scope> zzazd;
    private final int zzazf;
    private final View zzazg;
    private final String zzazh;

    public static final class zza {
        public final Set<Scope> zzakq;

        public zza(Set<Scope> set) {
            zzac.zzw(set);
            this.zzakq = Collections.unmodifiableSet(set);
        }
    }

    public zzg(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zzbaj zzbaj) {
        this.zzahh = account;
        this.zzazd = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.zzaFB = map == null ? Collections.EMPTY_MAP : map;
        this.zzazg = view;
        this.zzazf = i;
        this.zzaiq = str;
        this.zzazh = str2;
        this.zzaFC = zzbaj;
        HashSet hashSet = new HashSet(this.zzazd);
        for (zza zza2 : this.zzaFB.values()) {
            hashSet.addAll(zza2.zzakq);
        }
        this.zzaFA = Collections.unmodifiableSet(hashSet);
    }

    public static zzg zzaS(Context context) {
        return new GoogleApiClient.Builder(context).zzvp();
    }

    public Account getAccount() {
        return this.zzahh;
    }

    @Deprecated
    public String getAccountName() {
        if (this.zzahh != null) {
            return this.zzahh.name;
        }
        return null;
    }

    public Set<Scope> zzc(Api<?> api) {
        zza zza2 = this.zzaFB.get(api);
        if (zza2 == null || zza2.zzakq.isEmpty()) {
            return this.zzazd;
        }
        HashSet hashSet = new HashSet(this.zzazd);
        hashSet.addAll(zza2.zzakq);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.zzaFD = num;
    }

    public Account zzxB() {
        return this.zzahh != null ? this.zzahh : new Account("<<default account>>", "com.google");
    }

    public int zzxK() {
        return this.zzazf;
    }

    public Set<Scope> zzxL() {
        return this.zzazd;
    }

    public Set<Scope> zzxM() {
        return this.zzaFA;
    }

    public Map<Api<?>, zza> zzxN() {
        return this.zzaFB;
    }

    public String zzxO() {
        return this.zzaiq;
    }

    public String zzxP() {
        return this.zzazh;
    }

    public View zzxQ() {
        return this.zzazg;
    }

    public zzbaj zzxR() {
        return this.zzaFC;
    }

    public Integer zzxS() {
        return this.zzaFD;
    }
}

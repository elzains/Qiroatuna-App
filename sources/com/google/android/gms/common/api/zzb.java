package com.google.android.gms.common.api;

import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzzz;
import java.util.ArrayList;

public class zzb extends Exception {
    private final ArrayMap<zzzz<?>, ConnectionResult> zzayL;

    public zzb(ArrayMap<zzzz<?>, ConnectionResult> arrayMap) {
        this.zzayL = arrayMap;
    }

    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzzz next : this.zzayL.keySet()) {
            ConnectionResult connectionResult = this.zzayL.get(next);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String valueOf = String.valueOf(next.zzvw());
            String valueOf2 = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(valueOf2).length()).append(valueOf).append(": ").append(valueOf2).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    public ConnectionResult zza(zzc<? extends Api.ApiOptions> zzc) {
        zzzz<? extends Api.ApiOptions> apiKey = zzc.getApiKey();
        zzac.zzb(this.zzayL.get(apiKey) != null, (Object) "The given API was not part of the availability request.");
        return this.zzayL.get(apiKey);
    }

    public ArrayMap<zzzz<?>, ConnectionResult> zzvj() {
        return this.zzayL;
    }
}

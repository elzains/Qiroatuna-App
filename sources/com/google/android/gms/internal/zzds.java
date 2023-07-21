package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

@zzme
public class zzds extends zza {
    public static final Parcelable.Creator<zzds> CREATOR = new zzdt();
    @Nullable
    public final String url;
    public final long zzyL;
    public final String zzyM;
    public final String zzyN;
    public final String zzyO;
    public final Bundle zzyP;
    public final boolean zzyQ;

    zzds(@Nullable String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z) {
        this.url = str;
        this.zzyL = j;
        this.zzyM = str2 == null ? "" : str2;
        this.zzyN = str3 == null ? "" : str3;
        this.zzyO = str4 == null ? "" : str4;
        this.zzyP = bundle == null ? new Bundle() : bundle;
        this.zzyQ = z;
    }

    @Nullable
    public static zzds zzJ(String str) {
        return zze(Uri.parse(str));
    }

    @Nullable
    public static zzds zze(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzpk.zzbh(new StringBuilder(62).append("Expected 2 path parts for namespace and id, found :").append(pathSegments.size()).toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String next : zzw.zzcO().zzh(uri)) {
                if (next.startsWith("tag.")) {
                    bundle.putString(next.substring("tag.".length()), uri.getQueryParameter(next));
                }
            }
            return new zzds(queryParameter, parseLong, host, str, str2, bundle, equals);
        } catch (NullPointerException | NumberFormatException e) {
            zzpk.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdt.zza(this, parcel, i);
    }
}

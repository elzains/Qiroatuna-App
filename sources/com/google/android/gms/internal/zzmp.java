package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zzmp extends zza {
    public static final Parcelable.Creator<zzmp> CREATOR = new zzmq();
    public final boolean zzSL;
    @Nullable
    public final List<String> zzSM;

    public zzmp() {
        this(false, Collections.emptyList());
    }

    public zzmp(boolean z) {
        this(z, Collections.emptyList());
    }

    public zzmp(boolean z, List<String> list) {
        this.zzSL = z;
        this.zzSM = list;
    }

    @Nullable
    public static zzmp zzf(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new zzmp();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("reporting_urls");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(optJSONArray.getString(i));
                } catch (JSONException e) {
                    zzpk.zzc("Error grabbing url from json.", e);
                }
            }
        }
        return new zzmp(jSONObject.optBoolean("enable_protection"), arrayList);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzmq.zza(this, parcel, i);
    }
}

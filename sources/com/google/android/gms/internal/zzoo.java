package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zzoo extends zza {
    public static final Parcelable.Creator<zzoo> CREATOR = new zzop();
    public final String type;
    public final int zzVP;

    public zzoo(RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }

    public zzoo(String str, int i) {
        this.type = str;
        this.zzVP = i;
    }

    @Nullable
    public static zzoo zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        return new zzoo(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    @Nullable
    public static zzoo zzaR(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return zza(new JSONArray(str));
        } catch (JSONException e) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzoo)) {
            return false;
        }
        zzoo zzoo = (zzoo) obj;
        return zzaa.equal(this.type, zzoo.type) && zzaa.equal(Integer.valueOf(this.zzVP), Integer.valueOf(zzoo.zzVP));
    }

    public int hashCode() {
        return zzaa.hashCode(this.type, Integer.valueOf(this.zzVP));
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzop.zza(this, parcel, i);
    }

    public JSONArray zzjP() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rb_type", this.type);
        jSONObject.put("rb_amount", this.zzVP);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }
}

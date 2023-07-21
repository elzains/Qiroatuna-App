package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzjw {
    public List<String> zza(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void zza(Context context, String str, zzpb zzpb, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? "1" : "0";
            for (String replaceAll : list) {
                String replaceAll2 = replaceAll.replaceAll("@gw_adlocid@", str2).replaceAll("@gw_adnetrefresh@", str3).replaceAll("@gw_qdata@", zzpb.zzWc.zzKK).replaceAll("@gw_sdkver@", str).replaceAll("@gw_sessid@", zzw.zzcQ().getSessionId()).replaceAll("@gw_seqnum@", zzpb.zzRB);
                if (!TextUtils.isEmpty(zzpb.zzWd)) {
                    replaceAll2 = replaceAll2.replaceAll("@gw_adnetstatus@", zzpb.zzWd);
                }
                if (zzpb.zzLi != null) {
                    replaceAll2 = replaceAll2.replaceAll("@gw_adnetid@", zzpb.zzLi.zzKo).replaceAll("@gw_allocid@", zzpb.zzLi.zzKq);
                }
                zzw.zzcM().zzf(context, str, replaceAll2);
            }
        }
    }
}

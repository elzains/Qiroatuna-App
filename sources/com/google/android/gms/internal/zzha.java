package com.google.android.gms.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public interface zzha {

    public interface zza {
        String getCustomTemplateId();

        void zzb(zzha zzha);

        String zzfS();

        zzgs zzfT();

        View zzfU();
    }

    public interface zzb extends zza {
        List getImages();
    }

    Context getContext();

    View zza(View.OnClickListener onClickListener, boolean z);

    void zza(View view, zzgy zzgy);

    void zza(View view, String str, JSONObject jSONObject, Map<String, WeakReference<View>> map, View view2);

    void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, View view2);

    void zzb(View view, Map<String, WeakReference<View>> map);

    void zzc(View view, Map<String, WeakReference<View>> map);

    void zzd(MotionEvent motionEvent);

    void zzd(View view, Map<String, WeakReference<View>> map);

    boolean zzfY();

    View zzgc();

    void zzj(View view);
}

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzkw {
    private final zzqw zzIs;
    private final String zzMF;

    public zzkw(zzqw zzqw) {
        this(zzqw, "");
    }

    public zzkw(zzqw zzqw, String str) {
        this.zzIs = zzqw;
        this.zzMF = str;
    }

    public void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zzIs.zzb("onScreenInfoChanged", new JSONObject().put("width", i).put("height", i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put("density", (double) f).put("rotation", i5));
        } catch (JSONException e) {
            zzpk.zzb("Error occured while obtaining screen information.", e);
        }
    }

    public void zzaA(String str) {
        try {
            this.zzIs.zzb("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            zzpk.zzb("Error occured while dispatching ready Event.", e);
        }
    }

    public void zzaB(String str) {
        try {
            this.zzIs.zzb("onStateChanged", new JSONObject().put("state", str));
        } catch (JSONException e) {
            zzpk.zzb("Error occured while dispatching state change.", e);
        }
    }

    public void zzaz(String str) {
        try {
            this.zzIs.zzb("onError", new JSONObject().put("message", str).put("action", this.zzMF));
        } catch (JSONException e) {
            zzpk.zzb("Error occurred while dispatching error event.", e);
        }
    }

    public void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zzIs.zzb("onSizeChanged", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzpk.zzb("Error occured while dispatching size change.", e);
        }
    }

    public void zzc(int i, int i2, int i3, int i4) {
        try {
            this.zzIs.zzb("onDefaultPositionReceived", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzpk.zzb("Error occured while dispatching default position.", e);
        }
    }
}

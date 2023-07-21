package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzku {
    private final boolean zzMr;
    private final boolean zzMs;
    private final boolean zzMt;
    private final boolean zzMu;
    private final boolean zzMv;

    public static final class zza {
        /* access modifiers changed from: private */
        public boolean zzMr;
        /* access modifiers changed from: private */
        public boolean zzMs;
        /* access modifiers changed from: private */
        public boolean zzMt;
        /* access modifiers changed from: private */
        public boolean zzMu;
        /* access modifiers changed from: private */
        public boolean zzMv;

        public zzku zzho() {
            return new zzku(this);
        }

        public zza zzt(boolean z) {
            this.zzMr = z;
            return this;
        }

        public zza zzu(boolean z) {
            this.zzMs = z;
            return this;
        }

        public zza zzv(boolean z) {
            this.zzMt = z;
            return this;
        }

        public zza zzw(boolean z) {
            this.zzMu = z;
            return this;
        }

        public zza zzx(boolean z) {
            this.zzMv = z;
            return this;
        }
    }

    private zzku(zza zza2) {
        this.zzMr = zza2.zzMr;
        this.zzMs = zza2.zzMs;
        this.zzMt = zza2.zzMt;
        this.zzMu = zza2.zzMu;
        this.zzMv = zza2.zzMv;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzMr).put("tel", this.zzMs).put("calendar", this.zzMt).put("storePicture", this.zzMu).put("inlineVideo", this.zzMv);
        } catch (JSONException e) {
            zzpk.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}

package com.google.android.gms.internal;

@zzme
public class zzof {
    public final int errorCode;
    public final String zzKq;
    public final long zzLn;
    public final String zzVB;

    public static final class zza {
        /* access modifiers changed from: private */
        public String zzKW;
        /* access modifiers changed from: private */
        public int zzPY;
        /* access modifiers changed from: private */
        public String zzVC;
        /* access modifiers changed from: private */
        public long zzVD;

        public zza zzaP(String str) {
            this.zzKW = str;
            return this;
        }

        public zza zzaQ(String str) {
            this.zzVC = str;
            return this;
        }

        public zza zzae(int i) {
            this.zzPY = i;
            return this;
        }

        public zzof zzjK() {
            return new zzof(this);
        }

        public zza zzl(long j) {
            this.zzVD = j;
            return this;
        }
    }

    private zzof(zza zza2) {
        this.zzVB = zza2.zzKW;
        this.zzKq = zza2.zzVC;
        this.errorCode = zza2.zzPY;
        this.zzLn = zza2.zzVD;
    }
}

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;

public final class zzabh<L> {
    private volatile L mListener;
    private final zza zzaCX;
    private final zzb<L> zzaCY;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            zzac.zzax(z);
            zzabh.this.zzb((zzc) message.obj);
        }
    }

    public static final class zzb<L> {
        private final L mListener;
        private final String zzaDa;

        zzb(L l, String str) {
            this.mListener = l;
            this.zzaDa = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return this.mListener == zzb.mListener && this.zzaDa.equals(zzb.zzaDa);
        }

        public int hashCode() {
            return (System.identityHashCode(this.mListener) * 31) + this.zzaDa.hashCode();
        }
    }

    public interface zzc<L> {
        void zzs(L l);

        void zzwc();
    }

    zzabh(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zzaCX = new zza(looper);
        this.mListener = zzac.zzb(l, (Object) "Listener must not be null");
        this.zzaCY = new zzb<>(l, zzac.zzdr(str));
    }

    public void clear() {
        this.mListener = null;
    }

    public void zza(zzc<? super L> zzc2) {
        zzac.zzb(zzc2, (Object) "Notifier must not be null");
        this.zzaCX.sendMessage(this.zzaCX.obtainMessage(1, zzc2));
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzc<? super L> zzc2) {
        L l = this.mListener;
        if (l == null) {
            zzc2.zzwc();
            return;
        }
        try {
            zzc2.zzs(l);
        } catch (RuntimeException e) {
            zzc2.zzwc();
            throw e;
        }
    }

    public boolean zztK() {
        return this.mListener != null;
    }

    @NonNull
    public zzb<L> zzwW() {
        return this.zzaCY;
    }
}

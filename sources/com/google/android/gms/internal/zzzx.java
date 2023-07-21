package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaax;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzzx {
    public final int zzakD;

    private static abstract class zza extends zzzx {
        protected final TaskCompletionSource<Void> zzazE;

        public zza(int i, TaskCompletionSource<Void> taskCompletionSource) {
            super(i);
            this.zzazE = taskCompletionSource;
        }

        public void zza(@NonNull zzaal zzaal, boolean z) {
        }

        public final void zza(zzaax.zza<?> zza) throws DeadObjectException {
            try {
                zzb(zza);
            } catch (DeadObjectException e) {
                zzz(zzzx.zza((RemoteException) e));
                throw e;
            } catch (RemoteException e2) {
                zzz(zzzx.zza(e2));
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzb(zzaax.zza<?> zza) throws RemoteException;

        public void zzz(@NonNull Status status) {
            this.zzazE.trySetException(new com.google.android.gms.common.api.zza(status));
        }
    }

    public static class zzb<A extends zzaad.zza<? extends Result, Api.zzb>> extends zzzx {
        protected final A zzazF;

        public zzb(int i, A a) {
            super(i);
            this.zzazF = a;
        }

        public void zza(@NonNull zzaal zzaal, boolean z) {
            zzaal.zza((zzaaf<? extends Result>) this.zzazF, z);
        }

        public void zza(zzaax.zza<?> zza) throws DeadObjectException {
            this.zzazF.zzb(zza.zzvU());
        }

        public void zzz(@NonNull Status status) {
            this.zzazF.zzB(status);
        }
    }

    public static final class zzc extends zza {
        public final zzabm<Api.zzb, ?> zzazG;
        public final zzabz<Api.zzb, ?> zzazH;

        public zzc(zzabn zzabn, TaskCompletionSource<Void> taskCompletionSource) {
            super(3, taskCompletionSource);
            this.zzazG = zzabn.zzazG;
            this.zzazH = zzabn.zzazH;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzaal zzaal, boolean z) {
            super.zza(zzaal, z);
        }

        public void zzb(zzaax.zza<?> zza) throws RemoteException {
            if (this.zzazG.zzwW() != null) {
                zza.zzwI().put(this.zzazG.zzwW(), new zzabn(this.zzazG, this.zzazH));
            }
        }

        public /* bridge */ /* synthetic */ void zzz(@NonNull Status status) {
            super.zzz(status);
        }
    }

    public static final class zzd<TResult> extends zzzx {
        private final TaskCompletionSource<TResult> zzazE;
        private final zzabv<Api.zzb, TResult> zzazI;
        private final zzabs zzazJ;

        public zzd(int i, zzabv<Api.zzb, TResult> zzabv, TaskCompletionSource<TResult> taskCompletionSource, zzabs zzabs) {
            super(i);
            this.zzazE = taskCompletionSource;
            this.zzazI = zzabv;
            this.zzazJ = zzabs;
        }

        public void zza(@NonNull zzaal zzaal, boolean z) {
            zzaal.zza(this.zzazE, z);
        }

        public void zza(zzaax.zza<?> zza) throws DeadObjectException {
            try {
                this.zzazI.zza(zza.zzvU(), this.zzazE);
            } catch (DeadObjectException e) {
                throw e;
            } catch (RemoteException e2) {
                zzz(zzzx.zza(e2));
            }
        }

        public void zzz(@NonNull Status status) {
            this.zzazE.trySetException(this.zzazJ.zzA(status));
        }
    }

    public static final class zze extends zza {
        public final zzabh.zzb<?> zzazK;

        public zze(zzabh.zzb<?> zzb, TaskCompletionSource<Void> taskCompletionSource) {
            super(4, taskCompletionSource);
            this.zzazK = zzb;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzaal zzaal, boolean z) {
            super.zza(zzaal, z);
        }

        public void zzb(zzaax.zza<?> zza) throws RemoteException {
            zzabn remove = zza.zzwI().remove(this.zzazK);
            if (remove != null) {
                remove.zzazG.zzwX();
                return;
            }
            Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
            this.zzazE.trySetException(new com.google.android.gms.common.api.zza(Status.zzazz));
        }

        public /* bridge */ /* synthetic */ void zzz(@NonNull Status status) {
            super.zzz(status);
        }
    }

    public zzzx(int i) {
        this.zzakD = i;
    }

    /* access modifiers changed from: private */
    public static Status zza(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (zzt.zzzh() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zza(@NonNull zzaal zzaal, boolean z);

    public abstract void zza(zzaax.zza<?> zza2) throws DeadObjectException;

    public abstract void zzz(@NonNull Status status);
}

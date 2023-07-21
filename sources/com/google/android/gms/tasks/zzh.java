package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzabe;
import com.google.android.gms.internal.zzabf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult> extends Task<TResult> {
    private final zzg<TResult> zzbNI = new zzg<>();
    private boolean zzbNJ;
    private TResult zzbNK;
    private Exception zzbNL;
    private final Object zzrJ = new Object();

    private static class zza extends zzabe {
        private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();

        private zza(zzabf zzabf) {
            super(zzabf);
            this.zzaCR.zza("TaskOnStopCallback", (zzabe) this);
        }

        public static zza zzw(Activity activity) {
            zzabf zzs = zzs(activity);
            zza zza = (zza) zzs.zza("TaskOnStopCallback", zza.class);
            return zza == null ? new zza(zzs) : zza;
        }

        @MainThread
        public void onStop() {
            synchronized (this.mListeners) {
                for (WeakReference<zzf<?>> weakReference : this.mListeners) {
                    zzf zzf = (zzf) weakReference.get();
                    if (zzf != null) {
                        zzf.cancel();
                    }
                }
                this.mListeners.clear();
            }
        }

        public <T> void zzb(zzf<T> zzf) {
            synchronized (this.mListeners) {
                this.mListeners.add(new WeakReference(zzf));
            }
        }
    }

    zzh() {
    }

    private void zzTF() {
        zzac.zza(this.zzbNJ, (Object) "Task is not yet complete");
    }

    private void zzTG() {
        zzac.zza(!this.zzbNJ, (Object) "Task is already complete");
    }

    private void zzTH() {
        synchronized (this.zzrJ) {
            if (this.zzbNJ) {
                this.zzbNI.zza(this);
            }
        }
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zzc zzc = new zzc(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzbNI.zza(zzc);
        zza.zzw(activity).zzb(zzc);
        zzTH();
        return this;
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzbNI.zza(new zzc(executor, onCompleteListener));
        zzTH();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzd zzd = new zzd(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzbNI.zza(zzd);
        zza.zzw(activity).zzb(zzd);
        zzTH();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbNI.zza(new zzd(executor, onFailureListener));
        zzTH();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zze zze = new zze(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzbNI.zza(zze);
        zza.zzw(activity).zzb(zze);
        zzTH();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbNI.zza(new zze(executor, onSuccessListener));
        zzTH();
        return this;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzh zzh = new zzh();
        this.zzbNI.zza(new zza(executor, continuation, zzh));
        zzTH();
        return zzh;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzh zzh = new zzh();
        this.zzbNI.zza(new zzb(executor, continuation, zzh));
        zzTH();
        return zzh;
    }

    @Nullable
    public Exception getException() {
        Exception exc;
        synchronized (this.zzrJ) {
            exc = this.zzbNL;
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.zzrJ) {
            zzTF();
            if (this.zzbNL != null) {
                throw new RuntimeExecutionException(this.zzbNL);
            }
            tresult = this.zzbNK;
        }
        return tresult;
    }

    public <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.zzrJ) {
            zzTF();
            if (cls.isInstance(this.zzbNL)) {
                throw ((Throwable) cls.cast(this.zzbNL));
            } else if (this.zzbNL != null) {
                throw new RuntimeExecutionException(this.zzbNL);
            } else {
                tresult = this.zzbNK;
            }
        }
        return tresult;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNJ;
        }
        return z;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNJ && this.zzbNL == null;
        }
        return z;
    }

    public void setException(@NonNull Exception exc) {
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            zzTG();
            this.zzbNJ = true;
            this.zzbNL = exc;
        }
        this.zzbNI.zza(this);
    }

    public void setResult(TResult tresult) {
        synchronized (this.zzrJ) {
            zzTG();
            this.zzbNJ = true;
            this.zzbNK = tresult;
        }
        this.zzbNI.zza(this);
    }

    public boolean trySetException(@NonNull Exception exc) {
        boolean z = true;
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            if (this.zzbNJ) {
                z = false;
            } else {
                this.zzbNJ = true;
                this.zzbNL = exc;
                this.zzbNI.zza(this);
            }
        }
        return z;
    }

    public boolean trySetResult(TResult tresult) {
        boolean z = true;
        synchronized (this.zzrJ) {
            if (this.zzbNJ) {
                z = false;
            } else {
                this.zzbNJ = true;
                this.zzbNK = tresult;
                this.zzbNI.zza(this);
            }
        }
        return z;
    }
}

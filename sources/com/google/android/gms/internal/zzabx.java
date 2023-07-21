package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import java.lang.ref.WeakReference;

public class zzabx<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public final Object zzaAh = new Object();
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zzaAj;
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zzaDl = null;
    /* access modifiers changed from: private */
    public zzabx<? extends Result> zzaDm = null;
    private volatile ResultCallbacks<? super R> zzaDn = null;
    private PendingResult<R> zzaDo = null;
    private Status zzaDp = null;
    /* access modifiers changed from: private */
    public final zza zzaDq;
    private boolean zzaDr = false;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    PendingResult pendingResult = (PendingResult) message.obj;
                    synchronized (zzabx.this.zzaAh) {
                        if (pendingResult == null) {
                            zzabx.this.zzaDm.zzE(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzabp) {
                            zzabx.this.zzaDm.zzE(((zzabp) pendingResult).getStatus());
                        } else {
                            zzabx.this.zzaDm.zza(pendingResult);
                        }
                    }
                    return;
                case 1:
                    RuntimeException runtimeException = (RuntimeException) message.obj;
                    String valueOf = String.valueOf(runtimeException.getMessage());
                    Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what).toString());
                    return;
            }
        }
    }

    public zzabx(WeakReference<GoogleApiClient> weakReference) {
        zzac.zzb(weakReference, (Object) "GoogleApiClient reference must not be null");
        this.zzaAj = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zzaAj.get();
        this.zzaDq = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public void zzE(Status status) {
        synchronized (this.zzaAh) {
            this.zzaDp = status;
            zzF(this.zzaDp);
        }
    }

    private void zzF(Status status) {
        synchronized (this.zzaAh) {
            if (this.zzaDl != null) {
                Status onFailure = this.zzaDl.onFailure(status);
                zzac.zzb(onFailure, (Object) "onFailure must not return null");
                this.zzaDm.zzE(onFailure);
            } else if (zzxc()) {
                this.zzaDn.onFailure(status);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private void zzxa() {
        if (this.zzaDl != null || this.zzaDn != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzaAj.get();
            if (!(this.zzaDr || this.zzaDl == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzaDr = true;
            }
            if (this.zzaDp != null) {
                zzF(this.zzaDp);
            } else if (this.zzaDo != null) {
                this.zzaDo.setResultCallback(this);
            }
        }
    }

    private boolean zzxc() {
        return (this.zzaDn == null || ((GoogleApiClient) this.zzaAj.get()) == null) ? false : true;
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.zzaAh) {
            zzac.zza(this.zzaDn == null, (Object) "Cannot call andFinally() twice.");
            if (this.zzaDl != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaDn = resultCallbacks;
            zzxa();
        }
    }

    public void onResult(final R r) {
        synchronized (this.zzaAh) {
            if (!r.getStatus().isSuccess()) {
                zzE(r.getStatus());
                zzd((Result) r);
            } else if (this.zzaDl != null) {
                zzabo.zzwv().submit(new Runnable() {
                    @WorkerThread
                    public void run() {
                        try {
                            zzaaf.zzaAg.set(true);
                            zzabx.this.zzaDq.sendMessage(zzabx.this.zzaDq.obtainMessage(0, zzabx.this.zzaDl.onSuccess(r)));
                            zzaaf.zzaAg.set(false);
                            zzabx.this.zzd(r);
                            GoogleApiClient googleApiClient = (GoogleApiClient) zzabx.this.zzaAj.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzabx.this);
                            }
                        } catch (RuntimeException e) {
                            zzabx.this.zzaDq.sendMessage(zzabx.this.zzaDq.obtainMessage(1, e));
                            zzaaf.zzaAg.set(false);
                            zzabx.this.zzd(r);
                            GoogleApiClient googleApiClient2 = (GoogleApiClient) zzabx.this.zzaAj.get();
                            if (googleApiClient2 != null) {
                                googleApiClient2.zzb(zzabx.this);
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            zzaaf.zzaAg.set(false);
                            zzabx.this.zzd(r);
                            GoogleApiClient googleApiClient3 = (GoogleApiClient) zzabx.this.zzaAj.get();
                            if (googleApiClient3 != null) {
                                googleApiClient3.zzb(zzabx.this);
                            }
                            throw th2;
                        }
                    }
                });
            } else if (zzxc()) {
                this.zzaDn.onSuccess(r);
            }
        }
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zzabx<? extends Result> zzabx;
        boolean z = true;
        synchronized (this.zzaAh) {
            zzac.zza(this.zzaDl == null, (Object) "Cannot call then() twice.");
            if (this.zzaDn != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaDl = resultTransform;
            zzabx = new zzabx<>(this.zzaAj);
            this.zzaDm = zzabx;
            zzxa();
        }
        return zzabx;
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzaAh) {
            this.zzaDo = pendingResult;
            zzxa();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzxb() {
        this.zzaDn = null;
    }
}

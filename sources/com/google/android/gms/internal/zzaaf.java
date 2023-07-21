package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzaby;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzaaf<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzaAg = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: zzvJ */
        public Boolean initialValue() {
            return false;
        }
    };
    private boolean zzK;
    private final Object zzaAh;
    protected final zza<R> zzaAi;
    protected final WeakReference<GoogleApiClient> zzaAj;
    private final ArrayList<PendingResult.zza> zzaAk;
    private ResultCallback<? super R> zzaAl;
    private final AtomicReference<zzaby.zzb> zzaAm;
    private zzb zzaAn;
    private volatile boolean zzaAo;
    private boolean zzaAp;
    private zzs zzaAq;
    private volatile zzabx<R> zzaAr;
    private boolean zzaAs;
    private Status zzair;
    /* access modifiers changed from: private */
    public R zzazt;
    private final CountDownLatch zztj;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzaaf) message.obj).zzC(Status.zzazA);
                    return;
                default:
                    Log.wtf("BasePendingResult", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzaaf<R> zzaaf, long j) {
            sendMessageDelayed(obtainMessage(2, zzaaf), j);
        }

        /* access modifiers changed from: protected */
        public void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzaaf.zzd(r);
                throw e;
            }
        }

        public void zzvK() {
            removeMessages(2);
        }
    }

    private final class zzb {
        private zzb() {
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            zzaaf.zzd(zzaaf.this.zzazt);
            super.finalize();
        }
    }

    @Deprecated
    zzaaf() {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(Looper.getMainLooper());
        this.zzaAj = new WeakReference<>((Object) null);
    }

    @Deprecated
    protected zzaaf(Looper looper) {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(looper);
        this.zzaAj = new WeakReference<>((Object) null);
    }

    protected zzaaf(GoogleApiClient googleApiClient) {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzaAj = new WeakReference<>(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.zzaAh) {
            if (this.zzaAo) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed.");
            zzac.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzazt;
            this.zzazt = null;
            this.zzaAl = null;
            this.zzaAo = true;
        }
        zzvG();
        return r;
    }

    private void zzc(R r) {
        this.zzazt = r;
        this.zzaAq = null;
        this.zztj.countDown();
        this.zzair = this.zzazt.getStatus();
        if (this.zzK) {
            this.zzaAl = null;
        } else if (this.zzaAl != null) {
            this.zzaAi.zzvK();
            this.zzaAi.zza(this.zzaAl, get());
        } else if (this.zzazt instanceof Releasable) {
            this.zzaAn = new zzb();
        }
        Iterator<PendingResult.zza> it = this.zzaAk.iterator();
        while (it.hasNext()) {
            it.next().zzy(this.zzair);
        }
        this.zzaAk.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private void zzvG() {
        zzaby.zzb andSet = this.zzaAm.getAndSet((Object) null);
        if (andSet != null) {
            andSet.zzc(this);
        }
    }

    public final R await() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed");
        if (this.zzaAr != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zztj.await();
        } catch (InterruptedException e) {
            zzC(Status.zzazy);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        zzac.zza(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed.");
        if (this.zzaAr != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zztj.await(j, timeUnit)) {
                zzC(Status.zzazA);
            }
        } catch (InterruptedException e) {
            zzC(Status.zzazy);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r1 = r2.zzaAh
            monitor-enter(r1)
            boolean r0 = r2.zzK     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x000b
            boolean r0 = r2.zzaAo     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.common.internal.zzs r0 = r2.zzaAq     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.internal.zzs r0 = r2.zzaAq     // Catch:{ RemoteException -> 0x002c }
            r0.cancel()     // Catch:{ RemoteException -> 0x002c }
        L_0x0016:
            R r0 = r2.zzazt     // Catch:{ all -> 0x0029 }
            zzd(r0)     // Catch:{ all -> 0x0029 }
            r0 = 1
            r2.zzK = r0     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zzazB     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Result r0 = r2.zzc((com.google.android.gms.common.api.Status) r0)     // Catch:{ all -> 0x0029 }
            r2.zzc(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x000c
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzaAh) {
            z = this.zzK;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zztj.getCount() == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r5.zzaAh
            monitor-enter(r3)
            if (r6 != 0) goto L_0x000c
            r0 = 0
            r5.zzaAl = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r5.zzaAo     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzabx<R> r2 = r5.zzaAr     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r5.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r5.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzaaf$zza<R> r0 = r5.zzaAi     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r5.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r6, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r5.zzaAl = r6     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r6.zzaAh
            monitor-enter(r3)
            if (r7 != 0) goto L_0x000c
            r0 = 0
            r6.zzaAl = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r6.zzaAo     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzabx<R> r2 = r6.zzaAr     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r6.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzaaf$zza<R> r0 = r6.zzaAi     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r6.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r7, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r6.zzaAl = r7     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzaaf$zza<R> r0 = r6.zzaAi     // Catch:{ all -> 0x0027 }
            long r4 = r10.toMillis(r8)     // Catch:{ all -> 0x0027 }
            r0.zza(r6, (long) r4)     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed.");
        synchronized (this.zzaAh) {
            zzac.zza(this.zzaAr == null, (Object) "Cannot call then() twice.");
            zzac.zza(this.zzaAl == null, (Object) "Cannot call then() if callbacks are set.");
            if (this.zzK) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() if result was canceled.");
            this.zzaAs = true;
            this.zzaAr = new zzabx<>(this.zzaAj);
            then = this.zzaAr.then(resultTransform);
            if (isReady()) {
                this.zzaAi.zza(this.zzaAr, get());
            } else {
                this.zzaAl = this.zzaAr;
            }
        }
        return then;
    }

    public final void zzC(Status status) {
        synchronized (this.zzaAh) {
            if (!isReady()) {
                zzb(zzc(status));
                this.zzaAp = true;
            }
        }
    }

    public final void zza(PendingResult.zza zza2) {
        zzac.zzb(zza2 != null, (Object) "Callback cannot be null.");
        synchronized (this.zzaAh) {
            if (isReady()) {
                zza2.zzy(this.zzair);
            } else {
                this.zzaAk.add(zza2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzs zzs) {
        synchronized (this.zzaAh) {
            this.zzaAq = zzs;
        }
    }

    public void zza(zzaby.zzb zzb2) {
        this.zzaAm.set(zzb2);
    }

    public final void zzb(R r) {
        boolean z = true;
        synchronized (this.zzaAh) {
            if (this.zzaAp || this.zzK) {
                zzd(r);
                return;
            }
            if (isReady()) {
            }
            zzac.zza(!isReady(), (Object) "Results have already been set");
            if (this.zzaAo) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed");
            zzc(r);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract R zzc(Status status);

    public boolean zzvF() {
        boolean isCanceled;
        synchronized (this.zzaAh) {
            if (((GoogleApiClient) this.zzaAj.get()) == null || !this.zzaAs) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzvH() {
        setResultCallback((ResultCallback) null);
    }

    public void zzvI() {
        this.zzaAs = this.zzaAs || zzaAg.get().booleanValue();
    }

    public Integer zzvr() {
        return null;
    }
}

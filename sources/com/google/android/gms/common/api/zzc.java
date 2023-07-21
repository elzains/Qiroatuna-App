package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaam;
import com.google.android.gms.internal.zzaax;
import com.google.android.gms.internal.zzaay;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabi;
import com.google.android.gms.internal.zzabm;
import com.google.android.gms.internal.zzabr;
import com.google.android.gms.internal.zzabs;
import com.google.android.gms.internal.zzabv;
import com.google.android.gms.internal.zzabz;
import com.google.android.gms.internal.zzzy;
import com.google.android.gms.internal.zzzz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzc<O extends Api.ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Account zzahh;
    private final Api<O> zzaxf;
    private final O zzayT;
    private final zzzz<O> zzayU;
    private final GoogleApiClient zzayV;
    private final zzabs zzayW;
    protected final zzaax zzayX;
    private final Looper zzrs;

    public static class zza {
        public static final zza zzayY = new C0766zza().zzvk();
        public final Account account;
        public final zzabs zzayZ;
        public final Looper zzaza;

        /* renamed from: com.google.android.gms.common.api.zzc$zza$zza  reason: collision with other inner class name */
        public static class C0766zza {
            private zzabs zzayW;
            private Looper zzrs;

            public C0766zza zza(zzabs zzabs) {
                zzac.zzb(zzabs, (Object) "StatusExceptionMapper must not be null.");
                this.zzayW = zzabs;
                return this;
            }

            public C0766zza zzb(Looper looper) {
                zzac.zzb(looper, (Object) "Looper must not be null.");
                this.zzrs = looper;
                return this;
            }

            public zza zzvk() {
                if (this.zzayW == null) {
                    this.zzayW = new zzzy();
                }
                if (this.zzrs == null) {
                    if (Looper.myLooper() != null) {
                        this.zzrs = Looper.myLooper();
                    } else {
                        this.zzrs = Looper.getMainLooper();
                    }
                }
                return new zza(this.zzayW, (Account) null, this.zzrs);
            }
        }

        private zza(zzabs zzabs, Account account2, Looper looper) {
            this.zzayZ = zzabs;
            this.account = account2;
            this.zzaza = looper;
        }
    }

    @Deprecated
    @MainThread
    public zzc(@NonNull Activity activity, Api<O> api, O o, Looper looper, zzabs zzabs) {
        this(activity, api, o, new zza.C0766zza().zzb(looper).zza(zzabs).zzvk());
    }

    @MainThread
    public zzc(@NonNull Activity activity, Api<O> api, O o, zza zza2) {
        zzac.zzb(activity, (Object) "Null activity is not permitted.");
        zzac.zzb(api, (Object) "Api must not be null.");
        zzac.zzb(zza2, (Object) "Settings must not be null; use Settings.createDefault() instead.");
        this.mContext = activity.getApplicationContext();
        this.zzaxf = api;
        this.zzayT = o;
        this.zzrs = zza2.zzaza;
        this.zzayU = zzzz.zza(this.zzaxf, this.zzayT);
        this.zzayV = new zzaay(this);
        this.zzayX = zzaax.zzaP(this.mContext);
        this.mId = this.zzayX.zzwz();
        this.zzayW = zza2.zzayZ;
        this.zzahh = zza2.account;
        zzaam.zza(activity, this.zzayX, this.zzayU);
        this.zzayX.zzb((zzc<?>) this);
    }

    @Deprecated
    public zzc(@NonNull Activity activity, Api<O> api, O o, zzabs zzabs) {
        this(activity, api, o, new zza.C0766zza().zza(zzabs).zzb(activity.getMainLooper()).zzvk());
    }

    protected zzc(@NonNull Context context, Api<O> api, Looper looper) {
        zzac.zzb(context, (Object) "Null context is not permitted.");
        zzac.zzb(api, (Object) "Api must not be null.");
        zzac.zzb(looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.zzaxf = api;
        this.zzayT = null;
        this.zzrs = looper;
        this.zzayU = zzzz.zzb(api);
        this.zzayV = new zzaay(this);
        this.zzayX = zzaax.zzaP(this.mContext);
        this.mId = this.zzayX.zzwz();
        this.zzayW = new zzzy();
        this.zzahh = null;
    }

    @Deprecated
    public zzc(@NonNull Context context, Api<O> api, O o, Looper looper, zzabs zzabs) {
        this(context, api, o, new zza.C0766zza().zzb(looper).zza(zzabs).zzvk());
    }

    public zzc(@NonNull Context context, Api<O> api, O o, zza zza2) {
        zzac.zzb(context, (Object) "Null context is not permitted.");
        zzac.zzb(api, (Object) "Api must not be null.");
        zzac.zzb(zza2, (Object) "Settings must not be null; use Settings.createDefault() instead.");
        this.mContext = context.getApplicationContext();
        this.zzaxf = api;
        this.zzayT = o;
        this.zzrs = zza2.zzaza;
        this.zzayU = zzzz.zza(this.zzaxf, this.zzayT);
        this.zzayV = new zzaay(this);
        this.zzayX = zzaax.zzaP(this.mContext);
        this.mId = this.zzayX.zzwz();
        this.zzayW = zza2.zzayZ;
        this.zzahh = zza2.account;
        this.zzayX.zzb((zzc<?>) this);
    }

    @Deprecated
    public zzc(@NonNull Context context, Api<O> api, O o, zzabs zzabs) {
        this(context, api, o, new zza.C0766zza().zza(zzabs).zzvk());
    }

    private <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzvI();
        this.zzayX.zza(this, i, (zzaad.zza<? extends Result, Api.zzb>) t);
        return t;
    }

    private <TResult, A extends Api.zzb> Task<TResult> zza(int i, @NonNull zzabv<A, TResult> zzabv) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzayX.zza(this, i, zzabv, taskCompletionSource, this.zzayW);
        return taskCompletionSource.getTask();
    }

    public GoogleApiClient asGoogleApiClient() {
        return this.zzayV;
    }

    @WorkerThread
    public Api.zze buildApiClient(Looper looper, zzaax.zza<O> zza2) {
        return this.zzaxf.zzvf().zza(this.mContext, looper, new GoogleApiClient.Builder(this.mContext).zze(this.zzahh).zzvp(), this.zzayT, zza2, zza2);
    }

    public zzabr createSignInCoordinator(Context context, Handler handler) {
        return new zzabr(context, handler);
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doBestEffortWrite(@NonNull T t) {
        return zza(2, t);
    }

    public <TResult, A extends Api.zzb> Task<TResult> doBestEffortWrite(zzabv<A, TResult> zzabv) {
        return zza(2, zzabv);
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doRead(@NonNull T t) {
        return zza(0, t);
    }

    public <TResult, A extends Api.zzb> Task<TResult> doRead(zzabv<A, TResult> zzabv) {
        return zza(0, zzabv);
    }

    public <A extends Api.zzb, T extends zzabm<A, ?>, U extends zzabz<A, ?>> Task<Void> doRegisterEventListener(@NonNull T t, U u) {
        zzac.zzw(t);
        zzac.zzw(u);
        zzac.zzb(t.zzwW(), (Object) "Listener has already been released.");
        zzac.zzb(u.zzwW(), (Object) "Listener has already been released.");
        zzac.zzb(t.zzwW().equals(u.zzwW()), (Object) "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zzayX.zza(this, (zzabm<Api.zzb, ?>) t, (zzabz<Api.zzb, ?>) u);
    }

    public Task<Void> doUnregisterEventListener(@NonNull zzabh.zzb<?> zzb) {
        zzac.zzb(zzb, (Object) "Listener key cannot be null.");
        return this.zzayX.zza(this, zzb);
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doWrite(@NonNull T t) {
        return zza(1, t);
    }

    public <TResult, A extends Api.zzb> Task<TResult> doWrite(zzabv<A, TResult> zzabv) {
        return zza(1, zzabv);
    }

    public Api<O> getApi() {
        return this.zzaxf;
    }

    public zzzz<O> getApiKey() {
        return this.zzayU;
    }

    public O getApiOptions() {
        return this.zzayT;
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.zzrs;
    }

    public <L> zzabh<L> registerListener(@NonNull L l, String str) {
        return zzabi.zzb(l, this.zzrs, str);
    }
}

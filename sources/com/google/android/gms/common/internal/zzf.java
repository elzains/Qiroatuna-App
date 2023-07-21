package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzf<T extends IInterface> {
    public static final String[] zzaFs = {"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final com.google.android.gms.common.zze zzaAQ;
    private int zzaFa;
    private long zzaFb;
    private long zzaFc;
    private int zzaFd;
    private long zzaFe;
    private final zzn zzaFf;
    /* access modifiers changed from: private */
    public final Object zzaFg;
    /* access modifiers changed from: private */
    public zzv zzaFh;
    protected C0768zzf zzaFi;
    private T zzaFj;
    /* access modifiers changed from: private */
    public final ArrayList<zze<?>> zzaFk;
    private zzh zzaFl;
    private int zzaFm;
    /* access modifiers changed from: private */
    public final zzb zzaFn;
    /* access modifiers changed from: private */
    public final zzc zzaFo;
    private final int zzaFp;
    private final String zzaFq;
    protected AtomicInteger zzaFr;
    private final Object zzrJ;
    private final Looper zzrs;

    private abstract class zza extends zze<Boolean> {
        public final int statusCode;
        public final Bundle zzaFt;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzaFt = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public void zzu(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                zzf.this.zza(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzxG()) {
                        zzf.this.zza(1, null);
                        zzm(new ConnectionResult(8, (PendingIntent) null));
                        return;
                    }
                    return;
                case 10:
                    zzf.this.zza(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    zzf.this.zza(1, null);
                    if (this.zzaFt != null) {
                        pendingIntent = (PendingIntent) this.zzaFt.getParcelable("pendingIntent");
                    }
                    zzm(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzm(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzxG();
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        public zzd(Looper looper) {
            super(looper);
        }

        private void zza(Message message) {
            ((zze) message.obj).unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        public void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (zzf.this.zzaFr.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 5) && !zzf.this.isConnecting()) {
                zza(message);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                zzf.this.zzaFi.zzg(connectionResult);
                zzf.this.onConnectionFailed(connectionResult);
            } else if (message.what == 4) {
                zzf.this.zza(4, null);
                if (zzf.this.zzaFn != null) {
                    zzf.this.zzaFn.onConnectionSuspended(message.arg2);
                }
                zzf.this.onConnectionSuspended(message.arg2);
                boolean unused = zzf.this.zza(4, 1, null);
            } else if (message.what == 2 && !zzf.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zze) message.obj).zzxH();
            } else {
                Log.wtf("GmsClient", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
            }
        }
    }

    protected abstract class zze<TListener> {
        private TListener mListener;
        private boolean zzaFv = false;

        public zze(TListener tlistener) {
            this.mListener = tlistener;
        }

        public void unregister() {
            zzxI();
            synchronized (zzf.this.zzaFk) {
                zzf.this.zzaFk.remove(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzu(TListener tlistener);

        public void zzxH() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.zzaFv) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (tlistener != null) {
                try {
                    zzu(tlistener);
                } catch (RuntimeException e) {
                    throw e;
                }
            }
            synchronized (this) {
                this.zzaFv = true;
            }
            unregister();
        }

        public void zzxI() {
            synchronized (this) {
                this.mListener = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$zzf  reason: collision with other inner class name */
    public interface C0768zzf {
        void zzg(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends zzu.zza {
        private zzf zzaFw;
        private final int zzaFx;

        public zzg(@NonNull zzf zzf, int i) {
            this.zzaFw = zzf;
            this.zzaFx = i;
        }

        private void zzxJ() {
            this.zzaFw = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzac.zzb(this.zzaFw, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzaFw.zza(i, iBinder, bundle, this.zzaFx);
            zzxJ();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {
        private final int zzaFx;

        public zzh(int i) {
            this.zzaFx = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                zzf.this.zza(8, (Bundle) null, this.zzaFx);
                return;
            }
            synchronized (zzf.this.zzaFg) {
                zzv unused = zzf.this.zzaFh = zzv.zza.zzbu(iBinder);
            }
            zzf.this.zza(0, (Bundle) null, this.zzaFx);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zzf.this.zzaFg) {
                zzv unused = zzf.this.zzaFh = null;
            }
            zzf.this.mHandler.sendMessage(zzf.this.mHandler.obtainMessage(4, this.zzaFx, 1));
        }
    }

    protected class zzi implements C0768zzf {
        public zzi() {
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zzf.this.zza((zzr) null, zzf.this.zzxF());
            } else if (zzf.this.zzaFo != null) {
                zzf.this.zzaFo.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {
        public final IBinder zzaFy;

        @BinderThread
        public zzj(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzaFy = iBinder;
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            if (zzf.this.zzaFo != null) {
                zzf.this.zzaFo.onConnectionFailed(connectionResult);
            }
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzxG() {
            try {
                String interfaceDescriptor = this.zzaFy.getInterfaceDescriptor();
                if (!zzf.this.zzeA().equals(interfaceDescriptor)) {
                    String valueOf = String.valueOf(zzf.this.zzeA());
                    Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(valueOf).append(" vs. ").append(interfaceDescriptor).toString());
                    return false;
                }
                IInterface zzh = zzf.this.zzh(this.zzaFy);
                if (zzh == null || !zzf.this.zza(2, 3, zzh)) {
                    return false;
                }
                Bundle zzuC = zzf.this.zzuC();
                if (zzf.this.zzaFn != null) {
                    zzf.this.zzaFn.onConnected(zzuC);
                }
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzk extends zza {
        @BinderThread
        public zzk(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            zzf.this.zzaFi.zzg(connectionResult);
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzxG() {
            zzf.this.zzaFi.zzg(ConnectionResult.zzayj);
            return true;
        }
    }

    protected zzf(Context context, Looper looper, int i, zzb zzb2, zzc zzc2, String str) {
        this(context, looper, zzn.zzaU(context), com.google.android.gms.common.zze.zzuY(), i, (zzb) zzac.zzw(zzb2), (zzc) zzac.zzw(zzc2), str);
    }

    protected zzf(Context context, Looper looper, zzn zzn, com.google.android.gms.common.zze zze2, int i, zzb zzb2, zzc zzc2, String str) {
        this.zzrJ = new Object();
        this.zzaFg = new Object();
        this.zzaFk = new ArrayList<>();
        this.zzaFm = 1;
        this.zzaFr = new AtomicInteger(0);
        this.mContext = (Context) zzac.zzb(context, (Object) "Context must not be null");
        this.zzrs = (Looper) zzac.zzb(looper, (Object) "Looper must not be null");
        this.zzaFf = (zzn) zzac.zzb(zzn, (Object) "Supervisor must not be null");
        this.zzaAQ = (com.google.android.gms.common.zze) zzac.zzb(zze2, (Object) "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.zzaFp = i;
        this.zzaFn = zzb2;
        this.zzaFo = zzc2;
        this.zzaFq = str;
    }

    /* access modifiers changed from: private */
    public void zza(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzac.zzax(z);
        synchronized (this.zzrJ) {
            this.zzaFm = i;
            this.zzaFj = t;
            switch (i) {
                case 1:
                    zzxy();
                    break;
                case 2:
                    zzxx();
                    break;
                case 3:
                    zza(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzrJ) {
            if (this.zzaFm != i) {
                z = false;
            } else {
                zza(i2, t);
                z = true;
            }
        }
        return z;
    }

    private void zzxx() {
        if (this.zzaFl != null) {
            String valueOf = String.valueOf(zzez());
            String valueOf2 = String.valueOf(zzxv());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 70 + String.valueOf(valueOf2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(valueOf).append(" on ").append(valueOf2).toString());
            this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
            this.zzaFr.incrementAndGet();
        }
        this.zzaFl = new zzh(this.zzaFr.get());
        if (!this.zzaFf.zza(zzez(), zzxv(), this.zzaFl, zzxw())) {
            String valueOf3 = String.valueOf(zzez());
            String valueOf4 = String.valueOf(zzxv());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf3).length() + 34 + String.valueOf(valueOf4).length()).append("unable to connect to service: ").append(valueOf3).append(" on ").append(valueOf4).toString());
            zza(16, (Bundle) null, this.zzaFr.get());
        }
    }

    private void zzxy() {
        if (this.zzaFl != null) {
            this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
            this.zzaFl = null;
        }
    }

    public void disconnect() {
        this.zzaFr.incrementAndGet();
        synchronized (this.zzaFk) {
            int size = this.zzaFk.size();
            for (int i = 0; i < size; i++) {
                this.zzaFk.get(i).zzxI();
            }
            this.zzaFk.clear();
        }
        synchronized (this.zzaFg) {
            this.zzaFh = null;
        }
        zza(1, (IInterface) null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        zzv zzv;
        synchronized (this.zzrJ) {
            i = this.zzaFm;
            t = this.zzaFj;
        }
        synchronized (this.zzaFg) {
            zzv = this.zzaFh;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("CONNECTING");
                break;
            case 3:
                printWriter.print("CONNECTED");
                break;
            case 4:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(zzeA()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (zzv == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(zzv.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzaFc > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzaFc;
            String valueOf = String.valueOf(simpleDateFormat.format(new Date(this.zzaFc)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.zzaFb > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzaFa) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzaFa));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzaFb;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.zzaFb)));
            append2.println(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(j2).append(" ").append(valueOf2).toString());
        }
        if (this.zzaFe > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaFd));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzaFe;
            String valueOf3 = String.valueOf(simpleDateFormat.format(new Date(this.zzaFe)));
            append3.println(new StringBuilder(String.valueOf(valueOf3).length() + 21).append(j3).append(" ").append(valueOf3).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzrs;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaFm == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaFm == 2;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzaFd = connectionResult.getErrorCode();
        this.zzaFe = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzaFa = i;
        this.zzaFb = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(i, bundle)));
    }

    /* access modifiers changed from: protected */
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.zzaFc = System.currentTimeMillis();
    }

    public void zza(@NonNull C0768zzf zzf) {
        this.zzaFi = (C0768zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    public void zza(@NonNull C0768zzf zzf, int i, @Nullable PendingIntent pendingIntent) {
        this.zzaFi = (C0768zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaFr.get(), i, pendingIntent));
    }

    @WorkerThread
    public void zza(zzr zzr, Set<Scope> set) {
        zzj zzp = new zzj(this.zzaFp).zzdm(this.mContext.getPackageName()).zzp(zzqL());
        if (set != null) {
            zzp.zzf((Collection<Scope>) set);
        }
        if (zzrd()) {
            zzp.zzf(zzxB()).zzb(zzr);
        } else if (zzxE()) {
            zzp.zzf(getAccount());
        }
        zzp.zza(zzxA());
        try {
            synchronized (this.zzaFg) {
                if (this.zzaFh != null) {
                    this.zzaFh.zza(new zzg(this, this.zzaFr.get()), zzp);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zzcS(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            zza(8, (IBinder) null, (Bundle) null, this.zzaFr.get());
        }
    }

    public void zzcS(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaFr.get(), i));
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzeA();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzez();

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zzh(IBinder iBinder);

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        return new Bundle();
    }

    public boolean zzrd() {
        return false;
    }

    public boolean zzrr() {
        return false;
    }

    public Intent zzrs() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzuC() {
        return null;
    }

    public boolean zzvh() {
        return true;
    }

    @Nullable
    public IBinder zzvi() {
        IBinder asBinder;
        synchronized (this.zzaFg) {
            asBinder = this.zzaFh == null ? null : this.zzaFh.asBinder();
        }
        return asBinder;
    }

    public com.google.android.gms.common.zzc[] zzxA() {
        return new com.google.android.gms.common.zzc[0];
    }

    public final Account zzxB() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    /* access modifiers changed from: protected */
    public final void zzxC() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzxD() throws DeadObjectException {
        T t;
        synchronized (this.zzrJ) {
            if (this.zzaFm == 4) {
                throw new DeadObjectException();
            }
            zzxC();
            zzac.zza(this.zzaFj != null, (Object) "Client is connected but service is null");
            t = this.zzaFj;
        }
        return t;
    }

    public boolean zzxE() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzxF() {
        return Collections.EMPTY_SET;
    }

    /* access modifiers changed from: protected */
    public String zzxv() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzxw() {
        return this.zzaFq == null ? this.mContext.getClass().getName() : this.zzaFq;
    }

    public void zzxz() {
        int isGooglePlayServicesAvailable = this.zzaAQ.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            zza((C0768zzf) new zzi(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        zza((C0768zzf) new zzi());
    }
}

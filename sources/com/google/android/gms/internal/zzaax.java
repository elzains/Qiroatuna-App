package com.google.android.gms.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.view.MotionEventCompat;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzaac;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabr;
import com.google.android.gms.internal.zzzx;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zzaax implements Handler.Callback {
    public static final Status zzaCn = new Status(4, "Sign-out occurred while this API call was in progress.");
    /* access modifiers changed from: private */
    public static final Status zzaCo = new Status(4, "The user must be signed in to make this API call.");
    private static zzaax zzaCq;
    /* access modifiers changed from: private */
    public static final Object zztX = new Object();
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final Map<zzzz<?>, zza<?>> zzaAM = new ConcurrentHashMap(5, 0.75f, 1);
    /* access modifiers changed from: private */
    public long zzaBM = 120000;
    /* access modifiers changed from: private */
    public long zzaBN = 5000;
    /* access modifiers changed from: private */
    public long zzaCp = 10000;
    /* access modifiers changed from: private */
    public int zzaCr = -1;
    private final AtomicInteger zzaCs = new AtomicInteger(1);
    private final AtomicInteger zzaCt = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public zzaam zzaCu = null;
    /* access modifiers changed from: private */
    public final Set<zzzz<?>> zzaCv = new com.google.android.gms.common.util.zza();
    private final Set<zzzz<?>> zzaCw = new com.google.android.gms.common.util.zza();
    /* access modifiers changed from: private */
    public final GoogleApiAvailability zzazn;

    public class zza<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzaah {
        private final Api.zze zzaAJ;
        private boolean zzaBL;
        private final zzaal zzaCA;
        private final Set<zzaab> zzaCB = new HashSet();
        private final Map<zzabh.zzb<?>, zzabn> zzaCC = new HashMap();
        private final int zzaCD;
        private final zzabr zzaCE;
        private ConnectionResult zzaCF = null;
        private final Queue<zzzx> zzaCy = new LinkedList();
        private final Api.zzb zzaCz;
        private final zzzz<O> zzayU;

        @WorkerThread
        public zza(zzc<O> zzc) {
            this.zzaAJ = zzc.buildApiClient(zzaax.this.mHandler.getLooper(), this);
            if (this.zzaAJ instanceof zzal) {
                this.zzaCz = ((zzal) this.zzaAJ).zzyn();
            } else {
                this.zzaCz = this.zzaAJ;
            }
            this.zzayU = zzc.getApiKey();
            this.zzaCA = new zzaal();
            this.zzaCD = zzc.getInstanceId();
            if (this.zzaAJ.zzrd()) {
                this.zzaCE = zzc.createSignInCoordinator(zzaax.this.mContext, zzaax.this.mHandler);
            } else {
                this.zzaCE = null;
            }
        }

        @WorkerThread
        private void zzb(zzzx zzzx) {
            zzzx.zza(this.zzaCA, zzrd());
            try {
                zzzx.zza((zza<?>) this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzaAJ.disconnect();
            }
        }

        @WorkerThread
        private void zzj(ConnectionResult connectionResult) {
            for (zzaab zza : this.zzaCB) {
                zza.zza(this.zzayU, connectionResult);
            }
            this.zzaCB.clear();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzwF() {
            zzwJ();
            zzj(ConnectionResult.zzayj);
            zzwL();
            Iterator<zzabn> it = this.zzaCC.values().iterator();
            while (it.hasNext()) {
                it.next();
                try {
                    new TaskCompletionSource();
                } catch (DeadObjectException e) {
                    onConnectionSuspended(1);
                    this.zzaAJ.disconnect();
                } catch (RemoteException e2) {
                }
            }
            zzwH();
            zzwM();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzwG() {
            zzwJ();
            this.zzaBL = true;
            this.zzaCA.zzwa();
            zzaax.this.mHandler.sendMessageDelayed(Message.obtain(zzaax.this.mHandler, 9, this.zzayU), zzaax.this.zzaBN);
            zzaax.this.mHandler.sendMessageDelayed(Message.obtain(zzaax.this.mHandler, 11, this.zzayU), zzaax.this.zzaBM);
            int unused = zzaax.this.zzaCr = -1;
        }

        @WorkerThread
        private void zzwH() {
            while (this.zzaAJ.isConnected() && !this.zzaCy.isEmpty()) {
                zzb(this.zzaCy.remove());
            }
        }

        @WorkerThread
        private void zzwL() {
            if (this.zzaBL) {
                zzaax.this.mHandler.removeMessages(11, this.zzayU);
                zzaax.this.mHandler.removeMessages(9, this.zzayU);
                this.zzaBL = false;
            }
        }

        private void zzwM() {
            zzaax.this.mHandler.removeMessages(12, this.zzayU);
            zzaax.this.mHandler.sendMessageDelayed(zzaax.this.mHandler.obtainMessage(12, this.zzayU), zzaax.this.zzaCp);
        }

        @WorkerThread
        public void connect() {
            zzac.zza(zzaax.this.mHandler);
            if (!this.zzaAJ.isConnected() && !this.zzaAJ.isConnecting()) {
                if (this.zzaAJ.zzvh() && zzaax.this.zzaCr != 0) {
                    int unused = zzaax.this.zzaCr = zzaax.this.zzazn.isGooglePlayServicesAvailable(zzaax.this.mContext);
                    if (zzaax.this.zzaCr != 0) {
                        onConnectionFailed(new ConnectionResult(zzaax.this.zzaCr, (PendingIntent) null));
                        return;
                    }
                }
                zzb zzb = new zzb(this.zzaAJ, this.zzayU);
                if (this.zzaAJ.zzrd()) {
                    this.zzaCE.zza(zzb);
                }
                this.zzaAJ.zza(zzb);
            }
        }

        public int getInstanceId() {
            return this.zzaCD;
        }

        /* access modifiers changed from: package-private */
        public boolean isConnected() {
            return this.zzaAJ.isConnected();
        }

        public void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == zzaax.this.mHandler.getLooper()) {
                zzwF();
            } else {
                zzaax.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.zzwF();
                    }
                });
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
            if (r5.zzaCx.zzc(r6, r5.zzaCD) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0074, code lost:
            if (r6.getErrorCode() != 18) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
            r5.zzaBL = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x007b, code lost:
            if (r5.zzaBL == false) goto L_0x009b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x007d, code lost:
            com.google.android.gms.internal.zzaax.zza(r5.zzaCx).sendMessageDelayed(android.os.Message.obtain(com.google.android.gms.internal.zzaax.zza(r5.zzaCx), 9, r5.zzayU), com.google.android.gms.internal.zzaax.zzc(r5.zzaCx));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x009b, code lost:
            r2 = java.lang.String.valueOf(r5.zzayU.zzvw());
            zzD(new com.google.android.gms.common.api.Status(17, new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 38).append("API: ").append(r2).append(" is not available on this device.").toString()));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            return;
         */
        @android.support.annotation.WorkerThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
            /*
                r5 = this;
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this
                android.os.Handler r0 = r0.mHandler
                com.google.android.gms.common.internal.zzac.zza(r0)
                com.google.android.gms.internal.zzabr r0 = r5.zzaCE
                if (r0 == 0) goto L_0x0012
                com.google.android.gms.internal.zzabr r0 = r5.zzaCE
                r0.zzwY()
            L_0x0012:
                r5.zzwJ()
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this
                r1 = -1
                int unused = r0.zzaCr = r1
                r5.zzj(r6)
                int r0 = r6.getErrorCode()
                r1 = 4
                if (r0 != r1) goto L_0x002d
                com.google.android.gms.common.api.Status r0 = com.google.android.gms.internal.zzaax.zzaCo
                r5.zzD(r0)
            L_0x002c:
                return
            L_0x002d:
                java.util.Queue<com.google.android.gms.internal.zzzx> r0 = r5.zzaCy
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0038
                r5.zzaCF = r6
                goto L_0x002c
            L_0x0038:
                java.lang.Object r1 = com.google.android.gms.internal.zzaax.zztX
                monitor-enter(r1)
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzaam r0 = r0.zzaCu     // Catch:{ all -> 0x0060 }
                if (r0 == 0) goto L_0x0063
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this     // Catch:{ all -> 0x0060 }
                java.util.Set r0 = r0.zzaCv     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzzz<O> r2 = r5.zzayU     // Catch:{ all -> 0x0060 }
                boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x0060 }
                if (r0 == 0) goto L_0x0063
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzaam r0 = r0.zzaCu     // Catch:{ all -> 0x0060 }
                int r2 = r5.zzaCD     // Catch:{ all -> 0x0060 }
                r0.zzb(r6, r2)     // Catch:{ all -> 0x0060 }
                monitor-exit(r1)     // Catch:{ all -> 0x0060 }
                goto L_0x002c
            L_0x0060:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0060 }
                throw r0
            L_0x0063:
                monitor-exit(r1)     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this
                int r1 = r5.zzaCD
                boolean r0 = r0.zzc(r6, r1)
                if (r0 != 0) goto L_0x002c
                int r0 = r6.getErrorCode()
                r1 = 18
                if (r0 != r1) goto L_0x0079
                r0 = 1
                r5.zzaBL = r0
            L_0x0079:
                boolean r0 = r5.zzaBL
                if (r0 == 0) goto L_0x009b
                com.google.android.gms.internal.zzaax r0 = com.google.android.gms.internal.zzaax.this
                android.os.Handler r0 = r0.mHandler
                com.google.android.gms.internal.zzaax r1 = com.google.android.gms.internal.zzaax.this
                android.os.Handler r1 = r1.mHandler
                r2 = 9
                com.google.android.gms.internal.zzzz<O> r3 = r5.zzayU
                android.os.Message r1 = android.os.Message.obtain(r1, r2, r3)
                com.google.android.gms.internal.zzaax r2 = com.google.android.gms.internal.zzaax.this
                long r2 = r2.zzaBN
                r0.sendMessageDelayed(r1, r2)
                goto L_0x002c
            L_0x009b:
                com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
                r1 = 17
                com.google.android.gms.internal.zzzz<O> r2 = r5.zzayU
                java.lang.String r2 = r2.zzvw()
                java.lang.String r2 = java.lang.String.valueOf(r2)
                java.lang.String r3 = java.lang.String.valueOf(r2)
                int r3 = r3.length()
                int r3 = r3 + 38
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>(r3)
                java.lang.String r3 = "API: "
                java.lang.StringBuilder r3 = r4.append(r3)
                java.lang.StringBuilder r2 = r3.append(r2)
                java.lang.String r3 = " is not available on this device."
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r0.<init>(r1, r2)
                r5.zzD(r0)
                goto L_0x002c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaax.zza.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
        }

        public void onConnectionSuspended(int i) {
            if (Looper.myLooper() == zzaax.this.mHandler.getLooper()) {
                zzwG();
            } else {
                zzaax.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.zzwG();
                    }
                });
            }
        }

        @WorkerThread
        public void resume() {
            zzac.zza(zzaax.this.mHandler);
            if (this.zzaBL) {
                connect();
            }
        }

        @WorkerThread
        public void signOut() {
            zzac.zza(zzaax.this.mHandler);
            zzD(zzaax.zzaCn);
            this.zzaCA.zzvZ();
            for (zzabh.zzb<?> zze : this.zzaCC.keySet()) {
                zza(new zzzx.zze(zze, new TaskCompletionSource()));
            }
            zzj(new ConnectionResult(4));
            this.zzaAJ.disconnect();
        }

        @WorkerThread
        public void zzD(Status status) {
            zzac.zza(zzaax.this.mHandler);
            for (zzzx zzz : this.zzaCy) {
                zzz.zzz(status);
            }
            this.zzaCy.clear();
        }

        public void zza(final ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == zzaax.this.mHandler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                zzaax.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.onConnectionFailed(connectionResult);
                    }
                });
            }
        }

        @WorkerThread
        public void zza(zzzx zzzx) {
            zzac.zza(zzaax.this.mHandler);
            if (this.zzaAJ.isConnected()) {
                zzb(zzzx);
                zzwM();
                return;
            }
            this.zzaCy.add(zzzx);
            if (this.zzaCF == null || !this.zzaCF.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.zzaCF);
            }
        }

        @WorkerThread
        public void zzb(zzaab zzaab) {
            zzac.zza(zzaax.this.mHandler);
            this.zzaCB.add(zzaab);
        }

        @WorkerThread
        public void zzi(@NonNull ConnectionResult connectionResult) {
            zzac.zza(zzaax.this.mHandler);
            this.zzaAJ.disconnect();
            onConnectionFailed(connectionResult);
        }

        public boolean zzrd() {
            return this.zzaAJ.zzrd();
        }

        public Api.zze zzvU() {
            return this.zzaAJ;
        }

        public Map<zzabh.zzb<?>, zzabn> zzwI() {
            return this.zzaCC;
        }

        @WorkerThread
        public void zzwJ() {
            zzac.zza(zzaax.this.mHandler);
            this.zzaCF = null;
        }

        @WorkerThread
        public ConnectionResult zzwK() {
            zzac.zza(zzaax.this.mHandler);
            return this.zzaCF;
        }

        @WorkerThread
        public void zzwN() {
            zzac.zza(zzaax.this.mHandler);
            if (this.zzaAJ.isConnected() && this.zzaCC.size() == 0) {
                if (this.zzaCA.zzvY()) {
                    zzwM();
                } else {
                    this.zzaAJ.disconnect();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public zzbai zzwO() {
            if (this.zzaCE == null) {
                return null;
            }
            return this.zzaCE.zzwO();
        }

        @WorkerThread
        public void zzwn() {
            zzac.zza(zzaax.this.mHandler);
            if (this.zzaBL) {
                zzwL();
                zzD(zzaax.this.zzazn.isGooglePlayServicesAvailable(zzaax.this.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zzaAJ.disconnect();
            }
        }
    }

    private class zzb implements zzf.C0768zzf, zzabr.zza {
        /* access modifiers changed from: private */
        public final Api.zze zzaAJ;
        private zzr zzaBw = null;
        /* access modifiers changed from: private */
        public boolean zzaCI = false;
        private Set<Scope> zzakq = null;
        /* access modifiers changed from: private */
        public final zzzz<?> zzayU;

        public zzb(Api.zze zze, zzzz<?> zzzz) {
            this.zzaAJ = zze;
            this.zzayU = zzzz;
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzwP() {
            if (this.zzaCI && this.zzaBw != null) {
                this.zzaAJ.zza(this.zzaBw, this.zzakq);
            }
        }

        @WorkerThread
        public void zzb(zzr zzr, Set<Scope> set) {
            if (zzr == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zzi(new ConnectionResult(4));
                return;
            }
            this.zzaBw = zzr;
            this.zzakq = set;
            zzwP();
        }

        public void zzg(@NonNull final ConnectionResult connectionResult) {
            zzaax.this.mHandler.post(new Runnable() {
                public void run() {
                    if (connectionResult.isSuccess()) {
                        boolean unused = zzb.this.zzaCI = true;
                        if (zzb.this.zzaAJ.zzrd()) {
                            zzb.this.zzwP();
                        } else {
                            zzb.this.zzaAJ.zza((zzr) null, Collections.emptySet());
                        }
                    } else {
                        ((zza) zzaax.this.zzaAM.get(zzb.this.zzayU)).onConnectionFailed(connectionResult);
                    }
                }
            });
        }

        @WorkerThread
        public void zzi(ConnectionResult connectionResult) {
            ((zza) zzaax.this.zzaAM.get(this.zzayU)).zzi(connectionResult);
        }
    }

    private zzaax(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        this.mHandler = new Handler(looper, this);
        this.zzazn = googleApiAvailability;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
    }

    @WorkerThread
    private void zza(int i, ConnectionResult connectionResult) {
        zza zza2;
        Iterator<zza<?>> it = this.zzaAM.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                zza2 = null;
                break;
            }
            zza2 = it.next();
            if (zza2.getInstanceId() == i) {
                break;
            }
        }
        if (zza2 != null) {
            String valueOf = String.valueOf(this.zzazn.getErrorString(connectionResult.getErrorCode()));
            String valueOf2 = String.valueOf(connectionResult.getErrorMessage());
            zza2.zzD(new Status(17, new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(valueOf2).length()).append("Error resolution was canceled by the user, original error message: ").append(valueOf).append(": ").append(valueOf2).toString()));
            return;
        }
        Log.wtf("GoogleApiManager", new StringBuilder(76).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.").toString(), new Exception());
    }

    @WorkerThread
    private void zza(zzaab zzaab) {
        for (zzzz next : zzaab.zzvz()) {
            zza zza2 = this.zzaAM.get(next);
            if (zza2 == null) {
                zzaab.zza(next, new ConnectionResult(13));
                return;
            } else if (zza2.isConnected()) {
                zzaab.zza(next, ConnectionResult.zzayj);
            } else if (zza2.zzwK() != null) {
                zzaab.zza(next, zza2.zzwK());
            } else {
                zza2.zzb(zzaab);
            }
        }
    }

    @WorkerThread
    private void zza(zzabl zzabl) {
        zza zza2 = this.zzaAM.get(zzabl.zzaDe.getApiKey());
        if (zza2 == null) {
            zzc(zzabl.zzaDe);
            zza2 = this.zzaAM.get(zzabl.zzaDe.getApiKey());
        }
        if (!zza2.zzrd() || this.zzaCt.get() == zzabl.zzaDd) {
            zza2.zza(zzabl.zzaDc);
            return;
        }
        zzabl.zzaDc.zzz(zzaCn);
        zza2.signOut();
    }

    public static zzaax zzaP(Context context) {
        zzaax zzaax;
        synchronized (zztX) {
            if (zzaCq == null) {
                zzaCq = new zzaax(context.getApplicationContext(), zzwy(), GoogleApiAvailability.getInstance());
            }
            zzaax = zzaCq;
        }
        return zzaax;
    }

    @WorkerThread
    private void zzav(boolean z) {
        this.zzaCp = z ? 10000 : 300000;
        this.mHandler.removeMessages(12);
        for (zzzz<?> obtainMessage : this.zzaAM.keySet()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(12, obtainMessage), this.zzaCp);
        }
    }

    @WorkerThread
    private void zzc(zzc<?> zzc) {
        zzzz<?> apiKey = zzc.getApiKey();
        zza zza2 = this.zzaAM.get(apiKey);
        if (zza2 == null) {
            zza2 = new zza(zzc);
            this.zzaAM.put(apiKey, zza2);
        }
        if (zza2.zzrd()) {
            this.zzaCw.add(apiKey);
        }
        zza2.connect();
    }

    @WorkerThread
    private void zzwA() {
        zzt.zzzg();
        if (this.mContext.getApplicationContext() instanceof Application) {
            zzaac.zza((Application) this.mContext.getApplicationContext());
            zzaac.zzvB().zza((zzaac.zza) new zzaac.zza() {
                public void zzat(boolean z) {
                    zzaax.this.mHandler.sendMessage(zzaax.this.mHandler.obtainMessage(1, Boolean.valueOf(z)));
                }
            });
            if (!zzaac.zzvB().zzas(true)) {
                this.zzaCp = 300000;
            }
        }
    }

    @WorkerThread
    private void zzwB() {
        for (zza next : this.zzaAM.values()) {
            next.zzwJ();
            next.connect();
        }
    }

    @WorkerThread
    private void zzwC() {
        for (zzzz<?> remove : this.zzaCw) {
            this.zzaAM.remove(remove).signOut();
        }
        this.zzaCw.clear();
    }

    public static zzaax zzww() {
        zzaax zzaax;
        synchronized (zztX) {
            zzac.zzb(zzaCq, (Object) "Must guarantee manager is non-null before using getInstance");
            zzaax = zzaCq;
        }
        return zzaax;
    }

    public static void zzwx() {
        synchronized (zztX) {
            if (zzaCq != null) {
                zzaCq.signOut();
            }
        }
    }

    private static Looper zzwy() {
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                zzav(((Boolean) message.obj).booleanValue());
                break;
            case 2:
                zza((zzaab) message.obj);
                break;
            case 3:
                zzwB();
                break;
            case 4:
            case 8:
            case 13:
                zza((zzabl) message.obj);
                break;
            case 5:
                zza(message.arg1, (ConnectionResult) message.obj);
                break;
            case 6:
                zzwA();
                break;
            case 7:
                zzc((zzc<?>) (zzc) message.obj);
                break;
            case 9:
                if (this.zzaAM.containsKey(message.obj)) {
                    this.zzaAM.get(message.obj).resume();
                    break;
                }
                break;
            case 10:
                zzwC();
                break;
            case 11:
                if (this.zzaAM.containsKey(message.obj)) {
                    this.zzaAM.get(message.obj).zzwn();
                    break;
                }
                break;
            case MotionEventCompat.AXIS_RX:
                if (this.zzaAM.containsKey(message.obj)) {
                    this.zzaAM.get(message.obj).zzwN();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                return false;
        }
        return true;
    }

    public void signOut() {
        this.zzaCt.incrementAndGet();
        this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(10));
    }

    /* access modifiers changed from: package-private */
    public PendingIntent zza(zzzz<?> zzzz, int i) {
        if (this.zzaAM.get(zzzz) == null) {
            return null;
        }
        zzbai zzwO = this.zzaAM.get(zzzz).zzwO();
        if (zzwO == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, i, zzwO.zzrs(), 134217728);
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull zzabh.zzb<?> zzb2) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(13, new zzabl(new zzzx.zze(zzb2, taskCompletionSource), this.zzaCt.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull zzabm<Api.zzb, ?> zzabm, @NonNull zzabz<Api.zzb, ?> zzabz) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(8, new zzabl(new zzzx.zzc(new zzabn(zzabm, zzabz), taskCompletionSource), this.zzaCt.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.tasks.Task<java.lang.Void> zza(java.lang.Iterable<? extends com.google.android.gms.common.api.zzc<?>> r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.zzaab r1 = new com.google.android.gms.internal.zzaab
            r1.<init>(r5)
            java.util.Iterator r2 = r5.iterator()
        L_0x0009:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r2.next()
            com.google.android.gms.common.api.zzc r0 = (com.google.android.gms.common.api.zzc) r0
            java.util.Map<com.google.android.gms.internal.zzzz<?>, com.google.android.gms.internal.zzaax$zza<?>> r3 = r4.zzaAM
            com.google.android.gms.internal.zzzz r0 = r0.getApiKey()
            java.lang.Object r0 = r3.get(r0)
            com.google.android.gms.internal.zzaax$zza r0 = (com.google.android.gms.internal.zzaax.zza) r0
            if (r0 == 0) goto L_0x0029
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0009
        L_0x0029:
            android.os.Handler r0 = r4.mHandler
            android.os.Handler r2 = r4.mHandler
            r3 = 2
            android.os.Message r2 = r2.obtainMessage(r3, r1)
            r0.sendMessage(r2)
            com.google.android.gms.tasks.Task r0 = r1.getTask()
        L_0x0039:
            return r0
        L_0x003a:
            r1.zzvA()
            com.google.android.gms.tasks.Task r0 = r1.getTask()
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaax.zza(java.lang.Iterable):com.google.android.gms.tasks.Task");
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public <O extends Api.ApiOptions> void zza(zzc<O> zzc, int i, zzaad.zza<? extends Result, Api.zzb> zza2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzabl(new zzzx.zzb(i, zza2), this.zzaCt.get(), zzc)));
    }

    public <O extends Api.ApiOptions, TResult> void zza(zzc<O> zzc, int i, zzabv<Api.zzb, TResult> zzabv, TaskCompletionSource<TResult> taskCompletionSource, zzabs zzabs) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzabl(new zzzx.zzd(i, zzabv, taskCompletionSource, zzabs), this.zzaCt.get(), zzc)));
    }

    public void zza(@NonNull zzaam zzaam) {
        synchronized (zztX) {
            if (this.zzaCu != zzaam) {
                this.zzaCu = zzaam;
                this.zzaCv.clear();
                this.zzaCv.addAll(zzaam.zzwb());
            }
        }
    }

    public void zzb(zzc<?> zzc) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, zzc));
    }

    /* access modifiers changed from: package-private */
    public void zzb(@NonNull zzaam zzaam) {
        synchronized (zztX) {
            if (this.zzaCu == zzaam) {
                this.zzaCu = null;
                this.zzaCv.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzazn.zza(this.mContext, connectionResult, i);
    }

    /* access modifiers changed from: package-private */
    public void zzvn() {
        this.zzaCt.incrementAndGet();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(10));
    }

    public void zzvx() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    public int zzwz() {
        return this.zzaCs.getAndIncrement();
    }
}

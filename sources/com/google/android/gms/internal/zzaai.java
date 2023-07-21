package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzabc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzaai implements zzabc {
    private final Context mContext;
    private final Set<zzabq> zzaAA = Collections.newSetFromMap(new WeakHashMap());
    private final Api.zze zzaAB;
    private Bundle zzaAC;
    /* access modifiers changed from: private */
    public ConnectionResult zzaAD = null;
    /* access modifiers changed from: private */
    public ConnectionResult zzaAE = null;
    /* access modifiers changed from: private */
    public boolean zzaAF = false;
    /* access modifiers changed from: private */
    public final Lock zzaAG;
    private int zzaAH = 0;
    private final zzaat zzaAw;
    /* access modifiers changed from: private */
    public final zzaav zzaAx;
    /* access modifiers changed from: private */
    public final zzaav zzaAy;
    private final Map<Api.zzc<?>, zzaav> zzaAz;
    private final Looper zzrs;

    private class zza implements zzabc.zza {
        private zza() {
        }

        public void zzc(int i, boolean z) {
            zzaai.this.zzaAG.lock();
            try {
                if (zzaai.this.zzaAF || zzaai.this.zzaAE == null || !zzaai.this.zzaAE.isSuccess()) {
                    boolean unused = zzaai.this.zzaAF = false;
                    zzaai.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzaai.this.zzaAF = true;
                zzaai.this.zzaAy.onConnectionSuspended(i);
                zzaai.this.zzaAG.unlock();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzaai.this.zzaAG.lock();
            try {
                ConnectionResult unused = zzaai.this.zzaAD = connectionResult;
                zzaai.this.zzvP();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }

        public void zzo(@Nullable Bundle bundle) {
            zzaai.this.zzaAG.lock();
            try {
                zzaai.this.zzn(bundle);
                ConnectionResult unused = zzaai.this.zzaAD = ConnectionResult.zzayj;
                zzaai.this.zzvP();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }
    }

    private class zzb implements zzabc.zza {
        private zzb() {
        }

        public void zzc(int i, boolean z) {
            zzaai.this.zzaAG.lock();
            try {
                if (zzaai.this.zzaAF) {
                    boolean unused = zzaai.this.zzaAF = false;
                    zzaai.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzaai.this.zzaAF = true;
                zzaai.this.zzaAx.onConnectionSuspended(i);
                zzaai.this.zzaAG.unlock();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzaai.this.zzaAG.lock();
            try {
                ConnectionResult unused = zzaai.this.zzaAE = connectionResult;
                zzaai.this.zzvP();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }

        public void zzo(@Nullable Bundle bundle) {
            zzaai.this.zzaAG.lock();
            try {
                ConnectionResult unused = zzaai.this.zzaAE = ConnectionResult.zzayj;
                zzaai.this.zzvP();
            } finally {
                zzaai.this.zzaAG.unlock();
            }
        }
    }

    private zzaai(Context context, zzaat zzaat, Lock lock, Looper looper, zze zze, Map<Api.zzc<?>, Api.zze> map, Map<Api.zzc<?>, Api.zze> map2, zzg zzg, Api.zza<? extends zzbai, zzbaj> zza2, Api.zze zze2, ArrayList<zzaag> arrayList, ArrayList<zzaag> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zzaAw = zzaat;
        this.zzaAG = lock;
        this.zzrs = looper;
        this.zzaAB = zze2;
        this.zzaAx = new zzaav(context, this.zzaAw, lock, looper, zze, map2, (zzg) null, map4, (Api.zza<? extends zzbai, zzbaj>) null, arrayList2, new zza());
        this.zzaAy = new zzaav(context, this.zzaAw, lock, looper, zze, map, zzg, map3, zza2, arrayList, new zzb());
        ArrayMap arrayMap = new ArrayMap();
        for (Api.zzc<?> put : map2.keySet()) {
            arrayMap.put(put, this.zzaAx);
        }
        for (Api.zzc<?> put2 : map.keySet()) {
            arrayMap.put(put2, this.zzaAy);
        }
        this.zzaAz = Collections.unmodifiableMap(arrayMap);
    }

    public static zzaai zza(Context context, zzaat zzaat, Lock lock, Looper looper, zze zze, Map<Api.zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Boolean> map2, Api.zza<? extends zzbai, zzbaj> zza2, ArrayList<zzaag> arrayList) {
        Api.zze zze2 = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Map.Entry next : map.entrySet()) {
            Api.zze zze3 = (Api.zze) next.getValue();
            if (zze3.zzrr()) {
                zze2 = zze3;
            }
            if (zze3.zzrd()) {
                arrayMap.put((Api.zzc) next.getKey(), zze3);
            } else {
                arrayMap2.put((Api.zzc) next.getKey(), zze3);
            }
        }
        zzac.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api next2 : map2.keySet()) {
            Api.zzc<?> zzvg = next2.zzvg();
            if (arrayMap.containsKey(zzvg)) {
                arrayMap3.put(next2, map2.get(next2));
            } else if (arrayMap2.containsKey(zzvg)) {
                arrayMap4.put(next2, map2.get(next2));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<zzaag> it = arrayList.iterator();
        while (it.hasNext()) {
            zzaag next3 = it.next();
            if (arrayMap3.containsKey(next3.zzaxf)) {
                arrayList2.add(next3);
            } else if (arrayMap4.containsKey(next3.zzaxf)) {
                arrayList3.add(next3);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zzaai(context, zzaat, lock, looper, zze, arrayMap, arrayMap2, zzg, zza2, zze2, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private void zza(ConnectionResult connectionResult) {
        switch (this.zzaAH) {
            case 1:
                break;
            case 2:
                this.zzaAw.zzc(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzvR();
        this.zzaAH = 0;
    }

    /* access modifiers changed from: private */
    public void zzb(int i, boolean z) {
        this.zzaAw.zzc(i, z);
        this.zzaAE = null;
        this.zzaAD = null;
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private boolean zzc(zzaad.zza<? extends Result, ? extends Api.zzb> zza2) {
        Api.zzc<? extends Api.zzb> zzvg = zza2.zzvg();
        zzac.zzb(this.zzaAz.containsKey(zzvg), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return this.zzaAz.get(zzvg).equals(this.zzaAy);
    }

    /* access modifiers changed from: private */
    public void zzn(Bundle bundle) {
        if (this.zzaAC == null) {
            this.zzaAC = bundle;
        } else if (bundle != null) {
            this.zzaAC.putAll(bundle);
        }
    }

    private void zzvO() {
        this.zzaAE = null;
        this.zzaAD = null;
        this.zzaAx.connect();
        this.zzaAy.connect();
    }

    /* access modifiers changed from: private */
    public void zzvP() {
        if (zzb(this.zzaAD)) {
            if (zzb(this.zzaAE) || zzvS()) {
                zzvQ();
            } else if (this.zzaAE == null) {
            } else {
                if (this.zzaAH == 1) {
                    zzvR();
                    return;
                }
                zza(this.zzaAE);
                this.zzaAx.disconnect();
            }
        } else if (this.zzaAD != null && zzb(this.zzaAE)) {
            this.zzaAy.disconnect();
            zza(this.zzaAD);
        } else if (this.zzaAD != null && this.zzaAE != null) {
            ConnectionResult connectionResult = this.zzaAD;
            if (this.zzaAy.zzaCi < this.zzaAx.zzaCi) {
                connectionResult = this.zzaAE;
            }
            zza(connectionResult);
        }
    }

    private void zzvQ() {
        switch (this.zzaAH) {
            case 1:
                break;
            case 2:
                this.zzaAw.zzo(this.zzaAC);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                break;
        }
        zzvR();
        this.zzaAH = 0;
    }

    private void zzvR() {
        for (zzabq zzrq : this.zzaAA) {
            zzrq.zzrq();
        }
        this.zzaAA.clear();
    }

    private boolean zzvS() {
        return this.zzaAE != null && this.zzaAE.getErrorCode() == 4;
    }

    @Nullable
    private PendingIntent zzvT() {
        if (this.zzaAB == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.zzaAw.getSessionId(), this.zzaAB.zzrs(), 134217728);
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void connect() {
        this.zzaAH = 2;
        this.zzaAF = false;
        zzvO();
    }

    public void disconnect() {
        this.zzaAE = null;
        this.zzaAD = null;
        this.zzaAH = 0;
        this.zzaAx.disconnect();
        this.zzaAy.disconnect();
        zzvR();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zzaAy.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzaAx.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return this.zzaAz.get(api.zzvg()).equals(this.zzaAy) ? zzvS() ? new ConnectionResult(4, zzvT()) : this.zzaAy.getConnectionResult(api) : this.zzaAx.getConnectionResult(api);
    }

    public boolean isConnected() {
        boolean z = true;
        this.zzaAG.lock();
        try {
            if (!this.zzaAx.isConnected() || (!zzvN() && !zzvS() && this.zzaAH != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzaAG.lock();
        try {
            return this.zzaAH == 2;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(@NonNull T t) {
        if (!zzc((zzaad.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzaAx.zza(t);
        }
        if (!zzvS()) {
            return this.zzaAy.zza(t);
        }
        t.zzB(new Status(4, (String) null, zzvT()));
        return t;
    }

    public boolean zza(zzabq zzabq) {
        this.zzaAG.lock();
        try {
            if ((isConnecting() || isConnected()) && !zzvN()) {
                this.zzaAA.add(zzabq);
                if (this.zzaAH == 0) {
                    this.zzaAH = 1;
                }
                this.zzaAE = null;
                this.zzaAy.connect();
                return true;
            }
            this.zzaAG.unlock();
            return false;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(@NonNull T t) {
        if (!zzc((zzaad.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzaAx.zzb(t);
        }
        if (!zzvS()) {
            return this.zzaAy.zzb(t);
        }
        t.zzB(new Status(4, (String) null, zzvT()));
        return t;
    }

    public void zzvM() {
        this.zzaAx.zzvM();
        this.zzaAy.zzvM();
    }

    public boolean zzvN() {
        return this.zzaAy.isConnected();
    }

    public void zzvn() {
        this.zzaAG.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zzaAy.disconnect();
            this.zzaAE = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzrs).post(new Runnable() {
                    public void run() {
                        zzaai.this.zzaAG.lock();
                        try {
                            zzaai.this.zzvP();
                        } finally {
                            zzaai.this.zzaAG.unlock();
                        }
                    }
                });
            } else {
                zzvR();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }
}

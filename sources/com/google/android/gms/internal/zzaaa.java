package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzaaa extends zzaae {
    private final SparseArray<zza> zzazN = new SparseArray<>();

    private class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzazO;
        public final GoogleApiClient zzazP;
        public final GoogleApiClient.OnConnectionFailedListener zzazQ;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzazO = i;
            this.zzazP = googleApiClient;
            this.zzazQ = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.zzazO);
            printWriter.println(":");
            this.zzazP.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zzaaa.this.zzb(connectionResult, this.zzazO);
        }

        public void zzvy() {
            this.zzazP.unregisterConnectionFailedListener(this);
            this.zzazP.disconnect();
        }
    }

    private zzaaa(zzabf zzabf) {
        super(zzabf);
        this.zzaCR.zza("AutoManageHelper", (zzabe) this);
    }

    public static zzaaa zza(zzabd zzabd) {
        zzabf zzc = zzc(zzabd);
        zzaaa zzaaa = (zzaaa) zzc.zza("AutoManageHelper", zzaaa.class);
        return zzaaa != null ? zzaaa : new zzaaa(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzazN.size()) {
                this.zzazN.valueAt(i2).dump(str, fileDescriptor, printWriter, strArr);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzazN);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (!this.zzazZ) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.zzazN.size()) {
                    this.zzazN.valueAt(i2).zzazP.connect();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzazN.size()) {
                this.zzazN.valueAt(i2).zzazP.disconnect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzac.zza(this.zzazN.indexOfKey(i) < 0, (Object) new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(i).toString());
        Log.d("AutoManageHelper", new StringBuilder(54).append("starting AutoManage for client ").append(i).append(" ").append(this.mStarted).append(" ").append(this.zzazZ).toString());
        this.zzazN.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzazZ) {
            String valueOf = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 11).append("connecting ").append(valueOf).toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = this.zzazN.get(i);
        if (zza2 != null) {
            zzcA(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza2.zzazQ;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public void zzcA(int i) {
        zza zza2 = this.zzazN.get(i);
        this.zzazN.remove(i);
        if (zza2 != null) {
            zza2.zzvy();
        }
    }

    /* access modifiers changed from: protected */
    public void zzvx() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzazN.size()) {
                this.zzazN.valueAt(i2).zzazP.connect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}

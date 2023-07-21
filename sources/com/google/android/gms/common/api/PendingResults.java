package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzaaf;
import com.google.android.gms.internal.zzabk;
import com.google.android.gms.internal.zzabt;

public final class PendingResults {

    private static final class zza<R extends Result> extends zzaaf<R> {
        private final R zzazs;

        public zza(R r) {
            super(Looper.getMainLooper());
            this.zzazs = r;
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            if (status.getStatusCode() == this.zzazs.getStatus().getStatusCode()) {
                return this.zzazs;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class zzb<R extends Result> extends zzaaf<R> {
        private final R zzazt;

        public zzb(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.zzazt = r;
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            return this.zzazt;
        }
    }

    private static final class zzc<R extends Result> extends zzaaf<R> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        zzabt zzabt = new zzabt(Looper.getMainLooper());
        zzabt.cancel();
        return zzabt;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        zzac.zzb(r, (Object) "Result must not be null");
        zzac.zzb(r.getStatus().getStatusCode() == 16, (Object) "Status code must be CommonStatusCodes.CANCELED");
        zza zza2 = new zza(r);
        zza2.cancel();
        return zza2;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        zzac.zzb(r, (Object) "Result must not be null");
        zzc zzc2 = new zzc((GoogleApiClient) null);
        zzc2.zzb(r);
        return new zzabk(zzc2);
    }

    public static PendingResult<Status> immediatePendingResult(Status status) {
        zzac.zzb(status, (Object) "Result must not be null");
        zzabt zzabt = new zzabt(Looper.getMainLooper());
        zzabt.zzb(status);
        return zzabt;
    }

    public static <R extends Result> PendingResult<R> zza(R r, GoogleApiClient googleApiClient) {
        zzac.zzb(r, (Object) "Result must not be null");
        zzac.zzb(!r.getStatus().isSuccess(), (Object) "Status code must not be SUCCESS");
        zzb zzb2 = new zzb(googleApiClient, r);
        zzb2.zzb(r);
        return zzb2;
    }

    public static PendingResult<Status> zza(Status status, GoogleApiClient googleApiClient) {
        zzac.zzb(status, (Object) "Result must not be null");
        zzabt zzabt = new zzabt(googleApiClient);
        zzabt.zzb(status);
        return zzabt;
    }

    public static <R extends Result> OptionalPendingResult<R> zzb(R r, GoogleApiClient googleApiClient) {
        zzac.zzb(r, (Object) "Result must not be null");
        zzc zzc2 = new zzc(googleApiClient);
        zzc2.zzb(r);
        return new zzabk(zzc2);
    }
}

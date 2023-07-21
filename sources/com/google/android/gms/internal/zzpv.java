package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzn;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;

@zzme
public class zzpv {
    private static zzm zzYm;
    private static final Object zzYn = new Object();
    public static final zza<Void> zzYo = new zza<Void>() {
        public /* bridge */ /* synthetic */ Object zzh(InputStream inputStream) {
            return null;
        }

        public /* bridge */ /* synthetic */ Object zzji() {
            return null;
        }
    };

    public interface zza<T> {
        T zzh(InputStream inputStream);

        T zzji();
    }

    private static class zzb<T> extends zzl<InputStream> {
        /* access modifiers changed from: private */
        public final zza<T> zzYs;
        /* access modifiers changed from: private */
        public final zzn.zzb<T> zzaG;

        public zzb(String str, final zza<T> zza, final zzn.zzb<T> zzb) {
            super(0, str, new zzn.zza() {
                public void zze(zzs zzs) {
                    zzn.zzb.this.zzb(zza.zzji());
                }
            });
            this.zzYs = zza;
            this.zzaG = zzb;
        }

        /* access modifiers changed from: protected */
        public zzn<InputStream> zza(zzj zzj) {
            return zzn.zza(new ByteArrayInputStream(zzj.data), zzy.zzb(zzj));
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzi */
        public void zza(final InputStream inputStream) {
            final zzqm zza = zzpn.zza(new Callable<T>() {
                public T call() {
                    return zzb.this.zzYs.zzh(inputStream);
                }
            });
            zza.zzd(new Runnable() {
                public void run() {
                    try {
                        zzb.this.zzaG.zzb(zza.get());
                    } catch (Exception e) {
                        zzpk.zzb("Error occured while dispatching http response in getter.", e);
                        zzw.zzcQ().zza((Throwable) e, "HttpGetter.deliverResponse.1");
                    }
                }
            });
        }
    }

    private class zzc<T> extends zzqj<T> implements zzn.zzb<T> {
        private zzc(zzpv zzpv) {
        }

        public void zzb(@Nullable T t) {
            super.zzh(t);
        }
    }

    public zzpv(Context context) {
        zzZ(context);
    }

    private static zzm zzZ(Context context) {
        zzm zzm;
        synchronized (zzYn) {
            if (zzYm == null) {
                zzYm = zzad.zza(context.getApplicationContext());
            }
            zzm = zzYm;
        }
        return zzm;
    }

    public zzqm<String> zza(int i, final String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        final zzc zzc2 = new zzc();
        final byte[] bArr2 = bArr;
        final Map<String, String> map2 = map;
        zzYm.zze(new zzac(this, i, str, zzc2, new zzn.zza(this) {
            public void zze(zzs zzs) {
                String str = str;
                String valueOf = String.valueOf(zzs.toString());
                zzpk.zzbh(new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(valueOf).length()).append("Failed to load URL: ").append(str).append("\n").append(valueOf).toString());
                zzc2.zzb(null);
            }
        }) {
            public Map<String, String> getHeaders() throws zza {
                return map2 == null ? super.getHeaders() : map2;
            }

            public byte[] zzm() throws zza {
                return bArr2 == null ? super.zzm() : bArr2;
            }
        });
        return zzc2;
    }

    public <T> zzqm<T> zza(String str, zza<T> zza2) {
        zzc zzc2 = new zzc();
        zzYm.zze(new zzb(str, zza2, zzc2));
        return zzc2;
    }

    public zzqm<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, (byte[]) null);
    }
}

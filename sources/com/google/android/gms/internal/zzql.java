package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zzme
public class zzql {

    public interface zza<D, R> {
        R apply(D d);
    }

    public static <A, B> zzqm<B> zza(final zzqm<A> zzqm, final zza<A, B> zza2) {
        final zzqj zzqj = new zzqj();
        zzqm.zzc(new Runnable() {
            public void run() {
                try {
                    zzqj.this.zzh(zza2.apply(zzqm.get()));
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    zzqj.this.cancel(true);
                }
            }
        });
        return zzqj;
    }

    public static <T> T zza(Future<T> future, T t, int i, TimeUnit timeUnit) {
        try {
            return future.get((long) i, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return t;
        } catch (Exception e2) {
            return t;
        }
    }

    public static <V> zzqm<List<V>> zzo(final List<zzqm<V>> list) {
        final zzqj zzqj = new zzqj();
        final int size = list.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzqm<V> zzc : list) {
            zzc.zzc(new Runnable() {
                public void run() {
                    if (atomicInteger.incrementAndGet() >= size) {
                        try {
                            zzqj.zzh(zzql.zzp(list));
                        } catch (InterruptedException | ExecutionException e) {
                            zzpk.zzc("Unable to convert list of futures to a future of list", e);
                        }
                    }
                }
            });
        }
        return zzqj;
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzp(List<zzqm<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzqm<V> zzqm : list) {
            Object obj = zzqm.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

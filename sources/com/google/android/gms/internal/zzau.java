package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class zzau extends zzas {
    private static final String TAG = zzau.class.getSimpleName();
    private static long startTime = 0;
    protected static final Object zzpS = new Object();
    static boolean zzpT = false;
    protected static volatile zzbd zzpz = null;
    protected boolean zzpU = false;
    protected String zzpV;
    protected boolean zzpW = false;
    protected boolean zzpX = false;

    protected zzau(Context context, String str) {
        super(context);
        this.zzpV = str;
        this.zzpU = false;
    }

    protected zzau(Context context, String str, boolean z) {
        super(context);
        this.zzpV = str;
        this.zzpU = z;
    }

    static zzbe zza(zzbd zzbd, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzba {
        Method zzc = zzbd.zzc(zzaz.zzaC(), zzaz.zzaD());
        if (zzc == null || motionEvent == null) {
            throw new zzba();
        }
        try {
            return new zzbe((String) zzc.invoke((Object) null, new Object[]{motionEvent, displayMetrics}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzau.class) {
            if (!zzpT) {
                startTime = zzbf.zzbb().longValue() / 1000;
                zzpz = zzb(context, z);
                zzpT = true;
            }
        }
    }

    private static void zza(zzbd zzbd) {
        List<Context> singletonList = Collections.singletonList(Context.class);
        zzbd.zza(zzaz.zzai(), zzaz.zzaj(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzas(), zzaz.zzat(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzaq(), zzaz.zzar(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzac(), zzaz.zzad(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzam(), zzaz.zzan(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzW(), zzaz.zzX(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzaE(), zzaz.zzaF(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzY(), zzaz.zzZ(), (List<Class>) singletonList);
        List asList = Arrays.asList(new Class[]{MotionEvent.class, DisplayMetrics.class});
        zzbd.zza(zzaz.zzaC(), zzaz.zzaD(), (List<Class>) asList);
        zzbd.zza(zzaz.zzaA(), zzaz.zzaB(), (List<Class>) asList);
        zzbd.zza(zzaz.zzag(), zzaz.zzah(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzay(), zzaz.zzaz(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzao(), zzaz.zzap(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzae(), zzaz.zzaf(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzak(), zzaz.zzal(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzaw(), zzaz.zzax(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzaa(), zzaz.zzab(), (List<Class>) Arrays.asList(new Class[]{Context.class, Boolean.TYPE}));
        zzbd.zza(zzaz.zzau(), zzaz.zzav(), (List<Class>) Arrays.asList(new Class[]{StackTraceElement[].class}));
        zzbd.zza(zzaz.zzaG(), zzaz.zzaH(), (List<Class>) Arrays.asList(new Class[]{View.class}));
    }

    private void zza(zzbd zzbd, zzag.zza zza) {
        int i = 1;
        try {
            zzbe zza2 = zza(zzbd, this.zzpF, this.zzpQ);
            zza.zzbn = zza2.zzqI;
            zza.zzbo = zza2.zzqJ;
            zza.zzbp = zza2.zzqK;
            if (this.zzpP) {
                zza.zzbB = zza2.zzcf;
                zza.zzbC = zza2.zzcd;
            }
            zzag.zza.C0789zza zza3 = new zzag.zza.C0789zza();
            zzbe zzb = zzb(this.zzpF);
            zza3.zzbn = zzb.zzqI;
            zza3.zzbo = zzb.zzqJ;
            zza3.zzci = zzb.zzqK;
            if (this.zzpP) {
                zza3.zzcd = zzb.zzcd;
                zza3.zzcf = zzb.zzcf;
                zza3.zzch = Integer.valueOf(zzb.zzqL.longValue() != 0 ? 1 : 0);
                if (this.zzpI > 0) {
                    zza3.zzce = this.zzpQ != null ? Long.valueOf(Math.round(((double) this.zzpN) / ((double) this.zzpI))) : null;
                    zza3.zzcg = Long.valueOf(Math.round(((double) this.zzpM) / ((double) this.zzpI)));
                }
                zza3.zzck = zzb.zzck;
                zza3.zzcj = zzb.zzcj;
                if (zzb.zzqO.longValue() == 0) {
                    i = 0;
                }
                zza3.zzcl = Integer.valueOf(i);
                if (this.zzpL > 0) {
                    zza3.zzcm = Long.valueOf(this.zzpL);
                }
            }
            zza.zzbS = zza3;
        } catch (zzba e) {
        }
        if (this.zzpH > 0) {
            zza.zzbG = Long.valueOf(this.zzpH);
        }
        if (this.zzpI > 0) {
            zza.zzbF = Long.valueOf(this.zzpI);
        }
        if (this.zzpJ > 0) {
            zza.zzbE = Long.valueOf(this.zzpJ);
        }
        if (this.zzpK > 0) {
            zza.zzbH = Long.valueOf(this.zzpK);
        }
        try {
            int size = this.zzpG.size() - 1;
            if (size > 0) {
                zza.zzbT = new zzag.zza.C0789zza[size];
                for (int i2 = 0; i2 < size; i2++) {
                    zzbe zza4 = zza(zzbd, (MotionEvent) this.zzpG.get(i2), this.zzpQ);
                    zzag.zza.C0789zza zza5 = new zzag.zza.C0789zza();
                    zza5.zzbn = zza4.zzqI;
                    zza5.zzbo = zza4.zzqJ;
                    zza.zzbT[i2] = zza5;
                }
            }
        } catch (zzba e2) {
            zza.zzbT = null;
        }
    }

    protected static zzbd zzb(Context context, boolean z) {
        if (zzpz == null) {
            synchronized (zzpS) {
                if (zzpz == null) {
                    zzbd zza = zzbd.zza(context, zzaz.getKey(), zzaz.zzV(), z);
                    zza(zza);
                    zzpz = zza;
                }
            }
        }
        return zzpz;
    }

    /* access modifiers changed from: protected */
    public long zza(StackTraceElement[] stackTraceElementArr) throws zzba {
        Method zzc = zzpz.zzc(zzaz.zzau(), zzaz.zzav());
        if (zzc == null || stackTraceElementArr == null) {
            throw new zzba();
        }
        try {
            return new zzbb((String) zzc.invoke((Object) null, new Object[]{stackTraceElementArr})).zzqi.longValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    /* access modifiers changed from: protected */
    public zzag.zza zza(Context context, View view) {
        zzag.zza zza = new zzag.zza();
        if (!TextUtils.isEmpty(this.zzpV)) {
            zza.zzba = this.zzpV;
        }
        zzbd zzb = zzb(context, this.zzpU);
        zzb.zzaZ();
        zzb(zzb, zza, view);
        zzb.zzba();
        return zza;
    }

    /* access modifiers changed from: protected */
    public zzag.zza zza(Context context, zzae.zza zza) {
        zzag.zza zza2 = new zzag.zza();
        if (!TextUtils.isEmpty(this.zzpV)) {
            zza2.zzba = this.zzpV;
        }
        zzbd zzb = zzb(context, this.zzpU);
        zzb.zzaZ();
        zza(zzb, zza2, zza);
        zzb.zzba();
        return zza2;
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zza(zzbd zzbd, zzag.zza zza, View view) {
        ArrayList arrayList = new ArrayList();
        if (zzbd.zzaI() == null) {
            return arrayList;
        }
        int zzQ = zzbd.zzQ();
        arrayList.add(new zzbp(zzbd, zza));
        ArrayList arrayList2 = arrayList;
        arrayList2.add(new zzbs(zzbd, zzaz.zzao(), zzaz.zzap(), zza, zzQ, 1));
        ArrayList arrayList3 = arrayList;
        arrayList3.add(new zzbn(zzbd, zzaz.zzag(), zzaz.zzah(), zza, startTime, zzQ, 25));
        ArrayList arrayList4 = arrayList;
        arrayList4.add(new zzbm(zzbd, zzaz.zzae(), zzaz.zzaf(), zza, zzQ, 44));
        ArrayList arrayList5 = arrayList;
        arrayList5.add(new zzbh(zzbd, zzaz.zzW(), zzaz.zzX(), zza, zzQ, 3));
        ArrayList arrayList6 = arrayList;
        arrayList6.add(new zzbq(zzbd, zzaz.zzak(), zzaz.zzal(), zza, zzQ, 22));
        ArrayList arrayList7 = arrayList;
        arrayList7.add(new zzbl(zzbd, zzaz.zzac(), zzaz.zzad(), zza, zzQ, 5));
        ArrayList arrayList8 = arrayList;
        arrayList8.add(new zzbx(zzbd, zzaz.zzaE(), zzaz.zzaF(), zza, zzQ, 48));
        if (zzgd.zzDR.get().booleanValue()) {
            ArrayList arrayList9 = arrayList;
            arrayList9.add(new zzbi(zzbd, zzaz.zzY(), zzaz.zzZ(), zza, zzQ, 49));
        }
        ArrayList arrayList10 = arrayList;
        arrayList10.add(new zzbv(zzbd, zzaz.zzaw(), zzaz.zzax(), zza, zzQ, 51));
        ArrayList arrayList11 = arrayList;
        arrayList11.add(new zzbu(zzbd, zzaz.zzau(), zzaz.zzav(), zza, zzQ, 45, new Throwable().getStackTrace()));
        if (zzgd.zzDS.get().booleanValue()) {
            ArrayList arrayList12 = arrayList;
            arrayList12.add(new zzby(zzbd, zzaz.zzaG(), zzaz.zzaH(), zza, zzQ, 57, view));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zza(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        if (zzbd.zzaI() != null) {
            zza(zzb(zzbd, zza, zza2));
        }
    }

    /* access modifiers changed from: protected */
    public void zza(List<Callable<Void>> list) {
        ExecutorService zzaI;
        if (zzpz != null && (zzaI = zzpz.zzaI()) != null && !list.isEmpty()) {
            try {
                zzaI.invokeAll(list, zzgd.zzDO.get().longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzbf.zza(e)}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzbe zzb(MotionEvent motionEvent) throws zzba {
        Method zzc = zzpz.zzc(zzaz.zzaA(), zzaz.zzaB());
        if (zzc == null || motionEvent == null) {
            throw new zzba();
        }
        try {
            return new zzbe((String) zzc.invoke((Object) null, new Object[]{motionEvent, this.zzpQ}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        int zzQ = zzbd.zzQ();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = arrayList;
        arrayList2.add(new zzbk(zzbd, zzaz.zzaa(), zzaz.zzab(), zza, zzQ, 27, zza2));
        ArrayList arrayList3 = arrayList;
        arrayList3.add(new zzbn(zzbd, zzaz.zzag(), zzaz.zzah(), zza, startTime, zzQ, 25));
        ArrayList arrayList4 = arrayList;
        arrayList4.add(new zzbs(zzbd, zzaz.zzao(), zzaz.zzap(), zza, zzQ, 1));
        ArrayList arrayList5 = arrayList;
        arrayList5.add(new zzbt(zzbd, zzaz.zzaq(), zzaz.zzar(), zza, zzQ, 31));
        ArrayList arrayList6 = arrayList;
        arrayList6.add(new zzbw(zzbd, zzaz.zzay(), zzaz.zzaz(), zza, zzQ, 33));
        ArrayList arrayList7 = arrayList;
        arrayList7.add(new zzbj(zzbd, zzaz.zzas(), zzaz.zzat(), zza, zzQ, 29));
        ArrayList arrayList8 = arrayList;
        arrayList8.add(new zzbl(zzbd, zzaz.zzac(), zzaz.zzad(), zza, zzQ, 5));
        ArrayList arrayList9 = arrayList;
        arrayList9.add(new zzbr(zzbd, zzaz.zzam(), zzaz.zzan(), zza, zzQ, 12));
        ArrayList arrayList10 = arrayList;
        arrayList10.add(new zzbh(zzbd, zzaz.zzW(), zzaz.zzX(), zza, zzQ, 3));
        ArrayList arrayList11 = arrayList;
        arrayList11.add(new zzbm(zzbd, zzaz.zzae(), zzaz.zzaf(), zza, zzQ, 44));
        ArrayList arrayList12 = arrayList;
        arrayList12.add(new zzbq(zzbd, zzaz.zzak(), zzaz.zzal(), zza, zzQ, 22));
        ArrayList arrayList13 = arrayList;
        arrayList13.add(new zzbx(zzbd, zzaz.zzaE(), zzaz.zzaF(), zza, zzQ, 48));
        if (zzgd.zzDQ.get().booleanValue()) {
            ArrayList arrayList14 = arrayList;
            arrayList14.add(new zzbi(zzbd, zzaz.zzY(), zzaz.zzZ(), zza, zzQ, 49));
        }
        ArrayList arrayList15 = arrayList;
        arrayList15.add(new zzbv(zzbd, zzaz.zzaw(), zzaz.zzax(), zza, zzQ, 51));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zzb(zzbd zzbd, zzag.zza zza, View view) {
        zza(zzbd, zza);
        zza(zza(zzbd, zza, view));
    }
}

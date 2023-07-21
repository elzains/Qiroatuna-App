package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzas implements zzar {
    protected MotionEvent zzpF;
    protected LinkedList<MotionEvent> zzpG = new LinkedList<>();
    protected long zzpH = 0;
    protected long zzpI = 0;
    protected long zzpJ = 0;
    protected long zzpK = 0;
    protected long zzpL = 0;
    protected long zzpM = 0;
    protected long zzpN = 0;
    private boolean zzpO = false;
    protected boolean zzpP = false;
    protected DisplayMetrics zzpQ;

    protected zzas(Context context) {
        try {
            zzao.zzO();
            this.zzpQ = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
        }
    }

    private String zza(Context context, String str, boolean z, View view, byte[] bArr) {
        zzag.zza zza;
        boolean z2 = true;
        zzae.zza zza2 = null;
        if (bArr != null && bArr.length > 0) {
            try {
                zza2 = zzae.zza.zzc(bArr);
            } catch (zzbxs e) {
            }
        }
        if (z) {
            try {
                zza = zza(context, view);
                this.zzpO = true;
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e2) {
                return Integer.toString(7);
            } catch (Throwable th) {
                return Integer.toString(3);
            }
        } else {
            zza = zza(context, zza2);
        }
        if (zza == null || zza.zzaeS() == 0) {
            return Integer.toString(5);
        }
        if (zzb(z)) {
            z2 = false;
        }
        return zzao.zza(zza, str, z2);
    }

    private static boolean zza(zzbe zzbe) {
        return (zzbe == null || zzbe.zzcf == null || zzbe.zzqM == null) ? false : true;
    }

    private boolean zzb(zzbe zzbe) {
        return (this.zzpQ == null || zzbe == null || zzbe.zzcd == null || zzbe.zzqN == null) ? false : true;
    }

    private static boolean zzb(boolean z) {
        if (!zzgd.zzDP.get().booleanValue()) {
            return true;
        }
        return zzgd.zzDV.get().booleanValue() && z;
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzba;

    /* access modifiers changed from: protected */
    public abstract zzag.zza zza(Context context, View view);

    /* access modifiers changed from: protected */
    public abstract zzag.zza zza(Context context, zzae.zza zza);

    public String zza(Context context, String str, View view) {
        return zza(context, str, true, view, (byte[]) null);
    }

    public String zza(Context context, byte[] bArr) {
        if (!zzbf.zzbc() || !zzgd.zzDU.get().booleanValue()) {
            return zza(context, (String) null, false, (View) null, bArr);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzpF != null) {
            this.zzpF.recycle();
        }
        if (this.zzpQ != null) {
            this.zzpF = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzpQ.density, ((float) i2) * this.zzpQ.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzpF = null;
        }
        this.zzpP = false;
    }

    public void zza(MotionEvent motionEvent) {
        if (this.zzpO) {
            this.zzpK = 0;
            this.zzpJ = 0;
            this.zzpI = 0;
            this.zzpH = 0;
            this.zzpL = 0;
            this.zzpN = 0;
            this.zzpM = 0;
            Iterator it = this.zzpG.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzpG.clear();
            this.zzpF = null;
            this.zzpO = false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzpH++;
                break;
            case 1:
                this.zzpF = MotionEvent.obtain(motionEvent);
                this.zzpG.add(this.zzpF);
                if (this.zzpG.size() > 6) {
                    this.zzpG.remove().recycle();
                }
                this.zzpJ++;
                try {
                    this.zzpL = zza(new Throwable().getStackTrace());
                    break;
                } catch (zzba e) {
                    break;
                }
            case 2:
                this.zzpI += (long) (motionEvent.getHistorySize() + 1);
                try {
                    zzbe zzb = zzb(motionEvent);
                    if (zza(zzb)) {
                        this.zzpM += zzb.zzcf.longValue() + zzb.zzqM.longValue();
                    }
                    if (zzb(zzb)) {
                        this.zzpN = zzb.zzqN.longValue() + zzb.zzcd.longValue() + this.zzpN;
                        break;
                    }
                } catch (zzba e2) {
                    break;
                }
                break;
            case 3:
                this.zzpK++;
                break;
        }
        this.zzpP = true;
    }

    /* access modifiers changed from: protected */
    public abstract zzbe zzb(MotionEvent motionEvent) throws zzba;

    public String zzb(Context context) {
        if (!zzbf.zzbc() || !zzgd.zzDU.get().booleanValue()) {
            return zza(context, (String) null, false, (View) null, (byte[]) null);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public String zzb(Context context, String str) {
        return zza(context, str, (View) null);
    }
}

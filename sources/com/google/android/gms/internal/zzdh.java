package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import com.google.android.gms.internal.zzdk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzme
public class zzdh {
    private final int zzyn;
    private final int zzyo;
    private final int zzyp;
    private final zzdg zzyq = new zzdj();

    static class zza {
        ByteArrayOutputStream zzyr = new ByteArrayOutputStream(4096);
        Base64OutputStream zzys = new Base64OutputStream(this.zzyr, 10);

        public String toString() {
            String str;
            try {
                this.zzys.close();
            } catch (IOException e) {
                zzpk.zzb("HashManager: Unable to convert to Base64.", e);
            }
            try {
                this.zzyr.close();
                str = this.zzyr.toString();
            } catch (IOException e2) {
                zzpk.zzb("HashManager: Unable to convert to Base64.", e2);
                str = "";
            } finally {
                this.zzyr = null;
                this.zzys = null;
            }
            return str;
        }

        public void write(byte[] bArr) throws IOException {
            this.zzys.write(bArr);
        }
    }

    public zzdh(int i) {
        this.zzyo = i;
        this.zzyn = 6;
        this.zzyp = 0;
    }

    /* access modifiers changed from: package-private */
    public String zzG(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zza zzeu = zzeu();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzyo, new Comparator<zzdk.zza>(this) {
            /* renamed from: zza */
            public int compare(zzdk.zza zza, zzdk.zza zza2) {
                int i = zza.zzyv - zza2.zzyv;
                return i != 0 ? i : (int) (zza.value - zza2.value);
            }
        });
        for (String zzI : split) {
            String[] zzI2 = zzdi.zzI(zzI);
            if (zzI2.length != 0) {
                zzdk.zza(zzI2, this.zzyo, this.zzyn, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzeu.write(this.zzyq.zzF(((zzdk.zza) it.next()).zzyu));
            } catch (IOException e) {
                zzpk.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzeu.toString();
    }

    public String zza(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toLowerCase(Locale.US));
            stringBuffer.append(10);
        }
        return zzG(stringBuffer.toString());
    }

    /* access modifiers changed from: package-private */
    public zza zzeu() {
        return new zza();
    }
}

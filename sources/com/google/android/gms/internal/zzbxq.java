package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzbxq implements Cloneable {
    private Object value;
    private zzbxo<?, ?> zzcuG;
    private List<zzbxv> zzcuH = new ArrayList();

    zzbxq() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzu()];
        zza(zzbxm.zzag(bArr));
        return bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxq)) {
            return false;
        }
        zzbxq zzbxq = (zzbxq) obj;
        if (this.value == null || zzbxq.value == null) {
            if (this.zzcuH != null && zzbxq.zzcuH != null) {
                return this.zzcuH.equals(zzbxq.zzcuH);
            }
            try {
                return Arrays.equals(toByteArray(), zzbxq.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcuG == zzbxq.zzcuG) {
            return !this.zzcuG.zzckQ.isArray() ? this.value.equals(zzbxq.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbxq.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbxq.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbxq.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbxq.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbxq.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbxq.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbxq.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxm zzbxm) throws IOException {
        if (this.value != null) {
            this.zzcuG.zza(this.value, zzbxm);
            return;
        }
        for (zzbxv zza : this.zzcuH) {
            zza.zza(zzbxm);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxv zzbxv) {
        this.zzcuH.add(zzbxv);
    }

    /* renamed from: zzaeJ */
    public final zzbxq clone() {
        int i = 0;
        zzbxq zzbxq = new zzbxq();
        try {
            zzbxq.zzcuG = this.zzcuG;
            if (this.zzcuH == null) {
                zzbxq.zzcuH = null;
            } else {
                zzbxq.zzcuH.addAll(this.zzcuH);
            }
            if (this.value != null) {
                if (this.value instanceof zzbxt) {
                    zzbxq.value = (zzbxt) ((zzbxt) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbxq.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzbxq.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbxq.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbxq.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbxq.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbxq.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbxq.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbxt[]) {
                    zzbxt[] zzbxtArr = (zzbxt[]) this.value;
                    zzbxt[] zzbxtArr2 = new zzbxt[zzbxtArr.length];
                    zzbxq.value = zzbxtArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzbxtArr.length) {
                            break;
                        }
                        zzbxtArr2[i3] = (zzbxt) zzbxtArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzbxq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T zzb(zzbxo<?, T> zzbxo) {
        if (this.value == null) {
            this.zzcuG = zzbxo;
            this.value = zzbxo.zzac(this.zzcuH);
            this.zzcuH = null;
        } else if (!this.zzcuG.equals(zzbxo)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public int zzu() {
        int i = 0;
        if (this.value != null) {
            return this.zzcuG.zzaU(this.value);
        }
        Iterator<zzbxv> it = this.zzcuH.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = it.next().zzu() + i2;
        }
    }
}

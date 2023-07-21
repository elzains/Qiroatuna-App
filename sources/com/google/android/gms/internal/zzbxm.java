package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzbxm {
    private final ByteBuffer zzcuz;

    public static class zza extends IOException {
        zza(int i, int i2) {
            super(new StringBuilder(108).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(i).append(" limit ").append(i2).append(").").toString());
        }
    }

    private zzbxm(ByteBuffer byteBuffer) {
        this.zzcuz = byteBuffer;
        this.zzcuz.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbxm(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzL(int i, int i2) {
        return zzri(i) + zzrf(i2);
    }

    public static int zzM(int i, int i2) {
        return zzri(i) + zzrg(i2);
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3).toString());
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        int i4 = 0;
        int i5 = i + i2;
        while (i4 < length && i4 + i < i5) {
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i4] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i5) {
                i3 = i6 + 1;
                bArr[i6] = (byte) charAt2;
            } else if (charAt2 < 2048 && i6 <= i5 - 2) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i5 - 3) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | 480);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if (i6 <= i5 - 4) {
                if (i4 + 1 != charSequence.length()) {
                    i4++;
                    char charAt3 = charSequence.charAt(i4);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | 240);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i3 = i12 + 1;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i4 - 1).toString());
            } else {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charAt2).append(" at index ").append(i6).toString());
            }
            i4++;
            i6 = i3;
        }
        return i6;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static zzbxm zzag(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzai(byte[] bArr) {
        return zzrk(bArr.length) + bArr.length;
    }

    public static int zzb(int i, double d) {
        return zzri(i) + 8;
    }

    public static int zzb(int i, zzbxt zzbxt) {
        return (zzri(i) * 2) + zzd(zzbxt);
    }

    private static int zzb(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = i;
        int i3 = length;
        while (true) {
            if (i2 < length) {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 2048) {
                    i3 += zza(charSequence, i2);
                    break;
                }
                i2++;
                i3 = ((127 - charAt) >>> 31) + i3;
            } else {
                break;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) i3) + 4294967296L).toString());
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int codePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i - 1).toString());
            }
            i++;
        }
    }

    public static int zzbe(long j) {
        return zzbi(j);
    }

    public static int zzbf(long j) {
        return zzbi(j);
    }

    public static int zzbg(long j) {
        return zzbi(zzbk(j));
    }

    public static int zzbi(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long zzbk(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzc(int i, zzbxt zzbxt) {
        return zzri(i) + zze(zzbxt);
    }

    public static int zzc(int i, byte[] bArr) {
        return zzri(i) + zzai(bArr);
    }

    public static zzbxm zzc(byte[] bArr, int i, int i2) {
        return new zzbxm(bArr, i, i2);
    }

    public static int zzd(int i, float f) {
        return zzri(i) + 4;
    }

    public static int zzd(zzbxt zzbxt) {
        return zzbxt.zzaeS();
    }

    public static int zze(int i, long j) {
        return zzri(i) + zzbe(j);
    }

    public static int zze(zzbxt zzbxt) {
        int zzaeS = zzbxt.zzaeS();
        return zzaeS + zzrk(zzaeS);
    }

    public static int zzf(int i, long j) {
        return zzri(i) + zzbf(j);
    }

    public static int zzg(int i, long j) {
        return zzri(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzri(i) + zzbg(j);
    }

    public static int zzh(int i, boolean z) {
        return zzri(i) + 1;
    }

    public static int zzkb(String str) {
        int zzb = zzb((CharSequence) str);
        return zzb + zzrk(zzb);
    }

    public static int zzr(int i, String str) {
        return zzri(i) + zzkb(str);
    }

    public static int zzrf(int i) {
        if (i >= 0) {
            return zzrk(i);
        }
        return 10;
    }

    public static int zzrg(int i) {
        return zzrk(zzrm(i));
    }

    public static int zzri(int i) {
        return zzrk(zzbxw.zzO(i, 0));
    }

    public static int zzrk(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzrm(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public void zzJ(int i, int i2) throws IOException {
        zzN(i, 0);
        zzrd(i2);
    }

    public void zzK(int i, int i2) throws IOException {
        zzN(i, 0);
        zzre(i2);
    }

    public void zzN(int i, int i2) throws IOException {
        zzrj(zzbxw.zzO(i, i2));
    }

    public void zza(int i, double d) throws IOException {
        zzN(i, 1);
        zzn(d);
    }

    public void zza(int i, long j) throws IOException {
        zzN(i, 0);
        zzba(j);
    }

    public void zza(int i, zzbxt zzbxt) throws IOException {
        zzN(i, 2);
        zzc(zzbxt);
    }

    public int zzaeE() {
        return this.zzcuz.remaining();
    }

    public void zzaeF() {
        if (zzaeE() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zzah(byte[] bArr) throws IOException {
        zzrj(bArr.length);
        zzaj(bArr);
    }

    public void zzaj(byte[] bArr) throws IOException {
        zzd(bArr, 0, bArr.length);
    }

    public void zzb(int i, long j) throws IOException {
        zzN(i, 0);
        zzbb(j);
    }

    public void zzb(int i, byte[] bArr) throws IOException {
        zzN(i, 2);
        zzah(bArr);
    }

    public void zzb(zzbxt zzbxt) throws IOException {
        zzbxt.zza(this);
    }

    public void zzba(long j) throws IOException {
        zzbh(j);
    }

    public void zzbb(long j) throws IOException {
        zzbh(j);
    }

    public void zzbc(long j) throws IOException {
        zzbj(j);
    }

    public void zzbd(long j) throws IOException {
        zzbh(zzbk(j));
    }

    public void zzbh(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzrh((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        zzrh((int) j);
    }

    public void zzbj(long j) throws IOException {
        if (this.zzcuz.remaining() < 8) {
            throw new zza(this.zzcuz.position(), this.zzcuz.limit());
        }
        this.zzcuz.putLong(j);
    }

    public void zzbq(boolean z) throws IOException {
        zzrh(z ? 1 : 0);
    }

    public void zzc(byte b) throws IOException {
        if (!this.zzcuz.hasRemaining()) {
            throw new zza(this.zzcuz.position(), this.zzcuz.limit());
        }
        this.zzcuz.put(b);
    }

    public void zzc(int i, float f) throws IOException {
        zzN(i, 5);
        zzk(f);
    }

    public void zzc(int i, long j) throws IOException {
        zzN(i, 1);
        zzbc(j);
    }

    public void zzc(zzbxt zzbxt) throws IOException {
        zzrj(zzbxt.zzaeR());
        zzbxt.zza(this);
    }

    public void zzd(int i, long j) throws IOException {
        zzN(i, 0);
        zzbd(j);
    }

    public void zzd(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzcuz.remaining() >= i2) {
            this.zzcuz.put(bArr, i, i2);
            return;
        }
        throw new zza(this.zzcuz.position(), this.zzcuz.limit());
    }

    public void zzg(int i, boolean z) throws IOException {
        zzN(i, 0);
        zzbq(z);
    }

    public void zzk(float f) throws IOException {
        zzrl(Float.floatToIntBits(f));
    }

    public void zzka(String str) throws IOException {
        try {
            int zzrk = zzrk(str.length());
            if (zzrk == zzrk(str.length() * 3)) {
                int position = this.zzcuz.position();
                if (this.zzcuz.remaining() < zzrk) {
                    throw new zza(zzrk + position, this.zzcuz.limit());
                }
                this.zzcuz.position(position + zzrk);
                zza((CharSequence) str, this.zzcuz);
                int position2 = this.zzcuz.position();
                this.zzcuz.position(position);
                zzrj((position2 - position) - zzrk);
                this.zzcuz.position(position2);
                return;
            }
            zzrj(zzb((CharSequence) str));
            zza((CharSequence) str, this.zzcuz);
        } catch (BufferOverflowException e) {
            zza zza2 = new zza(this.zzcuz.position(), this.zzcuz.limit());
            zza2.initCause(e);
            throw zza2;
        }
    }

    public void zzn(double d) throws IOException {
        zzbj(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) throws IOException {
        zzN(i, 2);
        zzka(str);
    }

    public void zzrd(int i) throws IOException {
        if (i >= 0) {
            zzrj(i);
        } else {
            zzbh((long) i);
        }
    }

    public void zzre(int i) throws IOException {
        zzrj(zzrm(i));
    }

    public void zzrh(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzrj(int i) throws IOException {
        while ((i & -128) != 0) {
            zzrh((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        zzrh(i);
    }

    public void zzrl(int i) throws IOException {
        if (this.zzcuz.remaining() < 4) {
            throw new zza(this.zzcuz.position(), this.zzcuz.limit());
        }
        this.zzcuz.putInt(i);
    }
}

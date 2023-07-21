package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzc;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
public final class DataHolder extends com.google.android.gms.common.internal.safeparcel.zza implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zze();
    private static final zza zzaDW = new zza(new String[0], (String) null) {
        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public zza zza(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    };
    boolean mClosed;
    private final String[] zzaDP;
    Bundle zzaDQ;
    private final CursorWindow[] zzaDR;
    private final Bundle zzaDS;
    int[] zzaDT;
    int zzaDU;
    private boolean zzaDV;
    final int zzaiI;
    private final int zzavD;

    public static class zza {
        /* access modifiers changed from: private */
        public final String[] zzaDP;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zzaDX;
        private final String zzaDY;
        private final HashMap<Object, Integer> zzaDZ;
        private boolean zzaEa;
        private String zzaEb;

        private zza(String[] strArr, String str) {
            this.zzaDP = (String[]) zzac.zzw(strArr);
            this.zzaDX = new ArrayList<>();
            this.zzaDY = str;
            this.zzaDZ = new HashMap<>();
            this.zzaEa = false;
            this.zzaEb = null;
        }

        private int zzb(HashMap<String, Object> hashMap) {
            if (this.zzaDY == null) {
                return -1;
            }
            Object obj = hashMap.get(this.zzaDY);
            if (obj == null) {
                return -1;
            }
            Integer num = this.zzaDZ.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.zzaDZ.put(obj, Integer.valueOf(this.zzaDX.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            zzc.zzt(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Map.Entry next : contentValues.valueSet()) {
                hashMap.put((String) next.getKey(), next.getValue());
            }
            return zza((HashMap<String, Object>) hashMap);
        }

        public zza zza(HashMap<String, Object> hashMap) {
            zzc.zzt(hashMap);
            int zzb = zzb(hashMap);
            if (zzb == -1) {
                this.zzaDX.add(hashMap);
            } else {
                this.zzaDX.remove(zzb);
                this.zzaDX.add(zzb, hashMap);
            }
            this.zzaEa = false;
            return this;
        }

        public DataHolder zzcK(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.zzaDV = true;
        this.zzaiI = i;
        this.zzaDP = strArr;
        this.zzaDR = cursorWindowArr;
        this.zzavD = i2;
        this.zzaDS = bundle;
    }

    private DataHolder(zza zza2, int i, Bundle bundle) {
        this(zza2.zzaDP, zza(zza2, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zzaDV = true;
        this.zzaiI = 1;
        this.zzaDP = (String[]) zzac.zzw(strArr);
        this.zzaDR = (CursorWindow[]) zzac.zzw(cursorWindowArr);
        this.zzavD = i;
        this.zzaDS = bundle;
        zzxk();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(zzaDW, i, bundle);
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.zzaDP.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zzb2 = (i < 0 || i >= zza2.zzaDX.size()) ? zza2.zzaDX : zza2.zzaDX.subList(0, i);
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.zzaDP.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(i3).append(")").toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.zzaDP.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.zzaDP.length && z3; i4++) {
                    String str = zza2.zzaDP[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(i3).append(" - allocating new window.").toString());
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.zzaDP.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzc(String[] strArr) {
        return new zza(strArr, (String) null);
    }

    public static DataHolder zzcJ(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzi(String str, int i) {
        if (this.zzaDQ == null || !this.zzaDQ.containsKey(str)) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zzaDU) {
            throw new CursorIndexOutOfBoundsException(i, this.zzaDU);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.zzaDR) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.zzaDV && this.zzaDR.length > 0 && !isClosed()) {
                close();
                String valueOf = String.valueOf(toString());
                Log.e("DataBuffer", new StringBuilder(String.valueOf(valueOf).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(valueOf).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.zzaDU;
    }

    public int getStatusCode() {
        return this.zzavD;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzi(str, i);
        this.zzaDR[i2].copyStringToBuffer(i, this.zzaDQ.getInt(str), charArrayBuffer);
    }

    public long zzb(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].getLong(i, this.zzaDQ.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].getInt(i, this.zzaDQ.getInt(str));
    }

    public int zzcI(int i) {
        int i2 = 0;
        zzac.zzaw(i >= 0 && i < this.zzaDU);
        while (true) {
            if (i2 >= this.zzaDT.length) {
                break;
            } else if (i < this.zzaDT[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zzaDT.length ? i2 - 1 : i2;
    }

    public String zzd(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].getString(i, this.zzaDQ.getInt(str));
    }

    public boolean zzdf(String str) {
        return this.zzaDQ.containsKey(str);
    }

    public boolean zze(String str, int i, int i2) {
        zzi(str, i);
        return Long.valueOf(this.zzaDR[i2].getLong(i, this.zzaDQ.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].getFloat(i, this.zzaDQ.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].getBlob(i, this.zzaDQ.getInt(str));
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzi(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaDR[i2].isNull(i, this.zzaDQ.getInt(str));
    }

    public Bundle zzxf() {
        return this.zzaDS;
    }

    public void zzxk() {
        this.zzaDQ = new Bundle();
        for (int i = 0; i < this.zzaDP.length; i++) {
            this.zzaDQ.putInt(this.zzaDP[i], i);
        }
        this.zzaDT = new int[this.zzaDR.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzaDR.length; i3++) {
            this.zzaDT[i3] = i2;
            i2 += this.zzaDR[i3].getNumRows() - (i2 - this.zzaDR[i3].getStartPosition());
        }
        this.zzaDU = i2;
    }

    /* access modifiers changed from: package-private */
    public String[] zzxl() {
        return this.zzaDP;
    }

    /* access modifiers changed from: package-private */
    public CursorWindow[] zzxm() {
        return this.zzaDR;
    }
}

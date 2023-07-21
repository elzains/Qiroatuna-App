package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzaDN = {"data"};
    private final Parcelable.Creator<T> zzaDO;

    public zzd(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zzaDO = creator;
    }

    public static <T extends SafeParcelable> void zza(DataHolder.zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static DataHolder.zza zzxj() {
        return DataHolder.zzc(zzaDN);
    }

    /* renamed from: zzcH */
    public T get(int i) {
        byte[] zzg = this.zzaBi.zzg("data", i, this.zzaBi.zzcI(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) this.zzaDO.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}

package com.google.android.gms.iid;

import android.annotation.TargetApi;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.iid.zzb;

public class MessengerCompat implements ReflectedParcelable {
    public static final Parcelable.Creator<MessengerCompat> CREATOR = new Parcelable.Creator<MessengerCompat>() {
        /* renamed from: zzgs */
        public MessengerCompat createFromParcel(Parcel parcel) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                return new MessengerCompat(readStrongBinder);
            }
            return null;
        }

        /* renamed from: zzjK */
        public MessengerCompat[] newArray(int i) {
            return new MessengerCompat[i];
        }
    };
    Messenger zzbhU;
    zzb zzbhV;

    private final class zza extends zzb.zza {
        Handler handler;

        zza(MessengerCompat messengerCompat, Handler handler2) {
            this.handler = handler2;
        }

        public void send(Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }

    public MessengerCompat(Handler handler) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.zzbhU = new Messenger(handler);
        } else {
            this.zzbhV = new zza(this, handler);
        }
    }

    public MessengerCompat(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.zzbhU = new Messenger(iBinder);
        } else {
            this.zzbhV = zzb.zza.zzcZ(iBinder);
        }
    }

    public static int zzc(Message message) {
        return Build.VERSION.SDK_INT >= 21 ? zzd(message) : message.arg2;
    }

    @TargetApi(21)
    private static int zzd(Message message) {
        return message.sendingUid;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return getBinder().equals(((MessengerCompat) obj).getBinder());
        } catch (ClassCastException e) {
            return false;
        }
    }

    public IBinder getBinder() {
        return this.zzbhU != null ? this.zzbhU.getBinder() : this.zzbhV.asBinder();
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void send(Message message) throws RemoteException {
        if (this.zzbhU != null) {
            this.zzbhU.send(message);
        } else {
            this.zzbhV.send(message);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzbhU != null) {
            parcel.writeStrongBinder(this.zzbhU.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzbhV.asBinder());
        }
    }
}

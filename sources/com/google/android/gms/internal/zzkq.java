package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.C0156R;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
public class zzkq extends zzkw {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzFP;
    private String zzLS;
    private long zzLT;
    private long zzLU;
    private String zzLV;
    private String zzLW;

    public zzkq(zzqw zzqw, Map<String, String> map) {
        super(zzqw, "createCalendarEvent");
        this.zzFP = map;
        this.mContext = zzqw.zzlr();
        zzhj();
    }

    private String zzaw(String str) {
        return TextUtils.isEmpty(this.zzFP.get(str)) ? "" : this.zzFP.get(str);
    }

    private long zzax(String str) {
        String str2 = this.zzFP.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void zzhj() {
        this.zzLS = zzaw("description");
        this.zzLV = zzaw("summary");
        this.zzLT = zzax("start_ticks");
        this.zzLU = zzax("end_ticks");
        this.zzLW = zzaw("location");
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.zzLS);
        data.putExtra("eventLocation", this.zzLW);
        data.putExtra("description", this.zzLV);
        if (this.zzLT > -1) {
            data.putExtra("beginTime", this.zzLT);
        }
        if (this.zzLU > -1) {
            data.putExtra("endTime", this.zzLU);
        }
        data.setFlags(268435456);
        return data;
    }

    public void execute() {
        if (this.mContext == null) {
            zzaz("Activity context is not available.");
        } else if (!zzw.zzcM().zzN(this.mContext).zzfp()) {
            zzaz("This feature is not available on the device.");
        } else {
            AlertDialog.Builder zzM = zzw.zzcM().zzM(this.mContext);
            Resources resources = zzw.zzcQ().getResources();
            zzM.setTitle(resources != null ? resources.getString(C0156R.string.create_calendar_title) : "Create calendar event");
            zzM.setMessage(resources != null ? resources.getString(C0156R.string.create_calendar_message) : "Allow Ad to create a calendar event?");
            zzM.setPositiveButton(resources != null ? resources.getString(C0156R.string.accept) : "Accept", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    zzw.zzcM().zzb(zzkq.this.mContext, zzkq.this.createIntent());
                }
            });
            zzM.setNegativeButton(resources != null ? resources.getString(C0156R.string.decline) : "Decline", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    zzkq.this.zzaz("Operation denied by user.");
                }
            });
            zzM.create().show();
        }
    }
}

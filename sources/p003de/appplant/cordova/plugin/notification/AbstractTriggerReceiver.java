package p003de.appplant.cordova.plugin.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.AbstractTriggerReceiver */
public abstract class AbstractTriggerReceiver extends BroadcastReceiver {
    public abstract Notification buildNotification(Builder builder);

    public abstract void onTrigger(Notification notification, boolean z);

    public void onReceive(Context context, Intent intent) {
        try {
            Options options = new Options(context).parse(new JSONObject(intent.getExtras().getString("NOTIFICATION_OPTIONS")));
            if (options != null && !isFirstAlarmInFuture(options).booleanValue()) {
                Notification notification = buildNotification(new Builder(options));
                onTrigger(notification, notification.isUpdate(false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Boolean isFirstAlarmInFuture(Options options) {
        boolean z = false;
        Notification notification = new Builder(options).build();
        if (!notification.isRepeating()) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        Calendar alarm = Calendar.getInstance();
        alarm.setTime(notification.getOptions().getTriggerDate());
        int alarmHour = alarm.get(11);
        int alarmMin = alarm.get(12);
        int currentHour = now.get(11);
        int currentMin = now.get(12);
        if (!(currentHour == alarmHour || currentMin == alarmMin)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}

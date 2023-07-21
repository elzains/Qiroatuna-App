package p003de.appplant.cordova.plugin.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.p000v4.app.NotificationCompat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.Notification */
public class Notification {
    static final String PREF_KEY = "LocalNotification";
    private static Class<?> defaultReceiver = TriggerReceiver.class;
    private final NotificationCompat.Builder builder;
    private final Context context;
    private final Options options;
    private Class<?> receiver = defaultReceiver;

    /* renamed from: de.appplant.cordova.plugin.notification.Notification$Type */
    public enum Type {
        ALL,
        SCHEDULED,
        TRIGGERED
    }

    protected Notification(Context context2, Options options2, NotificationCompat.Builder builder2, Class<?> receiver2) {
        this.context = context2;
        this.options = options2;
        this.builder = builder2;
        this.receiver = receiver2 == null ? defaultReceiver : receiver2;
    }

    public Context getContext() {
        return this.context;
    }

    public Options getOptions() {
        return this.options;
    }

    public int getId() {
        return this.options.getId().intValue();
    }

    public boolean isRepeating() {
        return getOptions().getRepeatInterval() > 0;
    }

    public boolean wasInThePast() {
        return new Date().after(this.options.getTriggerDate());
    }

    public boolean isScheduled() {
        return isRepeating() || !wasInThePast();
    }

    public boolean isTriggered() {
        return wasInThePast();
    }

    /* access modifiers changed from: protected */
    public boolean isUpdate(boolean keepFlag) {
        boolean updated = this.options.getDict().optBoolean("updated", false);
        if (!keepFlag) {
            this.options.getDict().remove("updated");
        }
        return updated;
    }

    public Type getType() {
        return isScheduled() ? Type.SCHEDULED : Type.TRIGGERED;
    }

    public void schedule() {
        long triggerTime = this.options.getTriggerTime();
        persist();
        PendingIntent pi = PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, this.receiver).setAction(this.options.getIdStr()).putExtra("NOTIFICATION_OPTIONS", this.options.toString()), 268435456);
        if (isRepeating()) {
            getAlarmMgr().setRepeating(0, triggerTime, this.options.getRepeatInterval(), pi);
        } else {
            getAlarmMgr().set(0, triggerTime, pi);
        }
    }

    public void clear() {
        if (!isRepeating() && wasInThePast()) {
            unpersist();
        }
        if (!isRepeating()) {
            getNotMgr().cancel(getId());
        }
    }

    public void cancel() {
        getAlarmMgr().cancel(PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, this.receiver).setAction(this.options.getIdStr()), 0));
        getNotMgr().cancel(this.options.getId().intValue());
        unpersist();
    }

    public void show() {
        showNotification();
    }

    private void showNotification() {
        int id = getOptions().getId().intValue();
        if (Build.VERSION.SDK_INT <= 15) {
            getNotMgr().notify(id, this.builder.getNotification());
        } else {
            getNotMgr().notify(id, this.builder.build());
        }
    }

    public int getTriggerCountSinceSchedule() {
        long now = System.currentTimeMillis();
        long triggerTime = this.options.getTriggerTime();
        if (!wasInThePast()) {
            return 0;
        }
        if (!isRepeating()) {
            return 1;
        }
        return (int) ((now - triggerTime) / this.options.getRepeatInterval());
    }

    public String toString() {
        JSONObject dict = this.options.getDict();
        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(dict.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json.remove("firstAt");
        json.remove("updated");
        json.remove("soundUri");
        json.remove("iconUri");
        return json.toString();
    }

    private void persist() {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(this.options.getIdStr(), this.options.toString());
        if (Build.VERSION.SDK_INT < 9) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    private void unpersist() {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.remove(this.options.getIdStr());
        if (Build.VERSION.SDK_INT < 9) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    private SharedPreferences getPrefs() {
        return this.context.getSharedPreferences(PREF_KEY, 0);
    }

    private NotificationManager getNotMgr() {
        return (NotificationManager) this.context.getSystemService("notification");
    }

    private AlarmManager getAlarmMgr() {
        return (AlarmManager) this.context.getSystemService("alarm");
    }

    public static void setDefaultTriggerReceiver(Class<?> receiver2) {
        defaultReceiver = receiver2;
    }
}

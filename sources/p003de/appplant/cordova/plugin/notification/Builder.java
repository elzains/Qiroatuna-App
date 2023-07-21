package p003de.appplant.cordova.plugin.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.app.NotificationCompat;
import java.util.Random;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.Builder */
public class Builder {
    private Class<?> clearReceiver = ClearReceiver.class;
    private Class<?> clickActivity = ClickActivity.class;
    private final Context context;
    private final Options options;
    private Class<?> triggerReceiver;

    public Builder(Context context2, JSONObject options2) {
        this.context = context2;
        this.options = new Options(context2).parse(options2);
    }

    public Builder(Options options2) {
        this.context = options2.getContext();
        this.options = options2;
    }

    public Builder setTriggerReceiver(Class<?> receiver) {
        this.triggerReceiver = receiver;
        return this;
    }

    public Builder setClearReceiver(Class<?> receiver) {
        this.clearReceiver = receiver;
        return this;
    }

    public Builder setClickActivity(Class<?> activity) {
        this.clickActivity = activity;
        return this;
    }

    public Notification build() {
        Uri sound = this.options.getSoundUri();
        int smallIcon = this.options.getSmallIcon();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context).setDefaults(0).setContentTitle(this.options.getTitle()).setContentText(this.options.getText()).setNumber(this.options.getBadgeNumber()).setTicker(this.options.getText()).setAutoCancel(this.options.isAutoClear().booleanValue()).setOngoing(this.options.isOngoing().booleanValue()).setColor(this.options.getColor()).setLights(this.options.getLedColor(), 100, 100);
        if (sound != null) {
            builder.setSound(sound);
        }
        if (smallIcon == 0) {
            builder.setSmallIcon(this.options.getIcon());
        } else {
            builder.setSmallIcon(this.options.getSmallIcon());
            builder.setLargeIcon(this.options.getIconBitmap());
        }
        applyDeleteReceiver(builder);
        applyContentReceiver(builder);
        return new Notification(this.context, this.options, builder, this.triggerReceiver);
    }

    private void applyDeleteReceiver(NotificationCompat.Builder builder) {
        if (this.clearReceiver != null) {
            builder.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, this.clearReceiver).setAction(this.options.getIdStr()).putExtra("NOTIFICATION_OPTIONS", this.options.toString()), 134217728));
        }
    }

    private void applyContentReceiver(NotificationCompat.Builder builder) {
        if (this.clickActivity != null) {
            Intent intent = new Intent(this.context, this.clickActivity).putExtra("NOTIFICATION_OPTIONS", this.options.toString()).setFlags(1073741824);
            builder.setContentIntent(PendingIntent.getActivity(this.context, new Random().nextInt(), intent, 134217728));
        }
    }
}

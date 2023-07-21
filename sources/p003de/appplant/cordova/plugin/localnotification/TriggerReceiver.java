package p003de.appplant.cordova.plugin.localnotification;

import p003de.appplant.cordova.plugin.notification.Builder;
import p003de.appplant.cordova.plugin.notification.Notification;

/* renamed from: de.appplant.cordova.plugin.localnotification.TriggerReceiver */
public class TriggerReceiver extends p003de.appplant.cordova.plugin.notification.TriggerReceiver {
    public void onTrigger(Notification notification, boolean updated) {
        super.onTrigger(notification, updated);
        if (!updated) {
            LocalNotification.fireEvent("trigger", notification);
        }
    }

    public Notification buildNotification(Builder builder) {
        return builder.setTriggerReceiver(TriggerReceiver.class).setClickActivity(ClickActivity.class).setClearReceiver(ClearReceiver.class).build();
    }
}

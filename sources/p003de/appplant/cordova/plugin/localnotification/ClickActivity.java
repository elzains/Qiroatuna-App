package p003de.appplant.cordova.plugin.localnotification;

import p003de.appplant.cordova.plugin.notification.Builder;
import p003de.appplant.cordova.plugin.notification.Notification;
import p003de.appplant.cordova.plugin.notification.TriggerReceiver;

/* renamed from: de.appplant.cordova.plugin.localnotification.ClickActivity */
public class ClickActivity extends p003de.appplant.cordova.plugin.notification.ClickActivity {
    public void onClick(Notification notification) {
        LocalNotification.fireEvent("click", notification);
        super.onClick(notification);
        if (!notification.getOptions().isOngoing().booleanValue()) {
            LocalNotification.fireEvent(notification.isRepeating() ? "clear" : "cancel", notification);
        }
    }

    public Notification buildNotification(Builder builder) {
        return builder.setTriggerReceiver(TriggerReceiver.class).build();
    }
}

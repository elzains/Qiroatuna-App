package p003de.appplant.cordova.plugin.notification;

/* renamed from: de.appplant.cordova.plugin.notification.TriggerReceiver */
public class TriggerReceiver extends AbstractTriggerReceiver {
    public void onTrigger(Notification notification, boolean updated) {
        notification.show();
    }

    public Notification buildNotification(Builder builder) {
        return builder.build();
    }
}

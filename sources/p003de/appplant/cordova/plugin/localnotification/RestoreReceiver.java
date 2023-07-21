package p003de.appplant.cordova.plugin.localnotification;

import p003de.appplant.cordova.plugin.notification.AbstractRestoreReceiver;
import p003de.appplant.cordova.plugin.notification.Builder;
import p003de.appplant.cordova.plugin.notification.Notification;

/* renamed from: de.appplant.cordova.plugin.localnotification.RestoreReceiver */
public class RestoreReceiver extends AbstractRestoreReceiver {
    public void onRestore(Notification notification) {
        if (notification.isScheduled()) {
            notification.schedule();
        } else {
            notification.cancel();
        }
    }

    public Notification buildNotification(Builder builder) {
        return builder.setTriggerReceiver(TriggerReceiver.class).setClearReceiver(ClearReceiver.class).setClickActivity(ClickActivity.class).build();
    }
}

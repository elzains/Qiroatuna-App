package p003de.appplant.cordova.plugin.localnotification;

import p003de.appplant.cordova.plugin.notification.Notification;

/* renamed from: de.appplant.cordova.plugin.localnotification.ClearReceiver */
public class ClearReceiver extends p003de.appplant.cordova.plugin.notification.ClearReceiver {
    public void onClear(Notification notification) {
        super.onClear(notification);
        LocalNotification.fireEvent("clear", notification);
    }
}

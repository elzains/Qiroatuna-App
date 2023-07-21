package p003de.appplant.cordova.plugin.notification;

/* renamed from: de.appplant.cordova.plugin.notification.ClickActivity */
public class ClickActivity extends AbstractClickActivity {
    public void onClick(Notification notification) {
        launchApp();
        if (notification.isRepeating()) {
            notification.clear();
        } else {
            notification.cancel();
        }
    }

    public Notification buildNotification(Builder builder) {
        return builder.build();
    }
}

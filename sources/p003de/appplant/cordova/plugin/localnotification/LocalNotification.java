package p003de.appplant.cordova.plugin.localnotification;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p003de.appplant.cordova.plugin.notification.Manager;
import p003de.appplant.cordova.plugin.notification.Notification;

/* renamed from: de.appplant.cordova.plugin.localnotification.LocalNotification */
public class LocalNotification extends CordovaPlugin {
    private static Boolean deviceready = false;
    private static ArrayList<String> eventQueue = new ArrayList<>();
    protected static Boolean isInBackground = true;
    /* access modifiers changed from: private */
    public static CordovaWebView webView = null;

    public void initialize(CordovaInterface cordova2, CordovaWebView webView2) {
        webView = this.webView;
    }

    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        isInBackground = true;
    }

    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        isInBackground = false;
        deviceready();
    }

    public void onDestroy() {
        deviceready = false;
        isInBackground = true;
    }

    public boolean execute(final String action, final JSONArray args, final CallbackContext command) throws JSONException {
        Notification.setDefaultTriggerReceiver(TriggerReceiver.class);
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                if (action.equals("schedule")) {
                    LocalNotification.this.schedule(args);
                    command.success();
                } else if (action.equals("update")) {
                    LocalNotification.this.update(args);
                    command.success();
                } else if (action.equals("cancel")) {
                    LocalNotification.this.cancel(args);
                    command.success();
                } else if (action.equals("cancelAll")) {
                    LocalNotification.this.cancelAll();
                    command.success();
                } else if (action.equals("clear")) {
                    LocalNotification.this.clear(args);
                    command.success();
                } else if (action.equals("clearAll")) {
                    LocalNotification.this.clearAll();
                    command.success();
                } else if (action.equals("isPresent")) {
                    LocalNotification.this.isPresent(args.optInt(0), command);
                } else if (action.equals("isScheduled")) {
                    LocalNotification.this.isScheduled(args.optInt(0), command);
                } else if (action.equals("isTriggered")) {
                    LocalNotification.this.isTriggered(args.optInt(0), command);
                } else if (action.equals("getAllIds")) {
                    LocalNotification.this.getAllIds(command);
                } else if (action.equals("getScheduledIds")) {
                    LocalNotification.this.getScheduledIds(command);
                } else if (action.equals("getTriggeredIds")) {
                    LocalNotification.this.getTriggeredIds(command);
                } else if (action.equals("getSingle")) {
                    LocalNotification.this.getSingle(args, command);
                } else if (action.equals("getSingleScheduled")) {
                    LocalNotification.this.getSingleScheduled(args, command);
                } else if (action.equals("getSingleTriggered")) {
                    LocalNotification.this.getSingleTriggered(args, command);
                } else if (action.equals("getAll")) {
                    LocalNotification.this.getAll(args, command);
                } else if (action.equals("getScheduled")) {
                    LocalNotification.this.getScheduled(args, command);
                } else if (action.equals("getTriggered")) {
                    LocalNotification.this.getTriggered(args, command);
                } else if (action.equals("deviceready")) {
                    LocalNotification.deviceready();
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public void schedule(JSONArray notifications) {
        for (int i = 0; i < notifications.length(); i++) {
            fireEvent("schedule", getNotificationMgr().schedule(notifications.optJSONObject(i), (Class<?>) TriggerReceiver.class));
        }
    }

    /* access modifiers changed from: private */
    public void update(JSONArray updates) {
        for (int i = 0; i < updates.length(); i++) {
            JSONObject update = updates.optJSONObject(i);
            Notification notification = getNotificationMgr().update(update.optInt("id", 0), update, TriggerReceiver.class);
            if (notification != null) {
                fireEvent("update", notification);
            }
        }
    }

    /* access modifiers changed from: private */
    public void cancel(JSONArray ids) {
        for (int i = 0; i < ids.length(); i++) {
            Notification notification = getNotificationMgr().cancel(ids.optInt(i, 0));
            if (notification != null) {
                fireEvent("cancel", notification);
            }
        }
    }

    /* access modifiers changed from: private */
    public void cancelAll() {
        getNotificationMgr().cancelAll();
        fireEvent("cancelall");
    }

    /* access modifiers changed from: private */
    public void clear(JSONArray ids) {
        for (int i = 0; i < ids.length(); i++) {
            Notification notification = getNotificationMgr().clear(ids.optInt(i, 0));
            if (notification != null) {
                fireEvent("clear", notification);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearAll() {
        getNotificationMgr().clearAll();
        fireEvent("clearall");
    }

    /* access modifiers changed from: private */
    public void isPresent(int id, CallbackContext command) {
        command.sendPluginResult(new PluginResult(PluginResult.Status.OK, getNotificationMgr().exist(id)));
    }

    /* access modifiers changed from: private */
    public void isScheduled(int id, CallbackContext command) {
        command.sendPluginResult(new PluginResult(PluginResult.Status.OK, getNotificationMgr().exist(id, Notification.Type.SCHEDULED)));
    }

    /* access modifiers changed from: private */
    public void isTriggered(int id, CallbackContext command) {
        command.sendPluginResult(new PluginResult(PluginResult.Status.OK, getNotificationMgr().exist(id, Notification.Type.TRIGGERED)));
    }

    /* access modifiers changed from: private */
    public void getAllIds(CallbackContext command) {
        command.success(new JSONArray(getNotificationMgr().getIds()));
    }

    /* access modifiers changed from: private */
    public void getScheduledIds(CallbackContext command) {
        command.success(new JSONArray(getNotificationMgr().getIdsByType(Notification.Type.SCHEDULED)));
    }

    /* access modifiers changed from: private */
    public void getTriggeredIds(CallbackContext command) {
        command.success(new JSONArray(getNotificationMgr().getIdsByType(Notification.Type.TRIGGERED)));
    }

    /* access modifiers changed from: private */
    public void getSingle(JSONArray ids, CallbackContext command) {
        getOptions(ids.optString(0), Notification.Type.ALL, command);
    }

    /* access modifiers changed from: private */
    public void getSingleScheduled(JSONArray ids, CallbackContext command) {
        getOptions(ids.optString(0), Notification.Type.SCHEDULED, command);
    }

    /* access modifiers changed from: private */
    public void getSingleTriggered(JSONArray ids, CallbackContext command) {
        getOptions(ids.optString(0), Notification.Type.TRIGGERED, command);
    }

    /* access modifiers changed from: private */
    public void getAll(JSONArray ids, CallbackContext command) {
        getOptions(ids, Notification.Type.ALL, command);
    }

    /* access modifiers changed from: private */
    public void getScheduled(JSONArray ids, CallbackContext command) {
        getOptions(ids, Notification.Type.SCHEDULED, command);
    }

    /* access modifiers changed from: private */
    public void getTriggered(JSONArray ids, CallbackContext command) {
        getOptions(ids, Notification.Type.TRIGGERED, command);
    }

    private void getOptions(String id, Notification.Type type, CallbackContext command) {
        PluginResult result;
        List<JSONObject> options = getNotificationMgr().getOptionsBy(type, toList(new JSONArray().put(id)));
        if (options.isEmpty()) {
            result = new PluginResult(PluginResult.Status.NO_RESULT);
        } else {
            result = new PluginResult(PluginResult.Status.OK, options.get(0));
        }
        command.sendPluginResult(result);
    }

    private void getOptions(JSONArray ids, Notification.Type type, CallbackContext command) {
        List<JSONObject> options;
        if (ids.length() == 0) {
            options = getNotificationMgr().getOptionsByType(type);
        } else {
            options = getNotificationMgr().getOptionsBy(type, toList(ids));
        }
        command.success(new JSONArray(options));
    }

    /* access modifiers changed from: private */
    public static synchronized void deviceready() {
        synchronized (LocalNotification.class) {
            isInBackground = false;
            deviceready = true;
            Iterator<String> it = eventQueue.iterator();
            while (it.hasNext()) {
                sendJavascript(it.next());
            }
            eventQueue.clear();
        }
    }

    private void fireEvent(String event) {
        fireEvent(event, (Notification) null);
    }

    static void fireEvent(String event, Notification notification) {
        String params = "\"" + getApplicationState() + "\"";
        if (notification != null) {
            params = notification.toString() + "," + params;
        }
        sendJavascript("cordova.plugins.notification.local.core.fireEvent(\"" + event + "\"," + params + ")");
    }

    private static synchronized void sendJavascript(final String js) {
        synchronized (LocalNotification.class) {
            if (!deviceready.booleanValue()) {
                eventQueue.add(js);
            } else {
                Runnable jsLoader = new Runnable() {
                    public void run() {
                        LocalNotification.webView.loadUrl("javascript:" + js);
                    }
                };
                try {
                    webView.getClass().getMethod("post", new Class[]{Runnable.class}).invoke(webView, new Object[]{jsLoader});
                } catch (Exception e) {
                    ((Activity) webView.getContext()).runOnUiThread(jsLoader);
                }
            }
        }
        return;
    }

    private List<Integer> toList(JSONArray ary) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < ary.length(); i++) {
            list.add(Integer.valueOf(ary.optInt(i)));
        }
        return list;
    }

    static String getApplicationState() {
        return isInBackground.booleanValue() ? "background" : "foreground";
    }

    private Manager getNotificationMgr() {
        return Manager.getInstance(this.f21cordova.getActivity());
    }
}

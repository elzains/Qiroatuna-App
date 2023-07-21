package name.ratson.cordova.admob;

import org.json.JSONObject;

public abstract class AbstractExecutor {
    protected AdMob plugin;

    public abstract void destroy();

    public abstract String getAdType();

    public AbstractExecutor(AdMob plugin2) {
        this.plugin = plugin2;
    }

    public static String getErrorReason(int errorCode) {
        switch (errorCode) {
            case 0:
                return "Internal error";
            case 1:
                return "Invalid request";
            case 2:
                return "Network Error";
            case 3:
                return "No fill";
            default:
                return "";
        }
    }

    public void fireAdEvent(String eventName) {
        loadJS(new CordovaEventBuilder(eventName).build());
    }

    public void fireAdEvent(String eventName, JSONObject data) {
        loadJS(new CordovaEventBuilder(eventName).withData(data).build());
    }

    private void loadJS(String js) {
        this.plugin.webView.loadUrl(js);
    }
}

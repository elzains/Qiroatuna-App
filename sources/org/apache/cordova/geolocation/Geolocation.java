package org.apache.cordova.geolocation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class Geolocation extends CordovaPlugin {
    String TAG = "GeolocationPlugin";
    CallbackContext context;
    String[] permissions = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.context = callbackContext;
        if (!action.equals("getPermission")) {
            return false;
        }
        if (hasPermisssion()) {
            this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        }
        PermissionHelper.requestPermissions(this, 0, this.permissions);
        return true;
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions2, int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == -1) {
                LOG.m21d(this.TAG, "Permission Denied!");
                this.context.sendPluginResult(new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION));
                return;
            }
        }
        this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    public boolean hasPermisssion() {
        for (String p : this.permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(int requestCode) {
        PermissionHelper.requestPermissions(this, requestCode, this.permissions);
    }
}

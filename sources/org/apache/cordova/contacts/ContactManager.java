package org.apache.cordova.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactManager extends CordovaPlugin {
    private static final int CONTACT_PICKER_RESULT = 1000;
    public static final int INVALID_ARGUMENT_ERROR = 1;
    public static final int IO_ERROR = 4;
    private static final String LOG_TAG = "Contact Query";
    public static final int NOT_SUPPORTED_ERROR = 5;
    public static final int OPERATION_CANCELLED_ERROR = 6;
    public static final int PENDING_OPERATION_ERROR = 3;
    public static final int PERMISSION_DENIED_ERROR = 20;
    public static final int PICK_REQ_CODE = 3;
    public static final String READ = "android.permission.READ_CONTACTS";
    public static final int REMOVE_REQ_CODE = 2;
    public static final int SAVE_REQ_CODE = 1;
    public static final int SEARCH_REQ_CODE = 0;
    public static final int TIMEOUT_ERROR = 2;
    public static final int UNKNOWN_ERROR = 0;
    public static final String WRITE = "android.permission.WRITE_CONTACTS";
    public static String[] permissions;
    /* access modifiers changed from: private */
    public CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public ContactAccessor contactAccessor;
    private JSONArray executeArgs;

    /* access modifiers changed from: protected */
    public void getReadPermission(int requestCode) {
        PermissionHelper.requestPermission(this, requestCode, READ);
    }

    /* access modifiers changed from: protected */
    public void getWritePermission(int requestCode) {
        PermissionHelper.requestPermission(this, requestCode, WRITE);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        this.executeArgs = args;
        if (Build.VERSION.RELEASE.startsWith("1.")) {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 5));
            return true;
        }
        if (this.contactAccessor == null) {
            this.contactAccessor = new ContactAccessorSdk5(this.f21cordova);
        }
        if (action.equals("search")) {
            if (PermissionHelper.hasPermission(this, READ)) {
                search(this.executeArgs);
                return true;
            }
            getReadPermission(0);
            return true;
        } else if (action.equals("save")) {
            if (PermissionHelper.hasPermission(this, WRITE)) {
                save(this.executeArgs);
                return true;
            }
            getWritePermission(1);
            return true;
        } else if (action.equals("remove")) {
            if (PermissionHelper.hasPermission(this, WRITE)) {
                remove(this.executeArgs);
                return true;
            }
            getWritePermission(2);
            return true;
        } else if (!action.equals("pickContact")) {
            return false;
        } else {
            if (PermissionHelper.hasPermission(this, READ)) {
                pickContactAsync();
                return true;
            }
            getReadPermission(3);
            return true;
        }
    }

    private void remove(JSONArray args) throws JSONException {
        final String contactId = args.getString(0);
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                if (ContactManager.this.contactAccessor.remove(contactId)) {
                    ContactManager.this.callbackContext.success();
                } else {
                    ContactManager.this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 0));
                }
            }
        });
    }

    private void save(JSONArray args) throws JSONException {
        final JSONObject contact = args.getJSONObject(0);
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                JSONObject res = null;
                String id = ContactManager.this.contactAccessor.save(contact);
                if (id != null) {
                    try {
                        res = ContactManager.this.contactAccessor.getContactById(id);
                    } catch (JSONException e) {
                        Log.e(ContactManager.LOG_TAG, "JSON fail.", e);
                    }
                }
                if (res != null) {
                    ContactManager.this.callbackContext.success(res);
                } else {
                    ContactManager.this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 0));
                }
            }
        });
    }

    private void search(JSONArray args) throws JSONException {
        final JSONArray filter = args.getJSONArray(0);
        final JSONObject options = args.get(1) == null ? null : args.getJSONObject(1);
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                ContactManager.this.callbackContext.success(ContactManager.this.contactAccessor.search(filter, options));
            }
        });
    }

    private void pickContactAsync() {
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                this.f21cordova.startActivityForResult(this, new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 1000);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode != 1000) {
            return;
        }
        if (resultCode == -1) {
            String contactId = intent.getData().getLastPathSegment();
            Cursor c = this.f21cordova.getActivity().getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"_id"}, "contact_id = " + contactId, (String[]) null, (String) null);
            if (!c.moveToFirst()) {
                this.callbackContext.error("Error occured while retrieving contact raw id");
                return;
            }
            String id = c.getString(c.getColumnIndex("_id"));
            c.close();
            try {
                this.callbackContext.success(this.contactAccessor.getContactById(id));
            } catch (JSONException e) {
                Log.e(LOG_TAG, "JSON fail.", e);
            }
        } else {
            if (resultCode == 0) {
                this.callbackContext.error(6);
                return;
            }
            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 0));
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions2, int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == -1) {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
                return;
            }
        }
        switch (requestCode) {
            case 0:
                search(this.executeArgs);
                return;
            case 1:
                save(this.executeArgs);
                return;
            case 2:
                remove(this.executeArgs);
                return;
            case 3:
                pickContactAsync();
                return;
            default:
                return;
        }
    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext2) {
        this.callbackContext = callbackContext2;
        this.contactAccessor = new ContactAccessorSdk5(this.f21cordova);
    }
}

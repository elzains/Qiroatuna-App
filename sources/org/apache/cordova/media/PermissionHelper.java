package org.apache.cordova.media;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class PermissionHelper {
    private static final String LOG_TAG = "CordovaPermissionHelper";

    public static void requestPermission(CordovaPlugin plugin, int requestCode, String permission) {
        requestPermissions(plugin, requestCode, new String[]{permission});
    }

    public static void requestPermissions(CordovaPlugin plugin, int requestCode, String[] permissions) {
        Class<CordovaInterface> cls = CordovaInterface.class;
        try {
            cls.getDeclaredMethod("requestPermissions", new Class[]{CordovaPlugin.class, Integer.TYPE, String[].class}).invoke(plugin.f21cordova, new Object[]{plugin, Integer.valueOf(requestCode), permissions});
        } catch (NoSuchMethodException e) {
            LOG.m21d(LOG_TAG, "No need to request permissions " + Arrays.toString(permissions));
            deliverPermissionResult(plugin, requestCode, permissions);
        } catch (IllegalAccessException illegalAccessException) {
            LOG.m25e(LOG_TAG, "IllegalAccessException when requesting permissions " + Arrays.toString(permissions), (Throwable) illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
            LOG.m25e(LOG_TAG, "invocationTargetException when requesting permissions " + Arrays.toString(permissions), (Throwable) invocationTargetException);
        }
    }

    public static boolean hasPermission(CordovaPlugin plugin, String permission) {
        try {
            return ((Boolean) CordovaInterface.class.getDeclaredMethod("hasPermission", new Class[]{String.class}).invoke(plugin.f21cordova, new Object[]{permission})).booleanValue();
        } catch (NoSuchMethodException e) {
            LOG.m21d(LOG_TAG, "No need to check for permission " + permission);
            return true;
        } catch (IllegalAccessException illegalAccessException) {
            LOG.m25e(LOG_TAG, "IllegalAccessException when checking permission " + permission, (Throwable) illegalAccessException);
            return false;
        } catch (InvocationTargetException invocationTargetException) {
            LOG.m25e(LOG_TAG, "invocationTargetException when checking permission " + permission, (Throwable) invocationTargetException);
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void deliverPermissionResult(org.apache.cordova.CordovaPlugin r10, int r11, java.lang.String[] r12) {
        /*
            r6 = 0
            int r5 = r12.length
            int[] r4 = new int[r5]
            java.util.Arrays.fill(r4, r6)
            java.lang.Class<org.apache.cordova.CordovaPlugin> r5 = org.apache.cordova.CordovaPlugin.class
            java.lang.String r6 = "onRequestPermissionResult"
            r7 = 3
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r8 = 0
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r7[r8] = r9     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r8 = 1
            java.lang.Class<java.lang.String[]> r9 = java.lang.String[].class
            r7[r8] = r9     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r8 = 2
            java.lang.Class<int[]> r9 = int[].class
            r7[r8] = r9     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            java.lang.reflect.Method r3 = r5.getDeclaredMethod(r6, r7)     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r6 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r6 = 1
            r5[r6] = r12     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r6 = 2
            r5[r6] = r4     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
            r3.invoke(r10, r5)     // Catch:{ NoSuchMethodException -> 0x0035, IllegalAccessException -> 0x003e, InvocationTargetException -> 0x0047 }
        L_0x0034:
            return
        L_0x0035:
            r2 = move-exception
            java.lang.String r5 = "CordovaPermissionHelper"
            java.lang.String r6 = "NoSuchMethodException when delivering permissions results"
            org.apache.cordova.LOG.m25e((java.lang.String) r5, (java.lang.String) r6, (java.lang.Throwable) r2)
            goto L_0x0034
        L_0x003e:
            r0 = move-exception
            java.lang.String r5 = "CordovaPermissionHelper"
            java.lang.String r6 = "IllegalAccessException when delivering permissions results"
            org.apache.cordova.LOG.m25e((java.lang.String) r5, (java.lang.String) r6, (java.lang.Throwable) r0)
            goto L_0x0034
        L_0x0047:
            r1 = move-exception
            java.lang.String r5 = "CordovaPermissionHelper"
            java.lang.String r6 = "InvocationTargetException when delivering permissions results"
            org.apache.cordova.LOG.m25e((java.lang.String) r5, (java.lang.String) r6, (java.lang.Throwable) r1)
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.media.PermissionHelper.deliverPermissionResult(org.apache.cordova.CordovaPlugin, int, java.lang.String[]):void");
    }
}

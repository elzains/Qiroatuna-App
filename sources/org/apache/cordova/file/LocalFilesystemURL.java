package org.apache.cordova.file;

import android.net.Uri;

public class LocalFilesystemURL {
    public static final String FILESYSTEM_PROTOCOL = "cdvfile";
    public final String fsName;
    public final boolean isDirectory;
    public final String path;
    public final Uri uri;

    private LocalFilesystemURL(Uri uri2, String fsName2, String fsPath, boolean isDirectory2) {
        this.uri = uri2;
        this.fsName = fsName2;
        this.path = fsPath;
        this.isDirectory = isDirectory2;
    }

    public static LocalFilesystemURL parse(Uri uri2) {
        int firstSlashIdx;
        boolean isDirectory2 = true;
        if (!FILESYSTEM_PROTOCOL.equals(uri2.getScheme())) {
            return null;
        }
        String path2 = uri2.getPath();
        if (path2.length() < 1 || (firstSlashIdx = path2.indexOf(47, 1)) < 0) {
            return null;
        }
        String fsName2 = path2.substring(1, firstSlashIdx);
        String path3 = path2.substring(firstSlashIdx);
        if (path3.charAt(path3.length() - 1) != '/') {
            isDirectory2 = false;
        }
        return new LocalFilesystemURL(uri2, fsName2, path3, isDirectory2);
    }

    public static LocalFilesystemURL parse(String uri2) {
        return parse(Uri.parse(uri2));
    }

    public String toString() {
        return this.uri.toString();
    }
}

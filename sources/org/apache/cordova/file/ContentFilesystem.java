package org.apache.cordova.file;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentFilesystem extends Filesystem {
    private final Context context;

    public ContentFilesystem(Context context2, CordovaResourceApi resourceApi) {
        super(Uri.parse("content://"), "content", resourceApi);
        this.context = context2;
    }

    public Uri toNativeUri(LocalFilesystemURL inputURL) {
        String authorityAndPath = inputURL.uri.getEncodedPath().substring(this.f28name.length() + 2);
        if (authorityAndPath.length() < 2) {
            return null;
        }
        String ret = "content://" + authorityAndPath;
        String query = inputURL.uri.getEncodedQuery();
        if (query != null) {
            ret = ret + '?' + query;
        }
        String frag = inputURL.uri.getEncodedFragment();
        if (frag != null) {
            ret = ret + '#' + frag;
        }
        return Uri.parse(ret);
    }

    public LocalFilesystemURL toLocalUri(Uri inputURL) {
        if (!"content".equals(inputURL.getScheme())) {
            return null;
        }
        String subPath = inputURL.getEncodedPath();
        if (subPath.length() > 0) {
            subPath = subPath.substring(1);
        }
        Uri.Builder b = new Uri.Builder().scheme(LocalFilesystemURL.FILESYSTEM_PROTOCOL).authority("localhost").path(this.f28name).appendPath(inputURL.getAuthority());
        if (subPath.length() > 0) {
            b.appendEncodedPath(subPath);
        }
        return LocalFilesystemURL.parse(b.encodedQuery(inputURL.getEncodedQuery()).encodedFragment(inputURL.getEncodedFragment()).build());
    }

    public JSONObject getFileForLocalURL(LocalFilesystemURL inputURL, String fileName, JSONObject options, boolean directory) throws IOException, TypeMismatchException, JSONException {
        throw new UnsupportedOperationException("getFile() not supported for content:. Use resolveLocalFileSystemURL instead.");
    }

    public boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        Uri contentUri = toNativeUri(inputURL);
        try {
            this.context.getContentResolver().delete(contentUri, (String) null, (String[]) null);
            return true;
        } catch (UnsupportedOperationException t) {
            NoModificationAllowedException nmae = new NoModificationAllowedException("Deleting not supported for content uri: " + contentUri);
            nmae.initCause(t);
            throw nmae;
        }
    }

    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Cannot remove content url");
    }

    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        throw new UnsupportedOperationException("readEntriesAtLocalURL() not supported for content:. Use resolveLocalFileSystemURL instead.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getFileMetadataForLocalURL(org.apache.cordova.file.LocalFilesystemURL r18) throws java.io.FileNotFoundException {
        /*
            r17 = this;
            r12 = -1
            r6 = 0
            android.net.Uri r10 = r17.toNativeUri(r18)
            r0 = r17
            org.apache.cordova.CordovaResourceApi r15 = r0.resourceApi
            java.lang.String r8 = r15.getMimeType(r10)
            r0 = r17
            android.database.Cursor r2 = r0.openCursorForURL(r10)
            if (r2 == 0) goto L_0x006a
            boolean r15 = r2.moveToFirst()     // Catch:{ IOException -> 0x0075 }
            if (r15 == 0) goto L_0x006a
            r0 = r17
            java.lang.Long r14 = r0.resourceSizeForCursor(r2)     // Catch:{ IOException -> 0x0075 }
            if (r14 == 0) goto L_0x002a
            long r12 = r14.longValue()     // Catch:{ IOException -> 0x0075 }
        L_0x002a:
            r0 = r17
            java.lang.Long r9 = r0.lastModifiedDateForCursor(r2)     // Catch:{ IOException -> 0x0075 }
            if (r9 == 0) goto L_0x0036
            long r6 = r9.longValue()     // Catch:{ IOException -> 0x0075 }
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.close()
        L_0x003b:
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            java.lang.String r15 = "size"
            r5.put(r15, r12)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r15 = "type"
            r5.put(r15, r8)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r15 = "name"
            r0 = r17
            java.lang.String r0 = r0.f28name     // Catch:{ JSONException -> 0x0086 }
            r16 = r0
            r0 = r16
            r5.put(r15, r0)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r15 = "fullPath"
            r0 = r18
            java.lang.String r0 = r0.path     // Catch:{ JSONException -> 0x0086 }
            r16 = r0
            r0 = r16
            r5.put(r15, r0)     // Catch:{ JSONException -> 0x0086 }
            java.lang.String r15 = "lastModifiedDate"
            r5.put(r15, r6)     // Catch:{ JSONException -> 0x0086 }
        L_0x0069:
            return r5
        L_0x006a:
            r0 = r17
            org.apache.cordova.CordovaResourceApi r15 = r0.resourceApi     // Catch:{ IOException -> 0x0075 }
            org.apache.cordova.CordovaResourceApi$OpenForReadResult r11 = r15.openForRead(r10)     // Catch:{ IOException -> 0x0075 }
            long r12 = r11.length     // Catch:{ IOException -> 0x0075 }
            goto L_0x0036
        L_0x0075:
            r3 = move-exception
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException     // Catch:{ all -> 0x007f }
            r4.<init>()     // Catch:{ all -> 0x007f }
            r4.initCause(r3)     // Catch:{ all -> 0x007f }
            throw r4     // Catch:{ all -> 0x007f }
        L_0x007f:
            r15 = move-exception
            if (r2 == 0) goto L_0x0085
            r2.close()
        L_0x0085:
            throw r15
        L_0x0086:
            r3 = move-exception
            r5 = 0
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.ContentFilesystem.getFileMetadataForLocalURL(org.apache.cordova.file.LocalFilesystemURL):org.json.JSONObject");
    }

    public long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Couldn't write to file given its content URI");
    }

    public long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Couldn't truncate file given its content URI");
    }

    /* access modifiers changed from: protected */
    public Cursor openCursorForURL(Uri nativeUri) {
        try {
            return this.context.getContentResolver().query(nativeUri, (String[]) null, (String) null, (String[]) null, (String) null);
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    private Long resourceSizeForCursor(Cursor cursor) {
        String sizeStr;
        int columnIndex = cursor.getColumnIndex("_size");
        if (columnIndex == -1 || (sizeStr = cursor.getString(columnIndex)) == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(sizeStr));
    }

    /* access modifiers changed from: protected */
    public Long lastModifiedDateForCursor(Cursor cursor) {
        String dateStr;
        int columnIndex = cursor.getColumnIndex("date_modified");
        if (columnIndex == -1) {
            columnIndex = cursor.getColumnIndex("last_modified");
        }
        if (columnIndex == -1 || (dateStr = cursor.getString(columnIndex)) == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(dateStr));
    }

    public String filesystemPathForURL(LocalFilesystemURL url) {
        File f = this.resourceApi.mapUriToFile(toNativeUri(url));
        if (f == null) {
            return null;
        }
        return f.getAbsolutePath();
    }

    public LocalFilesystemURL URLforFilesystemPath(String path) {
        return null;
    }

    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        return true;
    }
}

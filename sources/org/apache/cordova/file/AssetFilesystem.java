package org.apache.cordova.file;

import android.content.res.AssetManager;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.LOG;
import org.apache.cordova.globalization.Globalization;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetFilesystem extends Filesystem {
    private static final String LOG_TAG = "AssetFilesystem";
    private static Map<String, Long> lengthCache;
    private static Map<String, String[]> listCache;
    private static boolean listCacheFromFile;
    private static Object listCacheLock = new Object();
    private final AssetManager assetManager;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[Catch:{ IOException -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0054 A[SYNTHETIC, Splitter:B:27:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0069 A[SYNTHETIC, Splitter:B:38:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x007b A[SYNTHETIC, Splitter:B:46:0x007b] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x002e=Splitter:B:14:0x002e, B:48:0x007e=Splitter:B:48:0x007e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void lazyInitCaches() {
        /*
            r7 = this;
            java.lang.Object r4 = listCacheLock
            monitor-enter(r4)
            java.util.Map<java.lang.String, java.lang.String[]> r3 = listCache     // Catch:{ all -> 0x0063 }
            if (r3 != 0) goto L_0x0040
            r1 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ ClassNotFoundException -> 0x004e, IOException -> 0x0066 }
            android.content.res.AssetManager r3 = r7.assetManager     // Catch:{ ClassNotFoundException -> 0x004e, IOException -> 0x0066 }
            java.lang.String r5 = "cdvasset.manifest"
            java.io.InputStream r3 = r3.open(r5)     // Catch:{ ClassNotFoundException -> 0x004e, IOException -> 0x0066 }
            r2.<init>(r3)     // Catch:{ ClassNotFoundException -> 0x004e, IOException -> 0x0066 }
            java.lang.Object r3 = r2.readObject()     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            listCache = r3     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            java.lang.Object r3 = r2.readObject()     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            lengthCache = r3     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            r3 = 1
            listCacheFromFile = r3     // Catch:{ ClassNotFoundException -> 0x0090, IOException -> 0x008d, all -> 0x008a }
            if (r2 == 0) goto L_0x0093
            r2.close()     // Catch:{ IOException -> 0x0042 }
            r1 = r2
        L_0x002e:
            java.util.Map<java.lang.String, java.lang.String[]> r3 = listCache     // Catch:{ all -> 0x0063 }
            if (r3 != 0) goto L_0x0040
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r5 = "Asset manifest not found. Recursive copies and directory listing will be slow."
            org.apache.cordova.LOG.m33w((java.lang.String) r3, (java.lang.String) r5)     // Catch:{ all -> 0x0063 }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x0063 }
            r3.<init>()     // Catch:{ all -> 0x0063 }
            listCache = r3     // Catch:{ all -> 0x0063 }
        L_0x0040:
            monitor-exit(r4)     // Catch:{ all -> 0x0063 }
            return
        L_0x0042:
            r0 = move-exception
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r5 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0063 }
            org.apache.cordova.LOG.m21d(r3, r5)     // Catch:{ all -> 0x0063 }
            r1 = r2
            goto L_0x002e
        L_0x004e:
            r0 = move-exception
        L_0x004f:
            r0.printStackTrace()     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x002e
        L_0x0058:
            r0 = move-exception
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r5 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0063 }
            org.apache.cordova.LOG.m21d(r3, r5)     // Catch:{ all -> 0x0063 }
            goto L_0x002e
        L_0x0063:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0063 }
            throw r3
        L_0x0066:
            r3 = move-exception
        L_0x0067:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x002e
        L_0x006d:
            r0 = move-exception
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r5 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0063 }
            org.apache.cordova.LOG.m21d(r3, r5)     // Catch:{ all -> 0x0063 }
            goto L_0x002e
        L_0x0078:
            r3 = move-exception
        L_0x0079:
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ IOException -> 0x007f }
        L_0x007e:
            throw r3     // Catch:{ all -> 0x0063 }
        L_0x007f:
            r0 = move-exception
            java.lang.String r5 = "AssetFilesystem"
            java.lang.String r6 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0063 }
            org.apache.cordova.LOG.m21d(r5, r6)     // Catch:{ all -> 0x0063 }
            goto L_0x007e
        L_0x008a:
            r3 = move-exception
            r1 = r2
            goto L_0x0079
        L_0x008d:
            r3 = move-exception
            r1 = r2
            goto L_0x0067
        L_0x0090:
            r0 = move-exception
            r1 = r2
            goto L_0x004f
        L_0x0093:
            r1 = r2
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.AssetFilesystem.lazyInitCaches():void");
    }

    private String[] listAssets(String assetPath) throws IOException {
        if (assetPath.startsWith("/")) {
            assetPath = assetPath.substring(1);
        }
        if (assetPath.endsWith("/")) {
            assetPath = assetPath.substring(0, assetPath.length() - 1);
        }
        lazyInitCaches();
        String[] ret = listCache.get(assetPath);
        if (ret != null) {
            return ret;
        }
        if (listCacheFromFile) {
            return new String[0];
        }
        String[] ret2 = this.assetManager.list(assetPath);
        listCache.put(assetPath, ret2);
        return ret2;
    }

    private long getAssetSize(String assetPath) throws FileNotFoundException {
        if (assetPath.startsWith("/")) {
            assetPath = assetPath.substring(1);
        }
        lazyInitCaches();
        if (lengthCache != null) {
            Long ret = lengthCache.get(assetPath);
            if (ret != null) {
                return ret.longValue();
            }
            throw new FileNotFoundException("Asset not found: " + assetPath);
        }
        try {
            CordovaResourceApi.OpenForReadResult offr = this.resourceApi.openForRead(nativeUriForFullPath(assetPath));
            long length = offr.length;
            if (length < 0) {
                length = (long) offr.inputStream.available();
            }
            if (offr == null) {
                return length;
            }
            try {
                offr.inputStream.close();
                return length;
            } catch (IOException e) {
                LOG.m21d(LOG_TAG, e.getLocalizedMessage());
                return length;
            }
        } catch (IOException e2) {
            FileNotFoundException fnfe = new FileNotFoundException("File not found: " + assetPath);
            fnfe.initCause(e2);
            throw fnfe;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    null.inputStream.close();
                } catch (IOException e3) {
                    LOG.m21d(LOG_TAG, e3.getLocalizedMessage());
                }
            }
            throw th;
        }
    }

    public AssetFilesystem(AssetManager assetManager2, CordovaResourceApi resourceApi) {
        super(Uri.parse("file:///android_asset/"), "assets", resourceApi);
        this.assetManager = assetManager2;
    }

    public Uri toNativeUri(LocalFilesystemURL inputURL) {
        return nativeUriForFullPath(inputURL.path);
    }

    public LocalFilesystemURL toLocalUri(Uri inputURL) {
        if (!"file".equals(inputURL.getScheme())) {
            return null;
        }
        Uri resolvedUri = Uri.fromFile(new File(inputURL.getPath()));
        String rootUriNoTrailingSlash = this.rootUri.getEncodedPath();
        String rootUriNoTrailingSlash2 = rootUriNoTrailingSlash.substring(0, rootUriNoTrailingSlash.length() - 1);
        if (!resolvedUri.getEncodedPath().startsWith(rootUriNoTrailingSlash2)) {
            return null;
        }
        String subPath = resolvedUri.getEncodedPath().substring(rootUriNoTrailingSlash2.length());
        if (!subPath.isEmpty()) {
            subPath = subPath.substring(1);
        }
        Uri.Builder b = new Uri.Builder().scheme(LocalFilesystemURL.FILESYSTEM_PROTOCOL).authority("localhost").path(this.f28name);
        if (!subPath.isEmpty()) {
            b.appendEncodedPath(subPath);
        }
        if (isDirectory(subPath) || inputURL.getPath().endsWith("/")) {
            b.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(b.build());
    }

    private boolean isDirectory(String assetPath) {
        try {
            return listAssets(assetPath).length != 0;
        } catch (IOException e) {
            return false;
        }
    }

    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        String pathNoSlashes = inputURL.path.substring(1);
        if (pathNoSlashes.endsWith("/")) {
            pathNoSlashes = pathNoSlashes.substring(0, pathNoSlashes.length() - 1);
        }
        try {
            String[] files = listAssets(pathNoSlashes);
            LocalFilesystemURL[] entries = new LocalFilesystemURL[files.length];
            for (int i = 0; i < files.length; i++) {
                entries[i] = localUrlforFullPath(new File(inputURL.path, files[i]).getPath());
            }
            return entries;
        } catch (IOException e) {
            FileNotFoundException fnfe = new FileNotFoundException();
            fnfe.initCause(e);
            throw fnfe;
        }
    }

    public JSONObject getFileForLocalURL(LocalFilesystemURL inputURL, String path, JSONObject options, boolean directory) throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException {
        LocalFilesystemURL requestedURL;
        if (options == null || !options.optBoolean("create")) {
            if (directory && !path.endsWith("/")) {
                path = path + "/";
            }
            if (path.startsWith("/")) {
                requestedURL = localUrlforFullPath(normalizePath(path));
            } else {
                requestedURL = localUrlforFullPath(normalizePath(inputURL.path + "/" + path));
            }
            getFileMetadataForLocalURL(requestedURL);
            boolean isDir = isDirectory(requestedURL.path);
            if (directory && !isDir) {
                throw new TypeMismatchException("path doesn't exist or is file");
            } else if (directory || !isDir) {
                return makeEntryForURL(requestedURL);
            } else {
                throw new TypeMismatchException("path doesn't exist or is directory");
            }
        } else {
            throw new UnsupportedOperationException("Assets are read-only");
        }
    }

    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL inputURL) throws FileNotFoundException {
        JSONObject metadata = new JSONObject();
        try {
            metadata.put("size", inputURL.isDirectory ? 0 : getAssetSize(inputURL.path));
            metadata.put(Globalization.TYPE, inputURL.isDirectory ? "text/directory" : this.resourceApi.getMimeType(toNativeUri(inputURL)));
            metadata.put("name", new File(inputURL.path).getName());
            metadata.put("fullPath", inputURL.path);
            metadata.put("lastModifiedDate", 0);
            return metadata;
        } catch (JSONException e) {
            return null;
        }
    }

    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws NoModificationAllowedException, IOException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws IOException, NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public String filesystemPathForURL(LocalFilesystemURL url) {
        return new File(this.rootUri.getPath(), url.path).toString();
    }

    /* access modifiers changed from: package-private */
    public LocalFilesystemURL URLforFilesystemPath(String path) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws InvalidModificationException, NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }
}

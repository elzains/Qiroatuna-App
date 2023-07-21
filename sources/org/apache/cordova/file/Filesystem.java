package org.apache.cordova.file;

import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Filesystem {

    /* renamed from: name  reason: collision with root package name */
    public final String f28name;
    protected final CordovaResourceApi resourceApi;
    private JSONObject rootEntry;
    protected final Uri rootUri;

    public interface ReadFileCallback {
        void handleData(InputStream inputStream, String str) throws IOException;
    }

    /* access modifiers changed from: package-private */
    public abstract LocalFilesystemURL URLforFilesystemPath(String str);

    /* access modifiers changed from: package-private */
    public abstract boolean canRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL);

    /* access modifiers changed from: package-private */
    public abstract String filesystemPathForURL(LocalFilesystemURL localFilesystemURL);

    /* access modifiers changed from: package-private */
    public abstract JSONObject getFileForLocalURL(LocalFilesystemURL localFilesystemURL, String str, JSONObject jSONObject, boolean z) throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException;

    /* access modifiers changed from: package-private */
    public abstract JSONObject getFileMetadataForLocalURL(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException;

    /* access modifiers changed from: package-private */
    public abstract LocalFilesystemURL[] listChildren(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException;

    /* access modifiers changed from: package-private */
    public abstract boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws FileExistsException, NoModificationAllowedException;

    /* access modifiers changed from: package-private */
    public abstract boolean removeFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws InvalidModificationException, NoModificationAllowedException;

    public abstract LocalFilesystemURL toLocalUri(Uri uri);

    public abstract Uri toNativeUri(LocalFilesystemURL localFilesystemURL);

    /* access modifiers changed from: package-private */
    public abstract long truncateFileAtURL(LocalFilesystemURL localFilesystemURL, long j) throws IOException, NoModificationAllowedException;

    /* access modifiers changed from: package-private */
    public abstract long writeToFileAtURL(LocalFilesystemURL localFilesystemURL, String str, int i, boolean z) throws NoModificationAllowedException, IOException;

    public Filesystem(Uri rootUri2, String name2, CordovaResourceApi resourceApi2) {
        this.rootUri = rootUri2;
        this.f28name = name2;
        this.resourceApi = resourceApi2;
    }

    public static JSONObject makeEntryForURL(LocalFilesystemURL inputURL, Uri nativeURL) {
        int end;
        boolean z;
        int i = 0;
        try {
            String path = inputURL.path;
            if (path.endsWith("/")) {
                end = 1;
            } else {
                end = 0;
            }
            String[] parts = path.substring(0, path.length() - end).split("/+");
            String fileName = parts[parts.length - 1];
            JSONObject entry = new JSONObject();
            if (!inputURL.isDirectory) {
                z = true;
            } else {
                z = false;
            }
            entry.put("isFile", z);
            entry.put("isDirectory", inputURL.isDirectory);
            entry.put("name", fileName);
            entry.put("fullPath", path);
            entry.put("filesystemName", inputURL.fsName);
            if (!"temporary".equals(inputURL.fsName)) {
                i = 1;
            }
            entry.put("filesystem", i);
            String nativeUrlStr = nativeURL.toString();
            if (inputURL.isDirectory && !nativeUrlStr.endsWith("/")) {
                nativeUrlStr = nativeUrlStr + "/";
            }
            entry.put("nativeURL", nativeUrlStr);
            return entry;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public JSONObject makeEntryForURL(LocalFilesystemURL inputURL) {
        Uri nativeUri = toNativeUri(inputURL);
        if (nativeUri == null) {
            return null;
        }
        return makeEntryForURL(inputURL, nativeUri);
    }

    public JSONObject makeEntryForNativeUri(Uri nativeUri) {
        LocalFilesystemURL inputUrl = toLocalUri(nativeUri);
        if (inputUrl == null) {
            return null;
        }
        return makeEntryForURL(inputUrl, nativeUri);
    }

    public JSONObject getEntryForLocalURL(LocalFilesystemURL inputURL) throws IOException {
        return makeEntryForURL(inputURL);
    }

    public JSONObject makeEntryForFile(File file) {
        return makeEntryForNativeUri(Uri.fromFile(file));
    }

    public final JSONArray readEntriesAtLocalURL(LocalFilesystemURL inputURL) throws FileNotFoundException {
        LocalFilesystemURL[] children = listChildren(inputURL);
        JSONArray entries = new JSONArray();
        if (children != null) {
            for (LocalFilesystemURL url : children) {
                entries.put(makeEntryForURL(url));
            }
        }
        return entries;
    }

    public Uri getRootUri() {
        return this.rootUri;
    }

    public boolean exists(LocalFilesystemURL inputURL) {
        try {
            getFileMetadataForLocalURL(inputURL);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public Uri nativeUriForFullPath(String fullPath) {
        if (fullPath == null) {
            return null;
        }
        String encodedPath = Uri.fromFile(new File(fullPath)).getEncodedPath();
        if (encodedPath.startsWith("/")) {
            encodedPath = encodedPath.substring(1);
        }
        return this.rootUri.buildUpon().appendEncodedPath(encodedPath).build();
    }

    public LocalFilesystemURL localUrlforFullPath(String fullPath) {
        Uri nativeUri = nativeUriForFullPath(fullPath);
        if (nativeUri != null) {
            return toLocalUri(nativeUri);
        }
        return null;
    }

    protected static String normalizePath(String rawPath) {
        boolean isAbsolutePath = rawPath.startsWith("/");
        if (isAbsolutePath) {
            rawPath = rawPath.replaceFirst("/+", "");
        }
        ArrayList<String> components = new ArrayList<>(Arrays.asList(rawPath.split("/+")));
        int index = 0;
        while (index < components.size()) {
            if (components.get(index).equals("..")) {
                components.remove(index);
                if (index > 0) {
                    components.remove(index - 1);
                    index--;
                }
            }
            index++;
        }
        StringBuilder normalizedPath = new StringBuilder();
        Iterator<String> it = components.iterator();
        while (it.hasNext()) {
            normalizedPath.append("/");
            normalizedPath.append(it.next());
        }
        if (isAbsolutePath) {
            return normalizedPath.toString();
        }
        return normalizedPath.toString().substring(1);
    }

    public long getFreeSpaceInBytes() {
        return 0;
    }

    public JSONObject getRootEntry() {
        if (this.rootEntry == null) {
            this.rootEntry = makeEntryForNativeUri(this.rootUri);
        }
        return this.rootEntry;
    }

    public JSONObject getParentForLocalURL(LocalFilesystemURL inputURL) throws IOException {
        Uri parentUri = inputURL.uri;
        String parentPath = new File(inputURL.uri.getPath()).getParent();
        if (!"/".equals(parentPath)) {
            parentUri = inputURL.uri.buildUpon().path(parentPath + '/').build();
        }
        return getEntryForLocalURL(LocalFilesystemURL.parse(parentUri));
    }

    /* access modifiers changed from: protected */
    public LocalFilesystemURL makeDestinationURL(String newName, LocalFilesystemURL srcURL, LocalFilesystemURL destURL, boolean isDirectory) {
        String newDest;
        if ("null".equals(newName) || "".equals(newName)) {
            newName = srcURL.uri.getLastPathSegment();
        }
        String newDest2 = destURL.uri.toString();
        if (newDest2.endsWith("/")) {
            newDest = newDest2 + newName;
        } else {
            newDest = newDest2 + "/" + newName;
        }
        if (isDirectory) {
            newDest = newDest + '/';
        }
        return LocalFilesystemURL.parse(newDest);
    }

    public JSONObject copyFileToURL(LocalFilesystemURL destURL, String newName, Filesystem srcFs, LocalFilesystemURL srcURL, boolean move) throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException {
        if (!move || srcFs.canRemoveFileAtLocalURL(srcURL)) {
            LocalFilesystemURL destination = makeDestinationURL(newName, srcURL, destURL, srcURL.isDirectory);
            CordovaResourceApi.OpenForReadResult ofrr = this.resourceApi.openForRead(srcFs.toNativeUri(srcURL));
            try {
                this.resourceApi.copyResource(ofrr, getOutputStreamForURL(destination));
                if (move) {
                    srcFs.removeFileAtLocalURL(srcURL);
                }
                return getEntryForLocalURL(destination);
            } catch (IOException e) {
                ofrr.inputStream.close();
                throw e;
            }
        } else {
            throw new NoModificationAllowedException("Cannot move file at source URL");
        }
    }

    public OutputStream getOutputStreamForURL(LocalFilesystemURL inputURL) throws IOException {
        return this.resourceApi.openOutputStream(toNativeUri(inputURL));
    }

    public void readFileAtURL(LocalFilesystemURL inputURL, long start, long end, ReadFileCallback readFileCallback) throws IOException {
        CordovaResourceApi.OpenForReadResult ofrr = this.resourceApi.openForRead(toNativeUri(inputURL));
        if (end < 0) {
            end = ofrr.length;
        }
        long numBytesToRead = end - start;
        if (start > 0) {
            try {
                ofrr.inputStream.skip(start);
            } catch (Throwable th) {
                ofrr.inputStream.close();
                throw th;
            }
        }
        InputStream inputStream = ofrr.inputStream;
        if (end < ofrr.length) {
            inputStream = new LimitedInputStream(inputStream, numBytesToRead);
        }
        readFileCallback.handleData(inputStream, ofrr.mimeType);
        ofrr.inputStream.close();
    }

    protected class LimitedInputStream extends FilterInputStream {
        long numBytesToRead;

        public LimitedInputStream(InputStream in, long numBytesToRead2) {
            super(in);
            this.numBytesToRead = numBytesToRead2;
        }

        public int read() throws IOException {
            if (this.numBytesToRead <= 0) {
                return -1;
            }
            this.numBytesToRead--;
            return this.in.read();
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            if (this.numBytesToRead <= 0) {
                return -1;
            }
            int bytesToRead = byteCount;
            if (((long) byteCount) > this.numBytesToRead) {
                bytesToRead = (int) this.numBytesToRead;
            }
            int numBytesRead = this.in.read(buffer, byteOffset, bytesToRead);
            this.numBytesToRead -= (long) numBytesRead;
            return numBytesRead;
        }
    }
}

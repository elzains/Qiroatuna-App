package org.apache.cordova.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.globalization.Globalization;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalFilesystem extends Filesystem {
    private final Context context;

    public LocalFilesystem(String name2, Context context2, CordovaResourceApi resourceApi, File fsRoot) {
        super(Uri.fromFile(fsRoot).buildUpon().appendEncodedPath("").build(), name2, resourceApi);
        this.context = context2;
    }

    public String filesystemPathForFullPath(String fullPath) {
        return new File(this.rootUri.getPath(), fullPath).toString();
    }

    public String filesystemPathForURL(LocalFilesystemURL url) {
        return filesystemPathForFullPath(url.path);
    }

    private String fullPathForFilesystemPath(String absolutePath) {
        if (absolutePath == null || !absolutePath.startsWith(this.rootUri.getPath())) {
            return null;
        }
        return absolutePath.substring(this.rootUri.getPath().length() - 1);
    }

    public Uri toNativeUri(LocalFilesystemURL inputURL) {
        return nativeUriForFullPath(inputURL.path);
    }

    public LocalFilesystemURL toLocalUri(Uri inputURL) {
        if (!"file".equals(inputURL.getScheme())) {
            return null;
        }
        File f = new File(inputURL.getPath());
        Uri resolvedUri = Uri.fromFile(f);
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
        if (f.isDirectory()) {
            b.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(b.build());
    }

    public LocalFilesystemURL URLforFilesystemPath(String path) {
        return localUrlforFullPath(fullPathForFilesystemPath(path));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r9.optBoolean("create");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getFileForLocalURL(org.apache.cordova.file.LocalFilesystemURL r7, java.lang.String r8, org.json.JSONObject r9, boolean r10) throws org.apache.cordova.file.FileExistsException, java.io.IOException, org.apache.cordova.file.TypeMismatchException, org.apache.cordova.file.EncodingException, org.json.JSONException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            if (r9 == 0) goto L_0x0012
            java.lang.String r4 = "create"
            boolean r0 = r9.optBoolean(r4)
            if (r0 == 0) goto L_0x0012
            java.lang.String r4 = "exclusive"
            boolean r1 = r9.optBoolean(r4)
        L_0x0012:
            java.lang.String r4 = ":"
            boolean r4 = r8.contains(r4)
            if (r4 == 0) goto L_0x0022
            org.apache.cordova.file.EncodingException r4 = new org.apache.cordova.file.EncodingException
            java.lang.String r5 = "This path has an invalid \":\" in it."
            r4.<init>(r5)
            throw r4
        L_0x0022:
            if (r10 == 0) goto L_0x003f
            java.lang.String r4 = "/"
            boolean r4 = r8.endsWith(r4)
            if (r4 != 0) goto L_0x003f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r8 = r4.toString()
        L_0x003f:
            java.lang.String r4 = "/"
            boolean r4 = r8.startsWith(r4)
            if (r4 == 0) goto L_0x006a
            java.lang.String r4 = normalizePath(r8)
            org.apache.cordova.file.LocalFilesystemURL r3 = r6.localUrlforFullPath(r4)
        L_0x004f:
            java.io.File r2 = new java.io.File
            java.lang.String r4 = r6.filesystemPathForURL(r3)
            r2.<init>(r4)
            if (r0 == 0) goto L_0x00a3
            if (r1 == 0) goto L_0x008c
            boolean r4 = r2.exists()
            if (r4 == 0) goto L_0x008c
            org.apache.cordova.file.FileExistsException r4 = new org.apache.cordova.file.FileExistsException
            java.lang.String r5 = "create/exclusive fails"
            r4.<init>(r5)
            throw r4
        L_0x006a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r7.path
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = normalizePath(r4)
            org.apache.cordova.file.LocalFilesystemURL r3 = r6.localUrlforFullPath(r4)
            goto L_0x004f
        L_0x008c:
            if (r10 == 0) goto L_0x009f
            r2.mkdir()
        L_0x0091:
            boolean r4 = r2.exists()
            if (r4 != 0) goto L_0x00cf
            org.apache.cordova.file.FileExistsException r4 = new org.apache.cordova.file.FileExistsException
            java.lang.String r5 = "create fails"
            r4.<init>(r5)
            throw r4
        L_0x009f:
            r2.createNewFile()
            goto L_0x0091
        L_0x00a3:
            boolean r4 = r2.exists()
            if (r4 != 0) goto L_0x00b1
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException
            java.lang.String r5 = "path does not exist"
            r4.<init>(r5)
            throw r4
        L_0x00b1:
            if (r10 == 0) goto L_0x00c1
            boolean r4 = r2.isFile()
            if (r4 == 0) goto L_0x00cf
            org.apache.cordova.file.TypeMismatchException r4 = new org.apache.cordova.file.TypeMismatchException
            java.lang.String r5 = "path doesn't exist or is file"
            r4.<init>(r5)
            throw r4
        L_0x00c1:
            boolean r4 = r2.isDirectory()
            if (r4 == 0) goto L_0x00cf
            org.apache.cordova.file.TypeMismatchException r4 = new org.apache.cordova.file.TypeMismatchException
            java.lang.String r5 = "path doesn't exist or is directory"
            r4.<init>(r5)
            throw r4
        L_0x00cf:
            org.json.JSONObject r4 = r6.makeEntryForURL(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.LocalFilesystem.getFileForLocalURL(org.apache.cordova.file.LocalFilesystemURL, java.lang.String, org.json.JSONObject, boolean):org.json.JSONObject");
    }

    public boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws InvalidModificationException {
        File fp = new File(filesystemPathForURL(inputURL));
        if (!fp.isDirectory() || fp.list().length <= 0) {
            return fp.delete();
        }
        throw new InvalidModificationException("You can't delete a directory that is not empty.");
    }

    public boolean exists(LocalFilesystemURL inputURL) {
        return new File(filesystemPathForURL(inputURL)).exists();
    }

    public long getFreeSpaceInBytes() {
        return DirectoryManager.getFreeSpaceInBytes(this.rootUri.getPath());
    }

    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws FileExistsException {
        return removeDirRecursively(new File(filesystemPathForURL(inputURL)));
    }

    /* access modifiers changed from: protected */
    public boolean removeDirRecursively(File directory) throws FileExistsException {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                removeDirRecursively(file);
            }
        }
        if (directory.delete()) {
            return true;
        }
        throw new FileExistsException("could not delete: " + directory.getName());
    }

    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        File fp = new File(filesystemPathForURL(inputURL));
        if (!fp.exists()) {
            throw new FileNotFoundException();
        }
        File[] files = fp.listFiles();
        if (files == null) {
            return null;
        }
        LocalFilesystemURL[] entries = new LocalFilesystemURL[files.length];
        for (int i = 0; i < files.length; i++) {
            entries[i] = URLforFilesystemPath(files[i].getPath());
        }
        return entries;
    }

    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL inputURL) throws FileNotFoundException {
        File file = new File(filesystemPathForURL(inputURL));
        if (!file.exists()) {
            throw new FileNotFoundException("File at " + inputURL.uri + " does not exist.");
        }
        JSONObject metadata = new JSONObject();
        try {
            metadata.put("size", file.isDirectory() ? 0 : file.length());
            metadata.put(Globalization.TYPE, this.resourceApi.getMimeType(Uri.fromFile(file)));
            metadata.put("name", file.getName());
            metadata.put("fullPath", inputURL.path);
            metadata.put("lastModifiedDate", file.lastModified());
            return metadata;
        } catch (JSONException e) {
            return null;
        }
    }

    private void copyFile(Filesystem srcFs, LocalFilesystemURL srcURL, File destFile, boolean move) throws IOException, InvalidModificationException, NoModificationAllowedException {
        String realSrcPath;
        if (!move || (realSrcPath = srcFs.filesystemPathForURL(srcURL)) == null || !new File(realSrcPath).renameTo(destFile)) {
            copyResource(this.resourceApi.openForRead(srcFs.toNativeUri(srcURL)), new FileOutputStream(destFile));
            if (move) {
                srcFs.removeFileAtLocalURL(srcURL);
            }
        }
    }

    private void copyDirectory(Filesystem srcFs, LocalFilesystemURL srcURL, File dstDir, boolean move) throws IOException, NoModificationAllowedException, InvalidModificationException, FileExistsException {
        String realSrcPath;
        if (move && (realSrcPath = srcFs.filesystemPathForURL(srcURL)) != null) {
            File srcDir = new File(realSrcPath);
            if (dstDir.exists()) {
                if (dstDir.list().length > 0) {
                    throw new InvalidModificationException("directory is not empty");
                }
                dstDir.delete();
            }
            if (srcDir.renameTo(dstDir)) {
                return;
            }
        }
        if (dstDir.exists()) {
            if (dstDir.list().length > 0) {
                throw new InvalidModificationException("directory is not empty");
            }
        } else if (!dstDir.mkdir()) {
            throw new NoModificationAllowedException("Couldn't create the destination directory");
        }
        for (LocalFilesystemURL childLocalUrl : srcFs.listChildren(srcURL)) {
            File target = new File(dstDir, new File(childLocalUrl.path).getName());
            if (childLocalUrl.isDirectory) {
                copyDirectory(srcFs, childLocalUrl, target, false);
            } else {
                copyFile(srcFs, childLocalUrl, target, false);
            }
        }
        if (move) {
            srcFs.recursiveRemoveFileAtLocalURL(srcURL);
        }
    }

    public JSONObject copyFileToURL(LocalFilesystemURL destURL, String newName, Filesystem srcFs, LocalFilesystemURL srcURL, boolean move) throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException {
        if (!new File(filesystemPathForURL(destURL)).exists()) {
            throw new FileNotFoundException("The source does not exist");
        }
        LocalFilesystemURL destinationURL = makeDestinationURL(newName, srcURL, destURL, srcURL.isDirectory);
        Uri dstNativeUri = toNativeUri(destinationURL);
        Uri srcNativeUri = srcFs.toNativeUri(srcURL);
        if (dstNativeUri.equals(srcNativeUri)) {
            throw new InvalidModificationException("Can't copy onto itself");
        } else if (!move || srcFs.canRemoveFileAtLocalURL(srcURL)) {
            File destFile = new File(dstNativeUri.getPath());
            if (destFile.exists()) {
                if (!srcURL.isDirectory && destFile.isDirectory()) {
                    throw new InvalidModificationException("Can't copy/move a file to an existing directory");
                } else if (srcURL.isDirectory && destFile.isFile()) {
                    throw new InvalidModificationException("Can't copy/move a directory to an existing file");
                }
            }
            if (!srcURL.isDirectory) {
                copyFile(srcFs, srcURL, destFile, move);
            } else if (dstNativeUri.toString().startsWith(srcNativeUri.toString() + '/')) {
                throw new InvalidModificationException("Can't copy directory into itself");
            } else {
                copyDirectory(srcFs, srcURL, destFile, move);
            }
            return makeEntryForURL(destinationURL);
        } else {
            throw new InvalidModificationException("Source URL is read-only (cannot move)");
        }
    }

    public long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws IOException, NoModificationAllowedException {
        byte[] rawData;
        FileOutputStream out;
        boolean append = false;
        if (offset > 0) {
            truncateFileAtURL(inputURL, (long) offset);
            append = true;
        }
        if (isBinary) {
            rawData = Base64.decode(data, 0);
        } else {
            rawData = data.getBytes(Charset.defaultCharset());
        }
        ByteArrayInputStream in = new ByteArrayInputStream(rawData);
        try {
            byte[] buff = new byte[rawData.length];
            String absolutePath = filesystemPathForURL(inputURL);
            out = new FileOutputStream(absolutePath, append);
            in.read(buff, 0, buff.length);
            out.write(buff, 0, rawData.length);
            out.flush();
            out.close();
            if (isPublicDirectory(absolutePath)) {
                broadcastNewFile(Uri.fromFile(new File(absolutePath)));
            }
            return (long) rawData.length;
        } catch (NullPointerException e) {
            NoModificationAllowedException realException = new NoModificationAllowedException(inputURL.toString());
            realException.initCause(e);
            throw realException;
        } catch (Throwable th) {
            out.close();
            throw th;
        }
    }

    private boolean isPublicDirectory(String absolutePath) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (File f : this.context.getExternalMediaDirs()) {
                if (f != null && absolutePath.startsWith(f.getAbsolutePath())) {
                    return true;
                }
            }
        }
        return absolutePath.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void broadcastNewFile(Uri nativeUri) {
        this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", nativeUri));
    }

    public long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws IOException {
        if (!new File(filesystemPathForURL(inputURL)).exists()) {
            throw new FileNotFoundException("File at " + inputURL.uri + " does not exist.");
        }
        RandomAccessFile raf = new RandomAccessFile(filesystemPathForURL(inputURL), "rw");
        try {
            if (raf.length() >= size) {
                raf.getChannel().truncate(size);
            } else {
                size = raf.length();
                raf.close();
            }
            return size;
        } finally {
            raf.close();
        }
    }

    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        return new File(filesystemPathForURL(inputURL)).exists();
    }

    private static void copyResource(CordovaResourceApi.OpenForReadResult input, OutputStream outputStream) throws IOException {
        try {
            InputStream inputStream = input.inputStream;
            if (!(inputStream instanceof FileInputStream) || !(outputStream instanceof FileOutputStream)) {
                byte[] buffer = new byte[8192];
                while (true) {
                    int bytesRead = inputStream.read(buffer, 0, 8192);
                    if (bytesRead <= 0) {
                        break;
                    }
                    outputStream.write(buffer, 0, bytesRead);
                }
            } else {
                FileChannel inChannel = ((FileInputStream) input.inputStream).getChannel();
                FileChannel outChannel = ((FileOutputStream) outputStream).getChannel();
                long offset = 0;
                long length = input.length;
                if (input.assetFd != null) {
                    offset = input.assetFd.getStartOffset();
                }
                inChannel.position(offset);
                outChannel.transferFrom(inChannel, 0, length);
            }
        } finally {
            input.inputStream.close();
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}

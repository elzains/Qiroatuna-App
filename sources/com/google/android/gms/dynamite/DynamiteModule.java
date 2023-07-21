package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamite.zzb;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class DynamiteModule {
    private static Boolean zzaRO;
    private static zza zzaRP;
    private static zzb zzaRQ;
    private static final HashMap<String, byte[]> zzaRR = new HashMap<>();
    private static String zzaRS;
    private static final zzb.zza zzaRT = new zzb.zza() {
        public int zzH(Context context, String str) {
            return DynamiteModule.zzH(context, str);
        }

        public int zzb(Context context, String str, boolean z) throws zza {
            return DynamiteModule.zzb(context, str, z);
        }
    };
    public static final zzb zzaRU = new zzb() {
        public zzb.C0779zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0779zzb zzb = new zzb.C0779zzb();
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSc != 0) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSb = zza.zzH(context, str);
                if (zzb.zzaSb != 0) {
                    zzb.zzaSd = -1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaRV = new zzb() {
        public zzb.C0779zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0779zzb zzb = new zzb.C0779zzb();
            zzb.zzaSb = zza.zzH(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSd = -1;
            } else {
                zzb.zzaSc = zza.zzb(context, str, true);
                if (zzb.zzaSc != 0) {
                    zzb.zzaSd = 1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaRW = new zzb() {
        public zzb.C0779zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0779zzb zzb = new zzb.C0779zzb();
            zzb.zzaSb = zza.zzH(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSb >= zzb.zzaSc) {
                zzb.zzaSd = -1;
            } else {
                zzb.zzaSd = 1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRX = new zzb() {
        public zzb.C0779zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0779zzb zzb = new zzb.C0779zzb();
            zzb.zzaSb = zza.zzH(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSd = -1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRY = new zzb() {
        public zzb.C0779zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0779zzb zzb = new zzb.C0779zzb();
            zzb.zzaSb = zza.zzH(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSc = zza.zzb(context, str, false);
            } else {
                zzb.zzaSc = zza.zzb(context, str, true);
            }
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSd = -1;
            }
            return zzb;
        }
    };
    private final Context zzaRZ;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzb {

        public interface zza {
            int zzH(Context context, String str);

            int zzb(Context context, String str, boolean z) throws zza;
        }

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$zzb$zzb  reason: collision with other inner class name */
        public static class C0779zzb {
            public int zzaSb = 0;
            public int zzaSc = 0;
            public int zzaSd = 0;
        }

        C0779zzb zza(Context context, String str, zza zza2) throws zza;
    }

    private DynamiteModule(Context context) {
        this.zzaRZ = (Context) zzac.zzw(context);
    }

    private static ClassLoader zzBS() {
        return new PathClassLoader(zzaRS, ClassLoader.getSystemClassLoader()) {
            /* access modifiers changed from: protected */
            public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                if (!str.startsWith("java.") && !str.startsWith("android.")) {
                    try {
                        return findClass(str);
                    } catch (ClassNotFoundException e) {
                    }
                }
                return super.loadClass(str, z);
            }
        };
    }

    public static int zzH(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length() + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(".").append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf3 = String.valueOf(declaredField.get((Object) null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf3).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf3).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String valueOf4 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf4.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf4) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zzI(Context context, String str) {
        return zzb(context, str, false);
    }

    private static DynamiteModule zzJ(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static Context zza(Context context, String str, byte[] bArr, zzb zzb2) {
        try {
            return (Context) zzd.zzF(zzb2.zza(zzd.zzA(context), str, bArr));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(valueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzb zzb2, String str) throws zza {
        zzb.C0779zzb zza2 = zzb2.zza(context, str, zzaRT);
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza2.zzaSb).append(" and remote module ").append(str).append(":").append(zza2.zzaSc).toString());
        if (zza2.zzaSd == 0 || ((zza2.zzaSd == -1 && zza2.zzaSb == 0) || (zza2.zzaSd == 1 && zza2.zzaSc == 0))) {
            throw new zza(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza2.zzaSb).append(" and remote version is ").append(zza2.zzaSc).append(".").toString());
        } else if (zza2.zzaSd == -1) {
            return zzJ(context, str);
        } else {
            if (zza2.zzaSd == 1) {
                try {
                    return zza(context, str, zza2.zzaSc);
                } catch (zza e) {
                    zza zza3 = e;
                    String valueOf = String.valueOf(zza3.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    if (zza2.zzaSb != 0) {
                        final int i = zza2.zzaSb;
                        if (zzb2.zza(context, str, new zzb.zza() {
                            public int zzH(Context context, String str) {
                                return i;
                            }

                            public int zzb(Context context, String str, boolean z) {
                                return 0;
                            }
                        }).zzaSd == -1) {
                            return zzJ(context, str);
                        }
                    }
                    throw new zza("Remote load failed. No local fallback found.", zza3);
                }
            } else {
                throw new zza(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza2.zzaSd).toString());
            }
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws zza {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzaRO;
        }
        if (bool != null) {
            return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
        }
        throw new zza("Failed to determine which loading route to use.");
    }

    private static void zza(ClassLoader classLoader) throws zza {
        try {
            zzaRQ = zzb.zza.zzcf((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new zza("Failed to instantiate dynamite loader", e);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0071=Splitter:B:35:0x0071, B:25:0x0043=Splitter:B:25:0x0043} */
    public static int zzb(android.content.Context r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)
            java.lang.Boolean r0 = zzaRO     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            android.content.Context r0 = r6.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            java.lang.Class r2 = r0.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            java.lang.String r0 = "sClassLoader"
            java.lang.reflect.Field r3 = r2.getDeclaredField(r0)     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            monitor-enter(r2)     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
            r0 = 0
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x0095 }
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0     // Catch:{ all -> 0x0095 }
            if (r0 == 0) goto L_0x0046
            java.lang.ClassLoader r3 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0095 }
            if (r0 != r3) goto L_0x0040
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0095 }
        L_0x0031:
            monitor-exit(r2)     // Catch:{ all -> 0x0095 }
        L_0x0032:
            zzaRO = r0     // Catch:{ all -> 0x0074 }
        L_0x0034:
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00e6
            int r0 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x00c3 }
        L_0x003f:
            return r0
        L_0x0040:
            zza(r0)     // Catch:{ zza -> 0x00ec }
        L_0x0043:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0095 }
            goto L_0x0031
        L_0x0046:
            java.lang.String r0 = "com.google.android.gms"
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ all -> 0x0095 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x0095 }
            boolean r0 = r0.equals(r4)     // Catch:{ all -> 0x0095 }
            if (r0 == 0) goto L_0x0061
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0095 }
            r3.set(r0, r4)     // Catch:{ all -> 0x0095 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0095 }
            goto L_0x0031
        L_0x0061:
            int r0 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x0089 }
            java.lang.String r4 = zzaRS     // Catch:{ zza -> 0x0089 }
            if (r4 == 0) goto L_0x0071
            java.lang.String r4 = zzaRS     // Catch:{ zza -> 0x0089 }
            boolean r4 = r4.isEmpty()     // Catch:{ zza -> 0x0089 }
            if (r4 == 0) goto L_0x0077
        L_0x0071:
            monitor-exit(r2)     // Catch:{ all -> 0x0095 }
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x0074:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            throw r0
        L_0x0077:
            java.lang.ClassLoader r4 = zzBS()     // Catch:{ zza -> 0x0089 }
            zza(r4)     // Catch:{ zza -> 0x0089 }
            r5 = 0
            r3.set(r5, r4)     // Catch:{ zza -> 0x0089 }
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ zza -> 0x0089 }
            zzaRO = r4     // Catch:{ zza -> 0x0089 }
            monitor-exit(r2)     // Catch:{ all -> 0x0095 }
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x0089:
            r0 = move-exception
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0095 }
            r3.set(r0, r4)     // Catch:{ all -> 0x0095 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0095 }
            goto L_0x0031
        L_0x0095:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0095 }
            throw r0     // Catch:{ ClassNotFoundException -> 0x0098, IllegalAccessException -> 0x00f1, NoSuchFieldException -> 0x00ef }
        L_0x0098:
            r0 = move-exception
        L_0x0099:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            int r3 = r3.length()     // Catch:{ all -> 0x0074 }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r4.<init>(r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = "Failed to load module via V2: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
            android.util.Log.w(r2, r0)     // Catch:{ all -> 0x0074 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0074 }
            goto L_0x0032
        L_0x00c3:
            r0 = move-exception
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r3 = r0.length()
            if (r3 == 0) goto L_0x00e0
            java.lang.String r0 = r2.concat(r0)
        L_0x00da:
            android.util.Log.w(r1, r0)
            r0 = 0
            goto L_0x003f
        L_0x00e0:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x00da
        L_0x00e6:
            int r0 = zzc((android.content.Context) r6, (java.lang.String) r7, (boolean) r8)
            goto L_0x003f
        L_0x00ec:
            r0 = move-exception
            goto L_0x0043
        L_0x00ef:
            r0 = move-exception
            goto L_0x0099
        L_0x00f1:
            r0 = move-exception
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws zza {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            IObjectWrapper zza2 = zzbm.zza(zzd.zzA(context), str, i);
            if (zzd.zzF(zza2) != null) {
                return new DynamiteModule((Context) zzd.zzF(zza2));
            }
            throw new zza("Failed to load remote module.");
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.zza zzbm(android.content.Context r6) {
        /*
            r1 = 0
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r2 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r2)
            com.google.android.gms.dynamite.zza r0 = zzaRP     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x000c
            com.google.android.gms.dynamite.zza r0 = zzaRP     // Catch:{ all -> 0x003a }
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
        L_0x000b:
            return r0
        L_0x000c:
            com.google.android.gms.common.zze r0 = com.google.android.gms.common.zze.zzuY()     // Catch:{ all -> 0x003a }
            int r0 = r0.isGooglePlayServicesAvailable(r6)     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0019
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            r0 = r1
            goto L_0x000b
        L_0x0019:
            java.lang.String r0 = "com.google.android.gms"
            r3 = 3
            android.content.Context r0 = r6.createPackageContext(r0, r3)     // Catch:{ Exception -> 0x003d }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ Exception -> 0x003d }
            java.lang.String r3 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r0 = r0.loadClass(r3)     // Catch:{ Exception -> 0x003d }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x003d }
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch:{ Exception -> 0x003d }
            com.google.android.gms.dynamite.zza r0 = com.google.android.gms.dynamite.zza.C0780zza.zzce(r0)     // Catch:{ Exception -> 0x003d }
            if (r0 == 0) goto L_0x0057
            zzaRP = r0     // Catch:{ Exception -> 0x003d }
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            goto L_0x000b
        L_0x003a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            throw r0
        L_0x003d:
            r0 = move-exception
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r4 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x003a }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x003a }
            int r5 = r0.length()     // Catch:{ all -> 0x003a }
            if (r5 == 0) goto L_0x005a
            java.lang.String r0 = r4.concat(r0)     // Catch:{ all -> 0x003a }
        L_0x0054:
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x003a }
        L_0x0057:
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            r0 = r1
            goto L_0x000b
        L_0x005a:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x003a }
            r0.<init>(r4)     // Catch:{ all -> 0x003a }
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzbm(android.content.Context):com.google.android.gms.dynamite.zza");
    }

    private static int zzc(Context context, String str, boolean z) {
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            return 0;
        }
        try {
            return zzbm.zza(zzd.zzA(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws zza {
        byte[] bArr;
        zzb zzb2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            bArr = zzaRR.get(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString());
            zzb2 = zzaRQ;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        } else if (zzb2 == null) {
            throw new zza("DynamiteLoaderV2 was not cached.");
        } else {
            Context zza2 = zza(context.getApplicationContext(), str, bArr, zzb2);
            if (zza2 != null) {
                return new DynamiteModule(zza2);
            }
            throw new zza("Failed to get module context");
        }
    }

    private static int zzd(Context context, String str, boolean z) throws zza {
        Cursor cursor = null;
        try {
            cursor = zze(context, str, z);
            if (cursor == null || !cursor.moveToFirst()) {
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new zza("Failed to connect to dynamite module ContentResolver.");
            }
            int i = cursor.getInt(0);
            if (i > 0) {
                synchronized (DynamiteModule.class) {
                    zzaRR.put(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString(), Base64.decode(cursor.getString(3), 0));
                    zzaRS = cursor.getString(2);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return i;
        } catch (Exception e) {
            if (e instanceof zza) {
                throw e;
            }
            throw new zza("V2 version check failed", e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static Cursor zze(Context context, String str, boolean z) {
        String str2 = z ? "api_force_staging" : "api";
        String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
        return context.getContentResolver().query(Uri.parse(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length()).append(valueOf).append(str2).append("/").append(str).toString()), (String[]) null, (String) null, (String[]) null, (String) null);
    }

    public Context zzBR() {
        return this.zzaRZ;
    }

    public IBinder zzdT(String str) throws zza {
        try {
            return (IBinder) this.zzaRZ.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e);
        }
    }
}

package android.support.p000v4.app;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: android.support.v4.app.BaseFragmentActivityHoneycomb */
abstract class BaseFragmentActivityHoneycomb extends BaseFragmentActivityGingerbread {
    BaseFragmentActivityHoneycomb() {
    }

    public View onCreateView(View parent, String name2, Context context, AttributeSet attrs) {
        View v = dispatchFragmentsOnCreateView(parent, name2, context, attrs);
        if (v != null || Build.VERSION.SDK_INT < 11) {
            return v;
        }
        return super.onCreateView(parent, name2, context, attrs);
    }
}

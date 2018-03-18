package ext.android.imageloader.progress;

import android.support.annotation.RestrictTo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LHEE on 2018/3/19.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public final class ProgressDispatcher implements ProgressListener {

    private static volatile ProgressDispatcher _instance;
    private final Map<Object, ProgressListener> mListeners;

    private ProgressDispatcher() {
        mListeners = new HashMap<>(5);
    }

    public static ProgressDispatcher get() {
        if (_instance == null) {
            synchronized (ProgressDispatcher.class) {
                if (_instance == null) {
                    _instance = new ProgressDispatcher();
                }
            }
        }
        return _instance;
    }

    public void addListener(Object model, ProgressListener listener) {
        if (!mListeners.containsKey(model)) {
            mListeners.put(model, listener);
        }
    }

    public void removeListener(Object model) {
        if (mListeners.containsKey(model)) {
            mListeners.remove(model);
        }
    }

    @Override
    public void update(String url, long bytesRead, long contentLength, boolean done) {
        if (mListeners.get(url) != null) {
            mListeners.get(url).update(url, bytesRead, contentLength, done);
        }
    }
}

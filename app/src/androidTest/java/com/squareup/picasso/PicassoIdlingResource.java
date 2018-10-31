package com.squareup.picasso;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.Stage;

import java.lang.ref.WeakReference;

/**
 *  Idling resource for Picasso image loading lib.
 *  After it is registered in tests Espresso will wait until images are loaded and idling resource is idle.
 */
public class PicassoIdlingResource implements IdlingResource, ActivityLifecycleCallback {

    private static final int IDLE_POLL_DELAY_MILLIS = 100;
    private ResourceCallback mCallback;
    private WeakReference<Picasso> mPicassoWeakReference;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public String getName() {
        return "PicassoIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        if (isIdle()) {
            notifyDone();
            return true;
        } else {
      /* Force a re-check of the idle state in a little while.
       * If isIdleNow() returns false, Espresso only polls it every few seconds which can slow down our tests.
       */
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isIdleNow();
                }
            }, IDLE_POLL_DELAY_MILLIS);
            return false;
        }
    }

    private boolean isIdle() {
        return mPicassoWeakReference == null
                || mPicassoWeakReference.get() == null
                || mPicassoWeakReference.get().targetToAction.isEmpty();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mCallback = resourceCallback;
    }

    private void notifyDone() {
        if (mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }

    @Override
    public void onActivityLifecycleChanged(Activity activity, Stage stage) {
        switch (stage) {
            case RESUMED:
                mPicassoWeakReference = new WeakReference<>(Picasso.with(activity));
                break;
            case PAUSED:
                // Clean up reference
                mPicassoWeakReference = null;
                break;
            default: // NOP
        }
    }
}

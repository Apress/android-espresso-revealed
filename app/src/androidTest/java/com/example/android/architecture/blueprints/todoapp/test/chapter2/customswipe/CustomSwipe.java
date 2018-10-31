package com.example.android.architecture.blueprints.todoapp.test.chapter2.customswipe;

import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.support.test.espresso.action.MotionEvents;
import android.support.test.espresso.action.Swiper;
import android.util.Log;
import android.view.MotionEvent;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Lets us create our own swipe gestures.
 */
public enum CustomSwipe implements Swiper {

    CUSTOM {
        @Override
        public Status sendSwipe(UiController uiController,
                                float[] startCoordinates,
                                float[] endCoordinates,
                                float[] precision) {
            return sendLinearSwipe(
                    uiController,
                    startCoordinates,
                    endCoordinates,
                    precision,
                    swipeCustomDuration);
        }
    };

    /**
     * The number of motion events to send for each swipe.
     */
    private static final int SWIPE_EVENT_COUNT = 10;
    /**
     * The duration of a swipe
     */
    private static int swipeCustomDuration = 0;

    /**
     * Setting duration to our custom swipe action
     *
     * @param duration length of time a custom swipe should last for in milliseconds.
     */
    public void setSwipeDuration(int duration) {
        swipeCustomDuration = duration;
    }

    private static Swiper.Status sendLinearSwipe(UiController uiController, float[] startCoordinates,
                                                 float[] endCoordinates, float[] precision, int duration) {
        checkNotNull(uiController);
        checkNotNull(startCoordinates);
        checkNotNull(endCoordinates);
        checkNotNull(precision);

        float[][] steps = interpolate(startCoordinates, endCoordinates, SWIPE_EVENT_COUNT);
        final int delayBetweenMovements = duration / steps.length;

        MotionEvent downEvent = MotionEvents.sendDown(uiController, startCoordinates, precision).down;
        try {
            for (int i = 0; i < steps.length; i++) {
                if (!MotionEvents.sendMovement(uiController, downEvent, steps[i])) {
                    Log.e("CustomSwipeActions",
                            "Injection of move event as part of the swipe failed. Sending cancel event.");
                    MotionEvents.sendCancel(uiController, downEvent);
                    return Swiper.Status.FAILURE;
                }

                long desiredTime = downEvent.getDownTime() + delayBetweenMovements * i;
                long timeUntilDesired = desiredTime - SystemClock.uptimeMillis();
                if (timeUntilDesired > 10) {
                    uiController.loopMainThreadForAtLeast(timeUntilDesired);
                }
            }

            if (!MotionEvents.sendUp(uiController, downEvent, endCoordinates)) {
                Log.e("CustomSwipeActions",
                        "Injection of up event as part of the swipe failed. Sending cancel event.");
                MotionEvents.sendCancel(uiController, downEvent);
                return Swiper.Status.FAILURE;
            }
        } finally {
            downEvent.recycle();
        }
        return Swiper.Status.SUCCESS;
    }

    private static float[][] interpolate(float[] start, float[] end, int steps) {
        checkElementIndex(1, start.length);
        checkElementIndex(1, end.length);

        float[][] res = new float[steps][2];

        for (int i = 1; i < steps + 1; i++) {
            res[i - 1][0] = start[0] + (end[0] - start[0]) * i / (steps + 2f);
            res[i - 1][1] = start[1] + (end[1] - start[1]) * i / (steps + 2f);
        }

        return res;
    }
}

package com.example.android.architecture.blueprints.todoapp.test.helpers

import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.runner.lifecycle.Stage.RESUMED
import android.support.v4.app.FragmentActivity


object Utils {

    /**
     * Returns resumed activity shown to the user.
     *
     *
     * Null may be returned in case no activity is found in this state.
     */
    val currentActivity: FragmentActivity?
        get() = getActivityInStage(RESUMED)

    /**
     * Method to return string resource text based on the context - main or test app
     *
     * @param resourceId - id of string resource from strings.xml file
     * @return Text representation of String resource
     */
    fun getStringFromTestResource(resourceId: Int): String {
        return InstrumentationRegistry
                .getContext()
                .resources
                .getString(resourceId)
    }

    /**
     * Returns activity in stage RESUMED which is displayed to the user.
     *
     * @return Null may be returned in case no activity is found in this state.
     */
    fun getActivityInStage(stage: Stage): FragmentActivity? {
        val currentActivity = arrayOfNulls<FragmentActivity>(1)
        getInstrumentation().runOnMainSync {
            val activities = ActivityLifecycleMonitorRegistry
                    .getInstance()
                    .getActivitiesInStage(stage)
            val iterator = activities.iterator()

            if (iterator.hasNext()) {
                currentActivity[0] = iterator.next() as FragmentActivity
            }
        }

        return currentActivity[0]
    }
}

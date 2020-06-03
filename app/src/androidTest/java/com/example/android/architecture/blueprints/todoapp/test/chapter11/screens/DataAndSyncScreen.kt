package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.Matchers.allOf

class DataAndSyncScreen : BaseScreen() {

    /*
   ELEMENTS
   */

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val syncFrequencyOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_sync_frequency),
            isCompletelyDisplayed()
    )
    private val syncFrequencyNeverOption = uiDevice.findObject(UiSelector().text("Never"))

    /*
    ACTIONS
     */

    fun tapOnSyncFrequencyOption(): DataAndSyncScreen {
        onView(syncFrequencyOption).perform(click())
        return this
    }

    fun tapOnNeverOption(): DataAndSyncScreen {
        syncFrequencyNeverOption.click()
        return this
    }

    /*
    HELPERS
    */

    fun isNeverOptionSelected(): Boolean {
        return try {
            syncFrequencyNeverOption.isSelected
            true
        } catch (ex: NoMatchingViewException) {
            false
        }
    }
}
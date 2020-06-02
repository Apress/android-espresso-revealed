package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.Matchers.allOf

import com.example.android.architecture.blueprints.todoapp.test.chapter3.viewWithText
import org.hamcrest.Matchers.anything

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
    fun clickOnSyncFrequencyOption(): DataAndSyncScreen {
        onView(syncFrequencyOption).perform(click())
        return this
    }

    fun clickOnNeverOption(): DataAndSyncScreen {
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
package com.example.android.architecture.blueprints.todoapp.test.chapter10.devicesetup

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * Demonstrates how to set device in test friendly state to reduce test flakiness.
 */
class DeviceSetupTest : BaseTest() {

    private var toDoTitle = ""
    private var toDoDescription = ""

    @Before
    override fun setUp() {
        super.setUp()
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun addsNewToDo() {
        // Adding new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        // Verifying new TO-DO with title is shown in the TO-DO list.
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    companion object {
        /**
         * Set of shell commands that should be run before test
         * which turn off System animations, increase Accessibility Touch & hold delay
         * and disable Virtual keyboard appearance.
         */
        @BeforeClass
        @JvmStatic
        fun setDevicePreferences() {
            val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            // System animation properties.
            uiDevice.executeShellCommand("settings put global animator_duration_scale 0.0")
            uiDevice.executeShellCommand("settings put global transition_animation_scale 0.0")
            uiDevice.executeShellCommand("settings put global window_animation_scale 0.0")

            // Touch & hold delay property.
            uiDevice.executeShellCommand("settings put secure long_press_timeout 1500")

            // Virtual keyboard appearance property.
            uiDevice.executeShellCommand("settings put secure show_ime_with_hard_keyboard 0")
        }
    }
}


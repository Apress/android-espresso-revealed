package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.preference.PreferenceActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.PreferenceMatchers.withKey
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Contains tests that use both Espresso and UI Automator API.
 */
@RunWith(AndroidJUnit4::class)
class EspressoUiAutomatorTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val twoSeconds = 2000L

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    /**
     * Clicks notification triggered by application under test and
     * verifies that TasksActivity is shown.
     */
    @Test
    fun clickNotificationOpensTasksActivity() {
        openDrawer()
        onView(allOf(withId(R.id.design_menu_item_text),
                withText(R.string.settings_title))).perform(click())
        onData(CoreMatchers.instanceOf(PreferenceActivity.Header::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(1)
                .onChildView(withId(android.R.id.title))
                .check(matches(withText("Notifications")))
                .perform(click())

        // Click on Send notification item.
        onData(withKey("notifications_send"))
                .inAdapterView(allOf(
                        withId(android.R.id.list),
                        withParent(withId(android.R.id.list_container))))
                .check(matches(isDisplayed()))
                .perform(click())

        // Perform UI Automator actions.
        uiDevice.openNotification()

        // Click notification by text and wait for application to appear.
        uiDevice.findObject(By.text("My notification"))
                .clickAndWait(Until.newWindow(), twoSeconds)

        // Verify application layout with Espresso.
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()))
    }

    /**
     * Waits for delayed notification triggered by application under test and
     * verifies that TasksActivity is shown.
     */
    @Test
    fun clickNotificationOpensTasksActivityDelay() {
        openDrawer()
        onView(allOf(withId(R.id.design_menu_item_text),
                withText(R.string.settings_title))).perform(click())
        onData(CoreMatchers.instanceOf(PreferenceActivity.Header::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(1)
                .onChildView(withId(android.R.id.title))
                .check(matches(withText("Notifications")))
                .perform(click())
        onData(withKey("notifications_with_delay_send"))
                .inAdapterView(allOf(
                        withId(android.R.id.list),
                        withParent(withId(android.R.id.list_container))))
                .check(matches(isDisplayed()))
                .perform(click())

        // Perform UI Automator actions.
        uiDevice.openNotification()

        // Wait and click delayed notification by text.
        uiDevice.findObject(By.res("com.android.systemui:id/notification_stack_scroller"))
                .wait(Until.findObject(By.text("My notification")), 8000)
                .clickAndWait(Until.newWindow(), twoSeconds)

        // Verify application layout with Espresso.
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()))
    }
}

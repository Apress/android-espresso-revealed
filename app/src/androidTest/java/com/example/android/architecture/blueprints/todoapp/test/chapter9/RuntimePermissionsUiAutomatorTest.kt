package com.example.android.architecture.blueprints.todoapp.test.chapter9

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RuntimePermissionsUiAutomatorTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val todoAppPackageName = InstrumentationRegistry.getTargetContext().packageName
    private val testContext = InstrumentationRegistry.getContext()

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun takesCameraPicture() {
        val toDoTitle = TestData.getToDoTitle()

        // Adding new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())

        // Clicking on camera button to trigger the permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())

        // UIAutomator - click permission dialog ALLOW button.
        uiDevice.findObject(By.res("com.android.packageinstaller:id/permission_allow_button")).click()

        onView(withId(R.id.picture)).perform(click())
        waitForElement(onView(withId(R.id.fab_edit_task_done))).perform(click())

        // verifying new TO-DO with title is shown in the TO-DO list.
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun deniesAndGrantsPermission() {
        val toDoTitle = TestData.getToDoTitle()

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.makePhoto)).perform(click())

        // UIAutomator - click permission dialog DENY button.
        uiDevice.findObject(By.res("com.android.packageinstaller:id/permission_deny_button")).click()

        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_action)).perform(click())

        uiDevice.findObject(By.res("com.android.packageinstaller:id/permission_allow_button")).click()

        onView(withId(R.id.picture)).perform(click())
        waitForElement(onView(withId(R.id.fab_edit_task_done))).perform(click())

        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun deniesAndGrantsPermissionFromSettings() {
        val toDoTitle = TestData.getToDoTitle()

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.makePhoto)).perform(click())

        uiDevice.findObject(By.res("com.android.packageinstaller:id/permission_deny_button"))
                .click()

        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_action)).perform(click())

        // UIAutomator - click on permission dialog checkbox and DENY button
        uiDevice
                .findObject(By.res("com.android.packageinstaller:id/do_not_ask_checkbox"))
                .click()
        uiDevice
                .findObject(By.res("com.android.packageinstaller:id/permission_deny_button"))
                .click()

        // Clicking camera button to trigger permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_text))
                .check(matches(allOf(isDisplayed(), withText("Camera unavailable"))))

        sendApplicationSettingsIntent()
        enableCameraPermission()
        launchBackToDoApplication()

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.picture)).perform(click())
        waitForElement(onView(withId(R.id.fab_edit_task_done))).perform(click())
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    private fun sendApplicationSettingsIntent() {
        // Create intent to open To-Do application settings.
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", todoAppPackageName, null)
        intent.data = uri
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        testContext.startActivity(intent)
    }

    private fun launchBackToDoApplication() {
        // Create intent to open To-Do application.
        val intent = testContext.packageManager.getLaunchIntentForPackage(todoAppPackageName)
        InstrumentationRegistry.getContext().startActivity(intent)
    }

    private fun enableCameraPermission() {
        // Wait for application Settings to appear
        uiDevice.wait(Until.hasObject(By.pkg("com.android.settings")), 5000)

        // Click on Permissions item.
        uiDevice.findObject(By.res("com.android.settings:id/list"))
                .children[3].clickAndWait(Until.newWindow(), 2000)

        // CLick on Camera item and wait for checked toggle state.
        uiDevice.findObject(By.res("android:id/list"))
                .children[0].click()
        uiDevice.findObject(By.res("android:id/list"))
                .children[0].wait(Until.checked(true), 1000)
    }
}

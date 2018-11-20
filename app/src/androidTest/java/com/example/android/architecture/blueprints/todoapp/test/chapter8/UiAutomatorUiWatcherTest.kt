package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.*
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Demonstrates [UiWatcher] functionality in test.
 */
@RunWith(AndroidJUnit4::class)
class UiAutomatorUiWatcherTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Before
    // Register dialog watcher.
    fun before() = registerStatisticsDialogWatcher()

    @After
    fun after() = uiDevice.removeWatcher("StatisticsDialog")

    @Test
    fun dismissesStatisticsDialogUsingWatcher() {

        val toolbar =
                "com.example.android.architecture.blueprints.todoapp.mock:id/toolbar"
        val menuDrawer =
                "com.example.android.architecture.blueprints.todoapp.mock:id/design_navigation_view"

        // Open menu drawer.
        uiDevice.findObject(
                UiSelector().resourceId(toolbar))
                .getChild(UiSelector().className(ImageButton::class.java.name))
                .click()

        // Open Statistics section.
        uiDevice.findObject(
                UiSelector()
                        .resourceId(menuDrawer)
                        .childSelector(
                                UiSelector()
                                        .className(LinearLayoutCompat::class.java.name).instance(1)))
                .click()

        /**
         * Locate Statistics label based on the view id.
         * At this moment watcher kicks in and dismissed dialog by clicking on OK button.
         */
        val statistics: UiObject = uiDevice.findObject(UiSelector()
                .resourceId("com.example.android.architecture.blueprints.todoapp.mock:id/statistics"))

        // Assert expected text is shown.
        assertTrue("Expected statistics label: \"You have no tasks.\" but got: ${statistics.text}",
                statistics.text == "You have no tasks.")
    }

    /**
     * Register Statistics dialog watcher that will monitor dialog presence.
     * Dialog will be dismissed when appeared by clicking on OK button.
     */
    private fun registerStatisticsDialogWatcher() {
        uiDevice.registerWatcher("StatisticsDialog", statisticsDialogWatcher)

        // Run registered watcher.
        uiDevice.runWatchers()
    }

    /**
     * Remove previously registered Statistics dialog.
     */
    private fun removeStatisticsDialogWatcher() {
        uiDevice.removeWatcher("StatisticsDialog")
    }

    companion object {
        private val instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

        val statisticsDialogWatcher = UiWatcher {
            val okDialogButton = uiDevice.findObject(By.res("android:id/button1"))
            if (null != okDialogButton) {
                okDialogButton.click()
                return@UiWatcher true
            }
            false
        }
    }
}

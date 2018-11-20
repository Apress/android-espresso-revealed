package com.example.android.architecture.blueprints.todoapp.test.chapter14

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Demonstrates Truth assertion sample.
 */
@RunWith(AndroidJUnit4::class)
class TruthTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun launchTasksActivity() {
        ActivityScenario.launch(TasksActivity::class.java)
    }

    /**
     * Specifically fails the test using JUnit assertion.
     */
    @Test
    fun generatesJunitAssertionError() {
        val selector = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_add_task"))

        // JUnit assertion.
        assertFalse(
                "Element with selector $selector is present on the screen when it should not",
                selector.exists())
    }

    /**
     * Specifically fails the test using Truth assertion.
     */
    @Test
    fun generatesTruthAssertionError() {
        val selector = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_add_task"))

        // Truth assertion.
        assertThat(selector.exists()).isFalse()
    }
}

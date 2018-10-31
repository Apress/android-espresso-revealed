package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Contains tests that use [UiSelector] selectors for elements location.
 */
@RunWith(AndroidJUnit4::class)
class UiAutomatorUiSelectorTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val fourSecondsTimeout = 4000L

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    /**
     * Creates two To-Do items, marks first as done and verifies its text.
     */
    @Test
    fun uiSelectorSample() {

        // Add first To-Do item.
        uiDevice.findObject(
                UiSelector().resourceId(
                        "com.example.android.architecture.blueprints.todoapp.mock:id/fab_add_task"))
                .click()
        uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/add_task_title"))
                .text = "item 1"
        uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_edit_task_done"))
                .click()
        uiDevice.findObject(UiSelector().text("TO-DO saved")).waitUntilGone(fourSecondsTimeout)

        // Add second To-Do item.
        uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_add_task"))
                .click()
        uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/add_task_title"))
                .text = "item 2"
        uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_edit_task_done"))
                .click()
        uiDevice.findObject(UiSelector().text("TO-DO saved")).waitUntilGone(fourSecondsTimeout)

        // Mark first To-Do item as done, click on it and validate text.
        uiDevice.findObject(UiSelector().className(RecyclerView::class.java.name)
                .childSelector(UiSelector().checkable(true)))
                .click()
        uiDevice.findObject(UiSelector().className(RecyclerView::class.java.name)
                .childSelector(UiSelector().className(LinearLayout::class.java)).instance(0))
                .click()
        val detailViewTitle = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/task_detail_title"))
        assertTrue("To-Do \"item 1\" is not shown.", detailViewTitle.exists())
        assertTrue("To-Do \"item 1\" is not shown.", detailViewTitle.text.equals("item 1"))
    }

    /**
     * Shows how uiSelectorSample() test can be simplified
     * by declaring UiObject elements in advance.
     */
    @Test
    fun uiSelectorSampleSimplified() {

        // Declare UiObject instances that will be used later in test.
        val fabAddTask = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_add_task"))
        val taskTitle = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/add_task_title"))
        val fabDone = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/fab_edit_task_done"))
        val todoSavedText = uiDevice.findObject(UiSelector().text("TO-DO saved"))
        val taskDetailsTitle = uiDevice.findObject(UiSelector().resourceId(
                "com.example.android.architecture.blueprints.todoapp.mock:id/task_detail_title"))
        val firstTodoCheckbox = uiDevice.findObject(UiSelector()
                .className(RecyclerView::class.java.name)
                .childSelector(UiSelector().checkable(true)).instance(0))
        val firstTodoItem = uiDevice.findObject(UiSelector().className(RecyclerView::class.java.name)
                .childSelector(UiSelector().className(LinearLayout::class.java)).instance(0))

        // Add first To-Do item.
        fabAddTask.click()
        taskTitle.text = "item 1"
        fabDone.click()
        todoSavedText.waitUntilGone(fourSecondsTimeout)

        // Add second To-Do item.
        fabAddTask.click()
        taskTitle.text = "item 2"
        fabDone.click()
        todoSavedText.waitUntilGone(fourSecondsTimeout)

        // Mark first To-Do item as done, click on it and validate text.
        firstTodoCheckbox.click()
        firstTodoItem.click()
        assertTrue("To-Do \"item 1\" is not shown.", taskDetailsTitle.exists())
        assertTrue("To-Do \"item 1\" title was wrong.", taskDetailsTitle.text.equals("item 1"))
    }
}

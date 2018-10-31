package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import android.support.v7.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiAutomatorBySelectorTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val twoSeconds = 2000L
    private val fourSeconds = 4000L
    private val applicationPackage = "com.example.android.architecture.blueprints.todoapp.mock"

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    /**
     * Creates two To-Do items, marks first as done and verifies its text.
     */
    @Test
    fun bySelectorSample() {

        val fab = By.res(applicationPackage, "fab_add_task")
        // Add first To-Do item.
        uiDevice.wait(Until.findObject(fab), twoSeconds)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.findObject(By.res(applicationPackage, "add_task_title")).text = "item 1"
        uiDevice.findObject(By.res(applicationPackage, "fab_edit_task_done"))
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.wait(Until.gone(By.text("TO-DO saved")), fourSeconds)

        // Add second To-Do item.
        uiDevice.wait(Until.findObject(By.res(applicationPackage, "fab_add_task")), twoSeconds)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.findObject(By.res(applicationPackage, "add_task_title")).text = "item 2"
        uiDevice.findObject(By.res(applicationPackage, "fab_edit_task_done"))
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.wait(Until.gone(By.text("TO-DO saved")), fourSeconds)

        // Mark first To-Do item as done, click on it and validate text.
        val todoList = uiDevice.findObject(By.clazz(RecyclerView::class.java))
        todoList.children[0]
                .findObject(By.checkable(true))
                .click()
        uiDevice.findObject(By.clazz(RecyclerView::class.java)).children[0]
                .findObject(By.clickable(true))
                .wait(Until.checked(true), twoSeconds)
        uiDevice.findObject(By.checkable(true)).wait(Until.checked(true), twoSeconds)
        todoList.children[0]
                .click()
        assertTrue("To-Do \"item 1\" is not shown.", uiDevice.hasObject(By.text("item 1")))
    }

    /**
     * Shows how bySelectorSample() test can be simplified
     * by declaring BySelector elements in advance.
     */
    @Test
    fun bySelectorSampleWithFindObjects() {

        val fabAddTask = By.res(applicationPackage, "fab_add_task")
        val taskTitle = By.res(applicationPackage, "add_task_title")
        val fabDone = By.res(applicationPackage, "fab_edit_task_done")
        val todoSavedText = By.text("TO-DO saved")
        val checkBox = By.checkable(true)
        val toDoRecyclerView = By.clazz(RecyclerView::class.java)

        // Add first To-Do item.
        uiDevice.waitForWindowUpdate(uiDevice.currentPackageName, twoSeconds)
        uiDevice.wait(Until.findObject(fabAddTask), twoSeconds)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.findObject(taskTitle).text = "item 1"
        uiDevice.findObject(fabDone)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.wait(Until.gone(todoSavedText), fourSeconds)

        // Add second To-Do item.
        uiDevice.wait(Until.findObject(fabAddTask), twoSeconds)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.findObject(taskTitle).text = "item 2"
        uiDevice.findObject(fabDone)
                .clickAndWait(Until.newWindow(), twoSeconds)
        uiDevice.wait(Until.gone(todoSavedText), fourSeconds)

        // Mark first To-Do item as done, click on it and validate text.
        // Showcases findObjects() method use.
        val todoListItems = uiDevice.findObjects(toDoRecyclerView)
        todoListItems[0].findObject(checkBox).click()
        todoListItems[0].click()
        assertTrue("To-Do \"item 1\" is not shown.", uiDevice.hasObject(By.text("item 1")))
    }
}

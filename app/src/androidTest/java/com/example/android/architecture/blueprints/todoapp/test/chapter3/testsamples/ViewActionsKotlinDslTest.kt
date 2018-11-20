package com.example.android.architecture.blueprints.todoapp.test.chapter3.testsamples

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test

/**
 * Demonstrates ViewActions usage with Kotlin Espresso DSL.
 * See chapter3.EspressoDsl.kt for details.
 */
class ViewActionsKotlinDslTest : BaseTest() {

    private var toDoTitle = ""
    private var toDoDescription = ""

    // ViewInteractions used in tests
    private val addFab = viewWithId(fab_add_task)
    private val taskTitleField = viewWithId(add_task_title)
    private val taskDescriptionField = viewWithId(add_task_description)
    private val editDoneFab = viewWithId(fab_edit_task_done)
    private val todoCheckbox = viewWithId(todo_complete)
    private val toolbarFilter = viewWithId(menu_filter)
    private val todoTitle = viewWithId(todo_title)
    private val activeFilterOption = onView(allOf(withId(title), withText("Active")))
    private val completedFilterOption = onView(allOf(withId(title), withText("Completed")))
    private val snackbar = viewWithId(snackbar_text)

    @Before
    override fun setUp() {
        super.setUp()
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun addsNewToDoDsl() {
        // adding new TO-DO
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()
        // verifying new TO-DO with title is shown in the TO-DO list
        viewWithText(toDoTitle).checkDisplayed()
    }

    @Test
    fun checksToDoStateChangeDsl() {
        // adding new TO-DO
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()
        // marking our TO-DO as completed
        todoCheckbox.click()
        // filtering out the completed TO-DO
        toolbarFilter.click()
        activeFilterOption.click()
        todoTitle.checkNotDisplayed()
        toolbarFilter.click()
        completedFilterOption.click()
        todoTitle.checkMatches(allOf(withText(toDoTitle), isDisplayed()))
    }

    @Test
    fun addsNewToDoWithWaiterDsl() {
        // adding new TO-DO
        addFab.click()
        taskTitleField.wait().type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()
        snackbar.waitForGone()
        // verifying new TO-DO with title is shown in the TO-DO list
        viewWithText(toDoTitle).checkDisplayed()
    }

    @Test
    fun editsToDoDsl() {
        val editedToDoTitle = "Edited $toDoTitle"
        val editedToDoDescription = "Edited $toDoDescription"

        // TODO - replace with proper implementation
    }
}

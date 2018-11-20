package com.example.android.architecture.blueprints.todoapp.test.chapter5

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Demonstrates how to stub [Intent]s.
 */
class StubIntentTest {

    @get:Rule
    var intentsTestRule = IntentsTestRule(TasksActivity::class.java)

    private var toDoTitle = ""
    private var toDoDescription = ""

    // ViewInteractions used in tests.
    private val addFab = viewWithId(R.id.fab_add_task)
    private val taskTitleField = viewWithId(R.id.add_task_title)
    private val taskDescriptionField = viewWithId(R.id.add_task_description)
    private val editDoneFab = viewWithId(R.id.fab_edit_task_done)
    private val shareMenuItem =
            onView(allOf(withId(R.id.title), withText(R.string.share)))

    @Before
    fun setUp() {
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun stubsShareIntentByAction() {
        Intents.intending(hasAction(equalTo(Intent.ACTION_SEND)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // adding new TO-DO
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()
        // verifying new TO-DO with title is shown in the TO-DO list
        viewWithText(toDoTitle).checkDisplayed()
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }

    @Test
    fun stubsShareIntentByType() {

        Intents.intending(hasType("text/plain"))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // Adding new TO-DO.
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()

        // Verifying new TO-DO with title is shown in the TO-DO list.
        viewWithText(toDoTitle).checkDisplayed()
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }

    @Test
    fun stubsShareIntentByExtra() {

        Intents.intending(hasExtras(hasEntry(Intent.EXTRA_TEXT, toDoTitle)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // Adding new TO-DO.
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()

        // Verifying new TO-DO with title is shown in the TO-DO list.
        viewWithText(toDoTitle).checkDisplayed()
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }
}

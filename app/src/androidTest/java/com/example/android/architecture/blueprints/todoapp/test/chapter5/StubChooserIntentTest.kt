package com.example.android.architecture.blueprints.todoapp.test.chapter5

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StubChooserIntentTest {

    @get:Rule
    var intentsTestRule = IntentsTestRule(TasksActivity::class.java)

    private var toDoTitle = ""
    private var toDoDescription = ""

    // ViewInteractions used in tests
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

        Intents.intending(hasAction(equalTo(Intent.ACTION_CHOOSER)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // adding new TO-DO
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()
        // verifying new TO-DO with title is shown in the TO-DO list
        viewWithText(toDoTitle).checkDisplayed()
        //open menu and click on Share item
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }

    @Test
    fun stubsShareIntentByExtraType() {

        Intents.intending(hasExtras(hasEntry(Intent.EXTRA_INTENT, hasType("text/plain"))))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // Adding new TO-DO.
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()

        // Verifying new TO-DO with title is shown in the TO-DO list.
        viewWithText(toDoTitle).checkDisplayed()

        // Open menu and click on Share item.
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }

    @Test
    fun stubsShareIntentByExtraTitle() {

        Intents.intending(hasExtras(hasEntry(Intent.EXTRA_TITLE, "Share to")))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        // Adding new TO-DO.
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()
        editDoneFab.click()

        // Verifying new TO-DO with title is shown in the TO-DO list.
        viewWithText(toDoTitle).checkDisplayed()

        // Open menu and click on Share item.
        openContextualActionModeOverflowMenu()
        shareMenuItem.click()
        viewWithText(toDoTitle).click()
    }
}

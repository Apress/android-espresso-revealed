package com.example.android.architecture.blueprints.todoapp.test.chapter5

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.intent.rule.IntentsTestRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.R.id.getImage
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Demonstrates how to stub image selection [Intent]s.
 */
class StubSelectImageIntentTest {

    private var toDoTitle = ""
    private var toDoDescription = ""

    // ViewInteractions used in tests
    private val addFab = viewWithId(R.id.fab_add_task)
    private val taskTitleField = viewWithId(R.id.add_task_title)
    private val taskDescriptionField = viewWithId(R.id.add_task_description)
    private val editDoneFab = viewWithId(R.id.fab_edit_task_done)
    private val addImageButton = viewWithId(getImage)

    @get:Rule
    var intentsTestRule = IntentsTestRule(TasksActivity::class.java)

    @Before
    fun setUp() {
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun stubsImageIntentWithDrawable() {
        val toDoImage =
                com.example.android.architecture.blueprints.todoapp.mock.test.R.drawable.todo_image_drawable

        Intents.intending(not(isInternal()))
                .respondWith(IntentHelper.createImageResultFromDrawable(toDoImage))

        // Adding new TO-DO.
        addFab.click()
        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()

        // Click on Get image from gallery button. At this point stubbed image is returned.
        addImageButton.click()
        editDoneFab.click()
        viewWithText(toDoTitle).click()
    }

    @Test
    fun stubsImageIntentWithAsset() {
        val imageFromAssets = "todo_image_assets.png"

        Intents.intending(not(isInternal()))
                .respondWith(IntentHelper.createImageResultFromAssets(imageFromAssets))

        // Adding new TO-DO.
        addFab.click()

        // Validate that intent to start AddEditTaskActivity was sent.
        intended(hasComponent(AddEditTaskActivity::class.java.name))

        taskTitleField.type(toDoTitle).closeKeyboard()
        taskDescriptionField.type(toDoDescription).closeKeyboard()

        // Click on Get image from gallery button. At this point stubbed image is returned.
        addImageButton.click()

        // Validate sent intent action.
        intended(hasAction(Intent.ACTION_GET_CONTENT))

        editDoneFab.click()
        viewWithText(toDoTitle).click()
    }
}

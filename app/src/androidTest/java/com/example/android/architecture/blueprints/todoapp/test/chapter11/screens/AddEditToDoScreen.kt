package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

/**
 * Represents both Create new TO-DO and Edit existing TO-DO screens.
 */
class AddEditToDoScreen {

    private val addToDoDescriptionEditText = onView(withId(R.id.add_task_description))
    private val addToDoTitleEditText = onView(withId(R.id.add_task_title))
    private val doneFabButton = onView(withId(R.id.fab_edit_task_done))
    private val emptyToDoSnackbar = onView(withText(R.string.empty_task_message))
    private val upButton = onView(allOf(
            instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar))))

    fun typeToDoTitle(title: String): AddEditToDoScreen {
        addToDoTitleEditText.perform(typeText(title), closeSoftKeyboard())
        return this
    }

    fun typeToDoDescription(taskDescription: String): AddEditToDoScreen {
        addToDoDescriptionEditText.perform(typeText(taskDescription), closeSoftKeyboard())
        return this
    }

    fun updateToDoTitle(title: String): AddEditToDoScreen {
        addToDoTitleEditText.perform(clearText(), typeText(title), closeSoftKeyboard())
        return this
    }

    fun updateToDoDescription(taskDescription: String): AddEditToDoScreen {
        addToDoDescriptionEditText.perform(clearText(), typeText(taskDescription), closeSoftKeyboard())
        return this
    }

    /**
     * Add new TO-DO flow
     */
    fun addNewToDo(taskItem: TodoItem?): ToDoListScreen {
        typeToDoTitle(taskItem!!.title)
        typeToDoDescription(taskItem.description)
        clickDoneFabButton()
        return ToDoListScreen()
    }

    /**
     * Edit existing TO-DO flow
     */
    fun updateToDo(taskItem: TodoItem?): ToDoListScreen {
        updateToDoTitle(taskItem!!.title)
        updateToDoDescription(taskItem.description)
        clickDoneFabButton()
        return ToDoListScreen()
    }

    fun addEmptyToDo(): AddEditToDoScreen {
        clickDoneFabButton()
        return this
    }

    fun clickDoneFabButton(): ToDoListScreen {
        doneFabButton.perform(click())
        return ToDoListScreen()
    }

    fun clickUpButton(): ToDoListScreen {
        upButton.perform(click())
        return ToDoListScreen()
    }

    fun clickBackButton(): ToDoListScreen {
        Espresso.pressBack()
        return ToDoListScreen()
    }

    fun verifySnackbarForEmptyToDo(): AddEditToDoScreen {
        emptyToDoSnackbar.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        return this
    }
}

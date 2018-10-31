package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import com.example.android.architecture.blueprints.todoapp.test.helpers.Utils.getStringFromTestResource
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf

/**
 * Represents the TO-DO item Details screen.
 */
class ToDoDetailsScreen {

    private val taskMarkedCompleteText = getStringFromTestResource(com.example.android.architecture.blueprints.todoapp.mock.test.R.string.task_marked_complete)
    private val taskMarkedActiveText = getStringFromTestResource(com.example.android.architecture.blueprints.todoapp.mock.test.R.string.task_marked_active)
    private val taskDescription = onView(withId(R.id.task_detail_description))
    private val taskTitle = onView(withId(R.id.task_detail_title))
    private val completeTaskCheckbox = onView(withId(R.id.task_detail_complete))
    private val fabEditTaskButton = onView(withId(R.id.fab_edit_task))
    private val deleteTaskToolbarButton = onView(withId(R.id.menu_delete))
    private val completeSnackbar = onView(withText(taskMarkedCompleteText))
    private val activeSnackbar = onView(withText(taskMarkedActiveText))
    private val hamburgerButton = onView(allOf(instanceOf<Any>(ImageButton::class.java), withParent(withId(R.id.toolbar))))

    fun tapCheckbox(): ToDoDetailsScreen {
        completeTaskCheckbox.perform(click())
        return this
    }

    fun clickEditToDoFabButton(): AddEditToDoScreen {
        fabEditTaskButton.perform(click())
        return AddEditToDoScreen()
    }

    fun deleteTask(): ToDoListScreen {
        deleteTaskToolbarButton.perform(click())
        return ToDoListScreen()
    }

    fun verifyTaskDetails(taskItem: TodoItem): ToDoDetailsScreen {
        taskTitle.check(matches(withText(taskItem.title)))
        taskDescription.check(matches(withText(taskItem.description)))
        return this
    }

    fun verifyCompleteSnackbarShown(): ToDoDetailsScreen {
        completeSnackbar.check(matches(isDisplayed()))
        return this
    }

    fun verifyActiveSnackbarShown(): ToDoDetailsScreen {
        activeSnackbar.check(matches(isDisplayed()))
        return this
    }

    fun returnToTaskList(): ToDoListScreen {
        hamburgerButton.perform(click())
        return ToDoListScreen()
    }
}

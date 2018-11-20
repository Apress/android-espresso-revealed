package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions.ClickTodoCheckBoxWithTitleViewAction.clickTodoCheckBoxWithTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomViewActions.verifyTaskNotInTheList
import com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers.RecyclerViewMatchers.withTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers
import org.hamcrest.core.AllOf.allOf

/**
 * Represents TO-DO list screen.
 */
class ToDoListScreen : BaseScreen() {

    private val fabAddButton = onView(withId(R.id.fab_add_task))
    private val toolbarMenuFilter = onView(withId(R.id.menu_filter))
    private val todoList = onView(withId(R.id.tasks_list))
    private val todoSavedSnackbar = withText(R.string.successfully_saved_task_message)
    private val youHaveNoToDosText = onView(withText(R.string.no_tasks_all))
    private val taskMarkedCompleteSnackbar = onView(withText(R.string.task_marked_complete))
    private val snackbar = onView(withText(R.id.snackbar_text))
    private val screenTitle = onView(allOf(withText(R.string.app_name), withParent(withId(R.id.toolbar))))

    private val allFilterItem = onView(allOf(withId(R.id.title), withText(R.string.nav_all)))
    private val completedFilterItem = onView(allOf(withId(R.id.title), withText(R.string.nav_completed)))
    private val activeFilterItem = onView(allOf(withId(R.id.title), withText(R.string.nav_active)))

    private val clearCompletedMenuItem = onView(allOf(withId(R.id.title), withText(R.string.menu_clear)))
    private val refreshMenuItem = onView(allOf(withId(R.id.title), withText(R.string.refresh)))

    fun openTaskDetails(taskTitle: String): ToDoDetailsScreen {
        todoList.perform(actionOnHolderItem<RecyclerView.ViewHolder>(withTitle(taskTitle), click()))
        return ToDoDetailsScreen()
    }

    fun openFilter(): ToDoListScreen {
        toolbarMenuFilter.perform(click())
        return this
    }

    fun clickAddFabButton(): AddEditToDoScreen {
        ConditionWatchers.waitForElementIsGone(todoSavedSnackbar)
        ConditionWatchers.waitForElementIsGone(taskMarkedCompleteSnackbar)
        fabAddButton.perform(click())
        return AddEditToDoScreen()
    }

    fun verifyToDoIsDisplayed(taskItem: TodoItem?): ToDoListScreen {
        ConditionWatchers.waitForElementIsGone(todoSavedSnackbar)
        todoList.perform(scrollToHolder<RecyclerView.ViewHolder>(withTitle(taskItem!!.title)))
        return this
    }

    fun verifyToDoItemNotShown(taskItem: TodoItem?): ToDoListScreen {
        todoList.perform(verifyTaskNotInTheList(taskItem))
        return this
    }

    fun verifyToDoListScreenInitialState(): ToDoListScreen {
        screenTitle.check(matches(isDisplayed()))
        youHaveNoToDosText.check(matches(isDisplayed()))
        fabAddButton.check(matches(isDisplayed()))
        toolbarMenuFilter.check(matches(isDisplayed()))
        return this
    }

    fun showAllTasks(): ToDoListScreen {
        toolbarMenuFilter.perform(click())
        allFilterItem.perform(click())
        return this
    }

    fun showActiveTasks(): ToDoListScreen {
        toolbarMenuFilter.perform(click())
        activeFilterItem.perform(click())
        return this
    }

    fun completeTask(taskItem: TodoItem?): ToDoListScreen {
        todoList.perform(clickTodoCheckBoxWithTitle(taskItem!!.title))
        return this
    }

    fun showCompletedTasks(): ToDoListScreen {
        toolbarMenuFilter.perform(click())
        completedFilterItem.perform(click())
        return this
    }

    fun clearCompletedTasks(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        clearCompletedMenuItem.perform(click())
        return this
    }

    fun refreshTasksList(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        refreshMenuItem.perform(click())
        return this
    }

    fun shareTaskList(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        clearCompletedMenuItem.perform(click())
        return this
    }
}

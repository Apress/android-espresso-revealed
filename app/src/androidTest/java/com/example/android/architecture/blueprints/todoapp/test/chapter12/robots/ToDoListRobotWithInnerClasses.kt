package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

/**
 * Robot class sample with inner classes that represent functional view groups.
 */
fun tasksListInnerClasses(func: TasksListRobotWithInnerClasses.() -> Unit) = TasksListRobotWithInnerClasses().apply { func() }
class TasksListRobotWithInnerClasses {

    infix fun addTask(func: AddEditToDoRobot.() -> Unit): AddEditToDoRobot {
        onView(withId(R.id.fab_add_task)).perform(click())
        return AddEditToDoRobot().apply(func)
    }

    fun addTask() {
        onView(withId(R.id.fab_add_task)).perform(click())
    }

    fun verifyTaskShown(withTitle: String) {
        onView(withText(withTitle)).check(matches(isDisplayed()))
    }

    fun verifyTaskNotShown(withTitle: String) {
        onView(withText(withTitle)).check(matches(not(isDisplayed())))
    }

    fun markCompleted(toDoTitle: String) {
        onView(allOf(withId(R.id.todo_complete), hasSibling(withText(toDoTitle)))).perform(click())
    }

    fun checkEmptyState() {
        onView(withId(R.id.noTasksMain)).check(matches(isDisplayed()))
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()))
    }

    fun toDoListFilter(func: ToDoListFilter.() -> Unit) = ToDoListFilter().apply { func() }
    inner class ToDoListFilter {
        init {
            onView(withId(R.id.menu_filter)).perform(click())
        }

        fun showAll() {
            onView(allOf(withId(R.id.title), withText("All"))).perform(click())
        }

        fun showCompleted() {
            onView(allOf(withId(R.id.title), withText("Completed"))).perform(click())
        }

        fun showActive() {
            onView(allOf(withId(R.id.title), withText("Active"))).perform(click())
        }
    }

    fun toDoListMenu(func: ToDoListMenu.() -> Unit) = ToDoListMenu().apply { func() }
    inner class ToDoListMenu {
        init {
            openContextualActionModeOverflowMenu()
        }

        fun clearCompleted() {
            onView(allOf(withId(R.id.title), withText("Clear completed"))).perform(click())
        }

        fun refresh() {
            onView(allOf(withId(R.id.title), withText("Refresh"))).perform(click())
        }

        fun share() {
            onView(allOf(withId(R.id.title), withText("Share"))).perform(click())
        }
    }
}

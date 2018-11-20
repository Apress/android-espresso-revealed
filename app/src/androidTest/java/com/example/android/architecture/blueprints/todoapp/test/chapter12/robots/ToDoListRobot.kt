package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

/**
 * Extension function that takes ToDoListRobot class function(s) as a parameter,
 * executes this function(s) and returns ToDosListRobot instance.
 */
fun toDoList(func: ToDoListRobot.() -> Unit) = ToDoListRobot().apply { func() }

/**
 * Robot pattern applied to TO-DO list screen.
 */
class ToDoListRobot {

    fun addToDo() {
        onView(withId(R.id.fab_add_task)).perform(click())
    }

    infix fun addToDo(func: AddEditToDoRobot.() -> Unit): AddEditToDoRobot {
        onView(withId(R.id.fab_add_task)).perform(click())
        return AddEditToDoRobot().apply(func)
    }

    fun clearCompleted() {
        openContextualActionModeOverflowMenu()
        onView(allOf(withId(R.id.title), withText("Clear completed"))).perform(click())
    }

    fun refresh() {
        openContextualActionModeOverflowMenu()
        onView(allOf(withId(R.id.title), withText("Refresh"))).perform(click())
    }

    fun share() {
        openContextualActionModeOverflowMenu()
        onView(allOf(withId(R.id.title), withText("Share"))).perform(click())
    }

    fun showAll() {
        onView(withId(R.id.menu_filter)).perform(click())
        onView(allOf(withId(R.id.title), withText("All"))).perform(click())
    }

    fun showCompleted() {
        onView(withId(R.id.menu_filter)).perform(click())
        onView(allOf(withId(R.id.title), withText("Completed"))).perform(click())
    }

    fun showActive() {
        onView(withId(R.id.menu_filter)).perform(click())
        onView(allOf(withId(R.id.title), withText("Active"))).perform(click())
    }

    fun verifyToDoShown(withTitle: String) {
        onView(withText(withTitle)).check(matches(isDisplayed()))
    }

    fun verifyToDoNotShown(withTitle: String) {
        onView(withText(withTitle)).check(matches(not(isDisplayed())))
    }

    fun markCompleted(toDoTitle: String) {
        onView(allOf(withId(R.id.todo_complete), hasSibling(withText(toDoTitle)))).perform(click())
    }

    fun checkDefaultLayout() {
        onView(withId(R.id.noTasksMain)).check(matches(isDisplayed()))
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()))
    }
}

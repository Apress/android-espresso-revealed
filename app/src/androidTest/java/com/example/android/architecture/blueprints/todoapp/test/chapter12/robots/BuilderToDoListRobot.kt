package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

/**
 * Builder pattern applied to TO-DO list screen.
 */
class BuilderToDoListRobot {

    fun addToDo() {
        onView(withId(R.id.fab_add_task)).perform(click())
    }

    fun showCompleted(): BuilderToDoListRobot {
        onView(withId(R.id.menu_filter)).perform(click())
        onView(allOf(withId(R.id.title), withText("Completed"))).perform(click())
        return this
    }

    fun showActive(): BuilderToDoListRobot {
        onView(withId(R.id.menu_filter)).perform(click())
        onView(allOf(withId(R.id.title), withText("Active"))).perform(click())
        return this
    }

    fun verifyToDoShown(withTitle: String): BuilderToDoListRobot {
        onView(withText(withTitle)).check(matches(isDisplayed()))
        return this
    }

    fun verifyToDoNotShown(withTitle: String): BuilderToDoListRobot {
        onView(withText(withTitle)).check(matches(not(isDisplayed())))
        return this
    }

    fun markCompleted(toDoTitle: String): BuilderToDoListRobot {
        onView(allOf(withId(R.id.todo_complete), hasSibling(withText(toDoTitle)))).perform(click())
        return this
    }

    fun checkDefaultLayout(): BuilderToDoListRobot {
        onView(withId(R.id.noTasksMain)).check(matches(isDisplayed()))
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()))
        return this
    }
}

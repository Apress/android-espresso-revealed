package com.example.android.architecture.blueprints.todoapp.test.chapter3.testsamples

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomClickAction.clickElementWithVisibility
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions.ClickTodoCheckBoxWithTitleViewAction.clickTodoCheckBoxWithTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElementIsGone
import org.junit.Test

/**
 * Demonstrates RecyclerView actions usage in Kotlin.
 */
class RecyclerViewActionsKotlinTest : BaseTest() {

    private val todoSavedSnackbar = onView(withText(R.string.successfully_saved_task_message))

    @Test
    fun addNewToDosChained() {
        generateToDos(12)
        onView(withId(tasks_list))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, scrollTo()))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(1))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(11))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))
        Espresso.pressBack()
        onView(withId(tasks_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    @Test
    fun completeToDo() {
        generateToDos(10)
        onView(withId(tasks_list)).perform(clickTodoCheckBoxWithTitle("item 2"))
        onView(withId(tasks_list))
                .perform(CustomRecyclerViewActions.ScrollToLastHolder.scrollToLastHolder())
    }

    private fun generateToDos(count: Int) {
        for (i in 1..count) {
            waitForElementIsGone(todoSavedSnackbar, 3000)
            // adding new TO-DO
            onView(withId(fab_add_task)).perform(clickElementWithVisibility(20))
            onView(withId(add_task_title))
                    .perform(typeText("item $i"), closeSoftKeyboard())
            onView(withId(fab_edit_task_done)).perform(click())
            waitForElement(todoSavedSnackbar, 3000)
        }
        waitForElementIsGone(todoSavedSnackbar, 3000)
    }
}

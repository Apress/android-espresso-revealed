package com.example.android.architecture.blueprints.todoapp.test.chapter10.testrecorder

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso Test Recorder generated test with lowered depth parameters.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AddTodoEspressoTestRecorderLowerDepth {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun addsTodoEspressoTestRecorderLowerDepth() {
        val floatingActionButton = onView(
                allOf(withId(R.id.fab_add_task), isDisplayed()))
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
                withId(R.id.add_task_title))
        appCompatEditText.perform(scrollTo(), replaceText("item 1"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                withId(R.id.add_task_description))
        appCompatEditText2.perform(scrollTo(), replaceText("description 1"), closeSoftKeyboard())

        val floatingActionButton2 = onView(
                allOf(withId(R.id.fab_edit_task_done), isDisplayed()))
        floatingActionButton2.perform(click())

        val textView = onView(
                allOf(withId(R.id.todo_title), withText("item 1"), isDisplayed()))
        textView.check(matches(withText("item 1")))
    }
}

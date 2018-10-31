package com.example.android.architecture.blueprints.todoapp.test.chapter10.testrecorder

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddTodoEspressoTestRecorderLowerDepth {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun tasksActivityTest() {
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

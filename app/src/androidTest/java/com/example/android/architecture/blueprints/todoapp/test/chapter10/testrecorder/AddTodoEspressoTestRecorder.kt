package com.example.android.architecture.blueprints.todoapp.test.chapter10.testrecorder

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso Test Recorder generated test with default parameters.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AddTodoEspressoTestRecorder {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun addsTodoEspressoTestRecorder() {
        val floatingActionButton = onView(
                allOf(withId(R.id.fab_add_task), withContentDescription("Add todo"),
                        childAtPosition(
                                allOf(withId(R.id.coordinatorLayout),
                                        childAtPosition(
                                                withClassName(`is`("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()))
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.add_task_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_task_scroll_view),
                                        0),
                                0)))
        appCompatEditText.perform(scrollTo(), replaceText("item 1"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.add_task_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_task_scroll_view),
                                        0),
                                1)))
        appCompatEditText2.perform(scrollTo(), replaceText("description 1"), closeSoftKeyboard())

        val floatingActionButton2 = onView(
                allOf(withId(R.id.fab_edit_task_done), withContentDescription("Floating action button"),
                        childAtPosition(
                                allOf(withId(R.id.coordinatorLayout),
                                        childAtPosition(
                                                withClassName(`is`("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()))
        floatingActionButton2.perform(click())

        val textView = onView(
                allOf(withId(R.id.todo_title), withText("item 1"),
                        childAtPosition(
                                allOf(withId(R.id.todo_item),
                                        childAtPosition(
                                                withId(R.id.tasks_list),
                                                0)),
                                1),
                        isDisplayed()))
        textView.check(matches(withText("item 1")))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

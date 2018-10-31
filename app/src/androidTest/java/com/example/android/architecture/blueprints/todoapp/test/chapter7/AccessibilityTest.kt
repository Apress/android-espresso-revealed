package com.example.android.architecture.blueprints.todoapp.test.chapter7

import android.preference.PreferenceActivity
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.accessibility.AccessibilityChecks
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils.matchesViews
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.Matchers.instanceOf
import org.junit.BeforeClass
import org.junit.Test

class AccessibilityTest : BaseTest() {

    @Test
    fun addsTodo() {
        val toDoTitle = TestData.getToDoTitle()
        val toDoDescription = TestData.getToDoDescription()

        // adding new TO-DO
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        // verifying new TO-DO with title is shown in the TO-DO list
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun opensWebViewSaple() {
        openDrawer()
        onView(allOf(withId(R.id.design_menu_item_text),
                withText(R.string.settings_title))).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(3)
                .perform(click())
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun setAccessibilityPrefs() {
            AccessibilityChecks.enable()
                    .setRunChecksFromRootView(true)
                    .setSuppressingResultMatcher(matchesViews(anyOf(
                            hasSibling(withId(R.id.menu_filter)),
                            withChild(withChild(withId(android.support.design.R.id.snackbar_text))))))
                    .setThrowExceptionForErrors(false)
        }
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter15.parametrizedtest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Parametrized test with multiple parameters.
 */
@RunWith(value = Parameterized::class)
class ParametrizedTestMultipleParameters(
        private val title: String,
        private val description: String) : BaseTest() {

    @Test
    fun usesMultipleParameters() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(title), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(description), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())

        // Verify new TO-DO with title is shown in the TO-DO list.
        onView(withText(title)).check(matches(isDisplayed()))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = arrayOf(
                arrayOf("item 1", "description 1"),
                arrayOf("item 2", "description 2"),
                arrayOf("item 3", "description 3"))
    }
}

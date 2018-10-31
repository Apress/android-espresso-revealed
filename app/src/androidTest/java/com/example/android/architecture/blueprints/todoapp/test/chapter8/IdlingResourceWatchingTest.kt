package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.*
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.setFailureHandler
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.base.DefaultFailureHandler
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class IdlingResourceWatchingTest {

    private val okDialogButton = onView(withId(android.R.id.button1))

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Before
    fun setUp() {
        setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation().targetContext))
    }

    @After
    fun cleanUpIdlingRegisteredResources() {

    }

    @Test
    fun dismissesStatisticsDialogUsingWatcher() {
        CommonElements.openDrawer()
        // Open Statistics section.
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText(R.string.statistics_title))).perform(click())

        onView(withId(R.id.statistics)).check(matches(isDisplayed()))
        Espresso.pressBack()
    }

    inner class CustomFailureHandler(targetContext: Context) : FailureHandler {

        private val delegate: FailureHandler

        init {
            delegate = DefaultFailureHandler(targetContext)
        }

        override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
            try {
                delegate.handle(error, viewMatcher)
            } catch (e: Exception) {
                when (e) {
                    is NoMatchingViewException, is PerformException -> {
                        IdlingRegistry
                                .getInstance()
                                .register(ElementWatchingIdlingResource(okDialogButton, 3, "StatisticsOkDialogButton"))
                    }
                    else -> throw e
                }
            }
        }
    }
}

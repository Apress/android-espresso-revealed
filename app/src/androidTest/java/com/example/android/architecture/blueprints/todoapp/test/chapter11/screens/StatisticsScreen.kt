package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.core.AllOf.allOf

class StatisticsScreen : BaseScreen() {

    private val statisticsTextNoTasks = onView(allOf(withId(R.id.statistics), withText(R.string.statistics_no_tasks)))
    private val okDialogButton = onView(withId(android.R.id.button1))
    private val screenTitle = onView(allOf(withText(R.string.statistics_title), withParent(withId(R.id.toolbar))))

    fun verifyStatisticsScreenInitialState(): StatisticsScreen {
        screenTitle.check(matches(isDisplayed()))
        statisticsTextNoTasks.check(matches(isDisplayed()))
        return this
    }

    fun dismissAlertDialog(): StatisticsScreen {
        okDialogButton.perform(click())
        return this
    }
}

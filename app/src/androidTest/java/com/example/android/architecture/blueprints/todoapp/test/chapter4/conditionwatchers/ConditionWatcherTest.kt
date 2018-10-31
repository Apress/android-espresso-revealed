package com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement
import org.junit.Test

/**
 * ConditionWatcher test sample.
 */
class ConditionWatcherTest : BaseTest() {

    private val addTaskFab = onView(withId(R.id.fab_add_task))

    @Test
    fun waitForElementCondition() {
        waitForElement(addTaskFab, 400).perform(click())
    }
}

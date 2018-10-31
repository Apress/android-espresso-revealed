package com.example.android.architecture.blueprints.todoapp.test.chapter9

import android.Manifest
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RuntimePermissionsTest {

    /**
     * Manifest.permission.CAMERA permission will be granted before the test run.
     */
    @get:Rule
    var mRuntimePermissionRule = GrantPermissionRule
            .grant(Manifest.permission.CAMERA)

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun takesCameraPicture() {
        val toDoTitle = TestData.getToDoTitle()
        val toDoDescription = TestData.getToDoDescription()

        // Adding new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())

        // Clicking on camera button to trigger the permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.picture)).perform(click())
        waitForElement(onView(withId(R.id.fab_edit_task_done))).perform(click())

        // verifying new TO-DO with title is shown in the TO-DO list.
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }
}

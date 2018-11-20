package com.example.android.architecture.blueprints.todoapp.test.chapter14

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Sample of [ActivityScenario.launch] method usage.
 */
@RunWith(AndroidJUnit4::class)
class ActivityScenarioTest {

    @Before
    fun launchTasksActivity() {
        ActivityScenario.launch(TasksActivity::class.java)
    }

    @Test
    fun activityScenarioLaunchSample() {
        openContextualActionModeOverflowMenu()
        onView(allOf(withId(R.id.title), withText(R.string.refresh))).perform(click())
    }
}

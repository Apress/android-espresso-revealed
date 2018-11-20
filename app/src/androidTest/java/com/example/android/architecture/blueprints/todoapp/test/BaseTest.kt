package com.example.android.architecture.blueprints.todoapp.test

import android.Manifest
import androidx.test.espresso.Espresso.setFailureHandler
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customfailurehandler.CustomFailureHandler
import com.example.android.architecture.blueprints.todoapp.test.chapter2.screenshotwatcher.ScreenshotWatcher
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseTest {

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    /**
     * Makes the current test name available inside test methods.
     */
    @get:Rule
    var testName = TestName()

    /**
     * Takes screenshot on test failure.
     */
    @get:Rule
    var screenshotWatcher = ScreenshotWatcher()

    /**
     * The chain of rules where outerRule is the starting point.
     * Allows having dependencies between rules.
     */
    @get:Rule
    var ruleChain: RuleChain = RuleChain
            .outerRule(GrantPermissionRule.grant(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA))
            .around(TestName())
            .around(ScreenshotWatcher())

    @Before
    open fun setUp() {
        //launch(TasksActivity::class.java)
        setFailureHandler(CustomFailureHandler(
                InstrumentationRegistry.getInstrumentation().targetContext))
    }
}

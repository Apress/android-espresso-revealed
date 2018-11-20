package com.example.android.architecture.blueprints.todoapp.test.chapter13

import android.Manifest
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter2.screenshotwatcher.ScreenshotWatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test class that demonstrates supervised monkey tests run.
 */
@RunWith(AndroidJUnit4::class)
class MonkeyTest {

    @get:Rule
    var grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @get:Rule
    var screenshotWatcher = ScreenshotWatcher()

    /**
     * Monkey tests will be executed against TO-DO application.
     */
    @Test
    fun testsInstrumentedApp() {
        ActivityScenario.launch(TasksActivity::class.java)
        Monkey.run(200)
    }

    /**
     * Monkey tests will be executed against provided application package name.
     * This is the example of how to test 3rd party application.
     */
    @Test
    fun testsThirdPartyApp() {
        val packageName = "com.google.android.dialer"
        PackageInfo.launchPackage(packageName)
        Monkey.run(200, packageName)
    }
}

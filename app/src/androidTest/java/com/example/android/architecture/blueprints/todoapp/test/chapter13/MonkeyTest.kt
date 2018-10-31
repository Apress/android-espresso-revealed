package com.example.android.architecture.blueprints.todoapp.test.chapter13

import android.Manifest
import android.support.test.InstrumentationRegistry
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.test.chapter2.screenshotwatcher.ScreenshotWatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MonkeyTest {

    private val grantPermissionRule = GrantPermissionRule.grant(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val screenshotWatcher = ScreenshotWatcher()
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Rule
    @JvmField
    val ruleChain = RuleChain
            .outerRule(grantPermissionRule)
            .around(screenshotWatcher)!!

    @Before
    fun startMainActivityFromHomeScreen() {

    }

//    @After
//    fun resetReleaseApplication() {
//        uiDevice.executeShellCommand("pm clear ")
//    }

    @Test
    fun test() {
    }
}

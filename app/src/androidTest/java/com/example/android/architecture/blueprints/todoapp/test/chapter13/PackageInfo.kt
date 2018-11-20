package com.example.android.architecture.blueprints.todoapp.test.chapter13

import android.app.ActivityManager
import android.app.ActivityManager.ProcessErrorStateInfo.CRASHED
import android.app.ActivityManager.ProcessErrorStateInfo.NOT_RESPONDING
import android.content.Context
import androidx.test.espresso.PerformException
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

/**
 * Provides package helper methods.
 */
object PackageInfo {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val testContext = InstrumentationRegistry.getInstrumentation().context

    /**
     * Checks if there is a need to relaunch the application.
     *
     * @return true when application under test is not displayed to the user.
     */
    fun shouldRelaunchTheApp(monkeyAction: String, packageName: String): Boolean {
        if (!isAppInErrorState(monkeyAction, packageName)
                && uiDevice.currentPackageName != packageName) {
            return true
        }
        return false
    }

    /**
     * Launches application based on its package name.
     * @param packageName - the name of the package to launch.
     */
    fun launchPackage(packageName: String) {
        val intent = testContext
                .packageManager
                .getLaunchIntentForPackage(packageName)!!
        testContext.startActivity(intent)
        uiDevice.wait(Until.hasObject(By
                .pkg(packageName)),
                5000)
    }

    /**
     * Checks if target application process is in error state and throws an exception, otherwise returns true.
     *
     * @return false if application is in error state, otherwise throws exception and fails the test.
     */
    private fun isAppInErrorState(monkeyAction: String, packageName: String): Boolean {
        val manager = testContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var errorDescription = ""

        // Get processes in error state, return false when list is null.
        manager.processesInErrorState?.forEach {

            val isTargetPackage = it.processName.contains(packageName)
            when {
                isTargetPackage && it.condition == CRASHED ->
                    errorDescription = "Application $packageName crashed after $monkeyAction action"
                isTargetPackage && it.condition == NOT_RESPONDING ->
                    errorDescription = "Application $packageName not responding after $monkeyAction action"
            }
            /** Build and throw new Espresso PerformException with proper description and stacktrace
             *  At this point test is failed.
             */
            throw PerformException.Builder()
                    .withActionDescription(errorDescription)
                    .withCause(Throwable(it.stackTrace))
                    .build()
        }
        return false
    }
}

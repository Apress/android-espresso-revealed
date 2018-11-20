package com.example.android.architecture.blueprints.todoapp.test.chapter13

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity

/**
 * Class that keeps Monkey tests logic and main actions.
 */
object Monkey {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val toDoAppPackageName = appContext.packageName
    private const val numberOfSteps = 10

    // Random integer value used by modulus operator (%) to decide which action should be performed.
    private const val dragNow = 7
    private const val pressNowBack = 13

    // Variable that will keep action description for logging/exception building purpose.
    private var monkeyAction = ""


    /**
     * Drags from start to end coordinate.
     *
     * @param startX - start x coordinate
     * @param startY - start y coordinate
     * @param endX - end x coordinate
     * @param endY - end y coordinate
     */
    private fun drag(startX: Int, startY: Int, endX: Int, endY: Int) {
        uiDevice.drag(
                startX,
                startY,
                endX,
                endY,
                numberOfSteps)
    }

    /**
     * Runs monkey tests for provided package.
     *
     * @param actionsCount - number of events to execute during monkey tests.
     * @param packageName - package name that should be tested. If not provided TO-DO application is tested.
     */
    fun run(actionsCount: Int, packageName: String = toDoAppPackageName) {

        loop@ for (i in 0..actionsCount) {

            if (PackageInfo.shouldRelaunchTheApp(monkeyAction, packageName)) {
                relaunchApp(packageName)
            }

            val randomX = ScreenDimensions.randomX
            val randomY = ScreenDimensions.randomY

            when {
                i % dragNow == 0 -> {
                    val randomX2 = ScreenDimensions.randomX
                    val randomY2 = ScreenDimensions.randomY
                    monkeyAction = String.format(
                            "drag from: %d - %d to: %d - %d", randomX,
                            randomY, randomX2, randomY2
                    )
                    drag(randomX, randomY, randomX2, randomY2)
                    continue@loop
                }
                i % pressNowBack == 0 -> {
                    monkeyAction = "press back system button"
                    uiDevice.pressBack()
                    continue@loop
                }
                else -> {
                    monkeyAction = "click coordinate x:$randomX y:$randomY"
                    uiDevice.click(randomX, randomY)
                    continue@loop
                }
            }
        }
    }

    /**
     * Launches the application by its package name.
     * In case package name is equal to the TO-DO application package  ActivityScenario.launch() is used.
     *
     * @param packageName - name of the package to relaunch
     */
    private fun relaunchApp(packageName: String) {

        if (packageName == toDoAppPackageName) {
            ActivityScenario.launch(TasksActivity::class.java)
        } else {
            PackageInfo.launchPackage(packageName)
        }
    }
}

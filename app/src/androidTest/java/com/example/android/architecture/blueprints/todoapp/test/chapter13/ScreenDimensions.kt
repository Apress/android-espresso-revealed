package com.example.android.architecture.blueprints.todoapp.test.chapter13

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import java.util.*

/**
 * Calculates screen dimensions, navigation, status and action bars dimensions.
 * Generates random coordinates for monkey clicks.
 */
object ScreenDimensions {

    private val heightWithoutNavigationBar: Int
    private var width = 0
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val navBarResourceId =
            appContext.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    private val statusBarResourceId =
            appContext.resources.getIdentifier("status_bar_height", "dimen", "android")

    init {
        width = uiDevice.displayWidth
        heightWithoutNavigationBar = uiDevice.displayHeight - ScreenDimensions.navigationBarHeight
    }

    /**
     * Calculate navigation bar height.
     */
    val navigationBarHeight : Int get() {
        return if (navBarResourceId > 0) {
            appContext.resources.getDimensionPixelSize(navBarResourceId)
        } else {
            0
        }
    }

    /**
     * Calculate status bar height.
     */
    val statusBarHeight: Int get() {
        return if (statusBarResourceId > 0) {
            appContext.resources.getDimensionPixelSize(statusBarResourceId)
        } else {
            0
        }
    }

    val randomY: Int
        get()  = (statusBarHeight..heightWithoutNavigationBar).random()

    val randomX: Int
        get() = (0..width).random()

    private fun IntRange.random() =
            Random().nextInt((endInclusive + 1) - start) +  start
}

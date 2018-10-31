package com.example.android.architecture.blueprints.todoapp.test.chapter13

import android.support.test.InstrumentationRegistry

class ScreenDimensions {

    fun calculate() {
        // status bar height
        var statusBarHeight = 0
        val resourceId1 = InstrumentationRegistry.getTargetContext().resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId1 > 0) {
            statusBarHeight = InstrumentationRegistry.getTargetContext().resources.getDimensionPixelSize(resourceId1)
        }

        // action bar height
        var actionBarHeight = 0
        val styledAttributes = InstrumentationRegistry.getTargetContext().theme.obtainStyledAttributes(
                intArrayOf(android.R.attr.actionBarSize)
        )
        actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        // navigation bar height
        var navigationBarHeight = 0
        val resourceId = InstrumentationRegistry.getTargetContext().resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationBarHeight = InstrumentationRegistry.getTargetContext().resources.getDimensionPixelSize(resourceId)
        }
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.ViewInteraction
import android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click

class ElementWatchingIdlingResource(private val viewInteraction: ViewInteraction, private val loopsCount: Int, resourceName: String) : IdlingResource {

    private val name = resourceName
    @Volatile
    private lateinit var callback: IdlingResource.ResourceCallback

    override fun getName() = name

    override fun isIdleNow(): Boolean {
        return try {
            runOnUiThread {
                viewInteraction.click()
            }
            callback.onTransitionToIdle()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }
}

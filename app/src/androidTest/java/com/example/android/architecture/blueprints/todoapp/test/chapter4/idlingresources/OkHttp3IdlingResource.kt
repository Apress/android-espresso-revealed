package com.example.android.architecture.blueprints.todoapp.test.chapter4.idlingresources

import android.support.test.espresso.IdlingResource
import okhttp3.OkHttpClient

/**
 * The modified [IdlingResource] for [OkHttpClient].
 */
class OkHttp3IdlingResource(private val client: OkHttpClient) : IdlingResource {

    @Volatile
    private lateinit var callback: IdlingResource.ResourceCallback
    private val name = "okhttp"

    override fun getName() = name

    override fun isIdleNow(): Boolean {
        if (client.dispatcher().runningCallsCount() == 0) {
            callback.onTransitionToIdle()
            return true
        }
        return false
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }
}

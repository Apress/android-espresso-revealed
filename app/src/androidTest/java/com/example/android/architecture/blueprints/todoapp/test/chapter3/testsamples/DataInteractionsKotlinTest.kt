package com.example.android.architecture.blueprints.todoapp.test.chapter3.testsamples

import android.preference.PreferenceActivity
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.PreferenceMatchers.withKey
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test

/**
 * Demonstrates DataInteraction usage in Kotlin.
 */
class DataInteractionsKotlinTest : BaseTest() {

    @Test
    fun dataInteractionSample() {
        openDrawer()
        onView(allOf<View>(withId(R.id.design_menu_item_text),
                withText(R.string.settings_title))).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .onChildView(withId(android.R.id.title))
                .check(matches(withText("General")))
                .perform(click())
        onData(withKey("email_edit_text"))
                .inAdapterView(allOf(withId(android.R.id.list), withParent(withId(android.R.id.list_container))))
                .check(matches(isDisplayed()))
                .perform(click())
        onView(withId(android.R.id.edit)).perform(replaceText("sample@ema.il"))
        onView(withId(android.R.id.button1)).perform(click())
        onData(withKey("email_edit_text"))
                .inAdapterView(allOf(withId(android.R.id.list), withParent(withId(android.R.id.list_container))))
                .onChildView(withId(android.R.id.summary))
                .check(matches(withText("sample@ema.il")))
    }
}

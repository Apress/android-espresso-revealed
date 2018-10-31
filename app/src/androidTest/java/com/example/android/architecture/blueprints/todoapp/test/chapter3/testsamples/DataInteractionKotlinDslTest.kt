package com.example.android.architecture.blueprints.todoapp.test.chapter3.testsamples

import android.R.id.*
import android.preference.PreferenceActivity
import android.support.design.R.id.design_menu_item_text
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.PreferenceMatchers.withKey
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v14.preference.R.attr.summary
import com.example.android.architecture.blueprints.todoapp.R.string.settings_title
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

class DataInteractionKotlinDslTest : BaseTest() {

    //ViewInteractions and DataInteractions used in tests
    private val settingsMenuItem = onView(allOf(withId(design_menu_item_text), withText(settings_title)))
    private val settingsList = dataInstanceOf(PreferenceActivity.Header::class.java)
    private val shareEmailItem = onData(withKey("email_edit_text"))
    private val emailEditText = onView(withId(edit))
    private val okDialogButton = onView(withId(button1))

    @Test
    fun dataInteractionSample() {
        openDrawer()
        settingsMenuItem.click()
        settingsList
                .inAdapterById(list)
                .atPosition(0)
                .childById(title)
                .checkWithText("General")
                .click()
        shareEmailItem
                .inAdapterView(allOf(withId(list), parentWithId(list_container)))
                .checkDisplayed()
                .click()
        emailEditText.replace("sample@ema.il")
        okDialogButton.click()
        shareEmailItem
                .inAdapterView(allOf(withId(list), parentWithId(list_container)))
                .childById(summary)
                .checkWithText("sample@ema.il")
    }
}

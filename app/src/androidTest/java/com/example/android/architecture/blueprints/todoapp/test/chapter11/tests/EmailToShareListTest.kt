package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.GeneralScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.*
import org.junit.Assert.assertTrue

class EmailToShareListTest {

    private val settingsScreen = SettingsScreen()
    private val generalScreen = GeneralScreen()

    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun cancelEmailToShareToDoListOption() {
        settingsScreen.openGeneralScreen()
        generalScreen.tapOnEmailToShareOption()
                .cancelEmailToShareDialog()
        assertTrue("General screen isn't displayed after pressing Cancel on dialog view",
                generalScreen.isGeneralScreenDisplayed())
    }

    @Test
    fun addEmailToShareToDoListOption() {
        settingsScreen.openGeneralScreen()
        generalScreen.tapOnEmailToShareOption()
                .addEmailAddressToShare()
                .editEmailAdressToShare()
        assertTrue("Example email isn't set as email to share.",
                generalScreen.isEmailToShareSave())
    }
}
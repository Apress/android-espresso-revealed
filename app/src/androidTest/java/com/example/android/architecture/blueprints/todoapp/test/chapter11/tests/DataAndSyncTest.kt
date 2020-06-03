package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.DataAndSyncScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DataAndSyncTest {


    private val settingsScreen = SettingsScreen()
    private val dataAndSyncScreen = DataAndSyncScreen()

    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun enablesNotificationsAndVerifiesIfAdditionalOptionsArteVisible() {
        settingsScreen.openDataAndSyncScreen()
        dataAndSyncScreen.tapOnSyncFrequencyOption()
                .tapOnNeverOption()
                .tapOnSyncFrequencyOption()
        assertTrue("Never option isn't marked.",
                dataAndSyncScreen.isNeverOptionSelected())
    }
}
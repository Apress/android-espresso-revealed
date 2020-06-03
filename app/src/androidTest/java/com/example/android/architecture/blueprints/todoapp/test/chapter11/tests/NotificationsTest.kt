package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.NotificationsScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.Rule
import org.junit.Test

class NotificationsTest {

    private val settingsScreen = SettingsScreen()
    private val notificationsScreen = NotificationsScreen()

    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun enablesNotificationsAndVerifiesIfAdditionalOptionsArteVisible() {
        settingsScreen.openNotificationsScreen()
        notificationsScreen.enableNotificationsToggle()
                .verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications()
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.NotificationsScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class NotificationsTest {

    private val settingsScreen = SettingsScreen()
    private val notificationsScreen = NotificationsScreen()

    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun enablesNotificationsAndVerifiesIfAdditionalOptionsArteVisible() {
        settingsScreen
        settingsScreen.openNotificationsScreen()
        notificationsScreen.enableNotificationsToggle()
        assertFalse(("When enable notification options is turned on then 3 additional options should be visible"), notificationsScreen.verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications())
    }}

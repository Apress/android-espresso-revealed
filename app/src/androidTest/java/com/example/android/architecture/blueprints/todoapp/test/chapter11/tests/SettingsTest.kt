package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.ToDoListScreen
import org.junit.Test

/**
 * Validates TO-DOs application Settings functionality.
 */
class SettingsTest : BaseTest() {

    /**
     * Validates application UP button navigation from Settings screen.
     */
    @Test
    fun verifiesUpNavigation() {
        ToDoListScreen()
                .openMenu()
                .clickSettingsMenuItem()
                .navigateUpToToDoListScreen()
                .verifyToDoListScreenInitialState()
                .openMenu()
                .clickStatisticsMenuItem()
                .dismissAlertDialog()
                .openMenu()
                .clickSettingsMenuItem()
                .navigateUpToStatisticsScreen()
                .verifyStatisticsScreenInitialState()
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.AppCompatImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers

/**
 * Represents application Settings screen.
 */
class SettingsScreen {

    private val upButton = onView(allOf(
            instanceOf(AppCompatImageButton::class.java),
            withParent(withId(R.id.action_bar))))

    private val notificationsOption = onView(withText("Notifications"))
    private val generalOption = onView(withText("General"))
    private val dataAndSyncOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_header_data_sync),
            isCompletelyDisplayed()
    )

    fun navigateUpToToDoListScreen(): ToDoListScreen {
        upButton.perform(click())
        return ToDoListScreen()
    }

    fun navigateUpToStatisticsScreen(): StatisticsScreen {
        upButton.perform(click())
        return StatisticsScreen()
    }

    fun openNotificationsScreen(): NotificationsScreen {
        notificationsOption.click()
        return NotificationsScreen()
    }

    fun openGeneralScreen(): GeneralScreen {
        generalOption.click()
        return GeneralScreen()
    }

    fun openDataAndSyncScreen(): DataAndSyncScreen {
        onData(anything())
                .inAdapterView(withId(android.R.id.list))
                .atPosition(2)
                .onChildView(dataAndSyncOption)
                .click()
        return DataAndSyncScreen()
    }
}


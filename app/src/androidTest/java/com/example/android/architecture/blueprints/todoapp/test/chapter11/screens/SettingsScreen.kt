package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.support.v7.widget.AppCompatImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

/**
 * Represents application Settings screen.
 */
class SettingsScreen {

    private val upButton = onView(allOf(
            instanceOf(AppCompatImageButton::class.java),
            withParent(withId(R.id.action_bar))))

    private val notificationsOption = onView(ViewMatchers.withText("Notifications"))

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
}


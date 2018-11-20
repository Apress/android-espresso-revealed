package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import androidx.appcompat.widget.AppCompatImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

/**
 * Represents application Settings screen.
 */
class SettingsScreen {

    private val upButton = onView(allOf(
            instanceOf(AppCompatImageButton::class.java),
            withParent(withId(R.id.action_bar))))

    fun navigateUpToToDoListScreen(): ToDoListScreen {
        upButton.perform(click())
        return ToDoListScreen()
    }

    fun navigateUpToStatisticsScreen(): StatisticsScreen {
        upButton.perform(click())
        return StatisticsScreen()
    }
}

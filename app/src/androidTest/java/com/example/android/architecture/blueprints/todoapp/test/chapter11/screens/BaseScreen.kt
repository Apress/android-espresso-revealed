package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.resources.NoAction
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf

/**
 * Base screen that shares common functionality for main application settings
 * like TO-DO list screen and Statistics screen.
 */
open class BaseScreen {

    private val hamburgerButton = onView(allOf(
            instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar))))

    fun openMenu(): MenuDrawerView {
        hamburgerButton.perform(click())
        return MenuDrawerView()
    }

    inner class MenuDrawerView {
        private val todoListMenuItem = onView(allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.list_title)))
        private val statisticsMenuItem = onView(allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.statistics_title)))
        private val settingsMenuItem = onView(allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)))
        private val todoMenuLogo = onView(withId(R.id.headerTodoLogo))
        private val todoMenuText = onView(withId(R.id.headerTodoText))

        fun clickTodoListMenuItem(): ToDoListScreen {
            todoListMenuItem.perform(click())
            return ToDoListScreen()
        }

        fun clickStatisticsMenuItem(): StatisticsScreen {
            statisticsMenuItem.perform(click())
            return StatisticsScreen()
        }

        fun clickSettingsMenuItem(): SettingsScreen {
            settingsMenuItem.perform(click())
            return SettingsScreen()
        }

        fun verifyMenuLayout(): MenuDrawerView {
            todoMenuText.check(matches(allOf(
                    isDisplayed(),
                    withText(R.string.navigation_view_header_title))))
            statisticsMenuItem.check(matches(isDisplayed()))
            todoListMenuItem.check(matches(isDisplayed()))
            return this
        }
    }
}


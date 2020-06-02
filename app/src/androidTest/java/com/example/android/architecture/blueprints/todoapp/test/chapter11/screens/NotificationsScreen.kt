package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click

class NotificationsScreen : BaseScreen() {

    /*
    ELEMENTS
     */

    private val sendNotification = allOf(
            withText("Send notification"),
            isCompletelyDisplayed()
    )

    private val sendNotificationWithDelay = allOf(
            withText("Send notification with delay"),
            isCompletelyDisplayed()
    )

    private val enableNotificationsOption = allOf(
            withId(R.id.title),
            withText(R.string.pref_title_new_message_notifications),
            isCompletelyDisplayed()
    )

    private val notificationSwitch = allOf(
            withId(android.R.id.switch_widget),
            withParent(hasSibling(withChild(withText(R.string.pref_title_new_message_notifications)))),
            isCompletelyDisplayed()
    )

    private val notifyWhenToDoOlderThanOption = allOf(
            withId(android.R.id.title),
            withText(R.string.slider_title),
            isCompletelyDisplayed()
    )
    private val ringtoneOption = allOf(
            withText(R.string.pref_title_ringtone),
            withId(android.R.id.title),
            isCompletelyDisplayed()
    )

    private val vibrateOption = allOf(
            withId(R.id.title),
            withText(R.string.pref_title_vibrate),
            isCompletelyDisplayed()
    )


    /*
    ACTIONS
    */

    fun enableNotificationsToggle(): NotificationsScreen {
        onView(notificationSwitch).click()
        return this;
    }

    /*
    HELPERS
     */

    fun verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications(): NotificationsScreen {
        notifyWhenToDoOlderThanOption.matches(isDisplayed())
        ringtoneOption.matches(isDisplayed())
        vibrateOption.matches(isDisplayed())
        return NotificationsScreen();
    }

}

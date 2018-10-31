package com.example.android.architecture.blueprints.todoapp.test.helpers

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf

object CommonElements {

    private val HAMBURGER_BUTTON = onView(allOf(instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar))))

    @JvmStatic
    fun openDrawer(): ViewInteraction {
        return HAMBURGER_BUTTON.perform(click())
    }
}

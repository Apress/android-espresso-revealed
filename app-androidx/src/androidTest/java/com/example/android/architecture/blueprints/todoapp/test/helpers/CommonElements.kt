package com.example.android.architecture.blueprints.todoapp.test.helpers

import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
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

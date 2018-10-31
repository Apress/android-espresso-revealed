package com.example.android.architecture.blueprints.todoapp.test.chapter15.drawablematchers

import android.graphics.drawable.Drawable
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.junit.Test

/**
 * Demonstrates Drawable matchers usage.
 */
class DrawableMatchersTest : BaseTest() {

    @Test
    fun checkDrawableInMenuDrawer() {
        openDrawer()
        onView(withId(R.id.headerTodoLogo))
                .check(matches(DrawableMatchers()
                        .withImageViewDrawable(getMenuIconDrawable())))
    }

    private fun getMenuIconDrawable(): Drawable? {
        val drawableId = com.example.android.architecture.blueprints.todoapp.mock.test
                .R.drawable.test_logo
        return InstrumentationRegistry.getInstrumentation().context.getDrawable(drawableId)
    }
}

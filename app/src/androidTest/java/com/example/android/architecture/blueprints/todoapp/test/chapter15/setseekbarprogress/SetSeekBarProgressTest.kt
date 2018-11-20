package com.example.android.architecture.blueprints.todoapp.test.chapter15.setseekbarprogress

import android.widget.SeekBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter15.setseekbarprogress.SeekBarViewActions.setProgress
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

/**
 * Testing [SeekBar] change.
 */
class SetSeekBarProgressTest: BaseTest() {

    @Test
    fun sliderActionSample() {
        openDrawer()
        onView(allOf(withId(R.id.design_menu_item_text),
                withText(R.string.statistics_title))).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.simpleSeekBar)).perform(setProgress(10))
        onView(withId(R.id.seekBarTextView)).check(matches(withText("Progress: 10")))
    }
}

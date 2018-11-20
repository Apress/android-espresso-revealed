package com.example.android.architecture.blueprints.todoapp.test.chapter15.setseekbarprogress

import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher

/**
 * [ViewAction]s that operate on [SeekBar].
 */
object SeekBarViewActions {

    /**
     * Sets progress of a [SeekBar].
     *
     * @param value - the progress value between min and max [SeekBar] value
     */
    fun setProgress(value: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(SeekBar::class.java)
            }

            override fun getDescription(): String {
                return ("Set SeekBar progress to $value.")
            }

            override fun perform(uiController: UiController, view: View) {
                val seekBar = view as SeekBar
                seekBar.progress = value
            }
        }
    }
}

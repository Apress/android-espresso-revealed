package com.example.android.architecture.blueprints.todoapp.test.chapter15.drawablematchers

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Contains [TextView] and [ImageView] [Drawable] matchers.
 */
class DrawableMatchers {

    /**
     * View matcher that matches an [TextView] with specific [Drawable].
     *
     * @param drawableToMatch - drawable to compare to.
     * @return [BoundedMatcher]
     */
    fun withTextViewDrawable(drawableToMatch: Drawable): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("Drawable in TextView $drawableToMatch")
            }

            override fun matchesSafely(editTextField: TextView): Boolean {
                val drawables = editTextField.compoundDrawables
                val drawable = drawables[2]

                return isSameBitmap(drawableToMatch, drawable)
            }
        }
    }

    /**
     * View matcher that matches an [ImageView] with specific [Drawable].
     *
     * @param expectedDrawable - drawable to compare to.
     * @return [BoundedMatcher]
     */
    fun withImageViewDrawable(expectedDrawable: Drawable?): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("Drawable in ImageView $expectedDrawable")
            }

            public override fun matchesSafely(imageView: ImageView) =
                    isSameBitmap(imageView.drawable, expectedDrawable)
        }
    }

    /**
     * Compares two [Drawable]s.
     *
     * @param drawable - drawable to inspect.
     * @param expectedDrawable - drawable to compare to.
     * @return true if the same or false if not.
     */
    fun isSameBitmap(drawable: Drawable?, expectedDrawable: Drawable?): Boolean {
        var localDrawable = drawable
        var localExpectedDrawable = expectedDrawable

        // Return if null.
        if (localDrawable == null || localExpectedDrawable == null) {
            return false
        }

        // StateListDrawable lets you assign a number of graphic images to a single Drawable
        // and swap out the visible item by a string ID value.
        if (localDrawable is StateListDrawable && localExpectedDrawable is StateListDrawable) {
            localDrawable = localDrawable.current
            localExpectedDrawable = localExpectedDrawable.current
        }

        // BitmapDrawable - a Drawable that wraps a bitmap and can be tiled, stretched, or aligned.
        if (localDrawable is BitmapDrawable) {
            val bitmap = localDrawable.bitmap
            val otherBitmap = (localExpectedDrawable as BitmapDrawable).bitmap
            return bitmap.sameAs(otherBitmap)
        }
        return false
    }
}

/*
 * Modified by Denys Zelenchuk on 14.7.2018.
 */

/*
 * Modified by Denys Zelenchuk on 13.7.2018.
 */

/*
 * Modified by Denys Zelenchuk on 13.7.2018.
 */

/*
 * Modified by Denys Zelenchuk on 14.6.2018.
 */

/*
 * Modified by Denys Zelenchuk on 12.6.2018.
 */

package com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.test.espresso.intent.Checks;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class DrawableMatchers {

    public static Matcher<View> withTextViewDrawable(final Drawable drawableToMatch) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with textview drawable" + drawableToMatch);
            }

            @Override
            protected boolean matchesSafely(TextView editTextField) {
                //getCompoundDrawables() returns drawables array for the start, top, end, and bottom borders.
                Drawable[] drawables = editTextField.getCompoundDrawables();
                //we are interested in start border image - shown on the left side - 1st element in the array
                Drawable drawable = drawables[0];
                Checks.checkNotNull(drawable, "Compound drawable should be not null");
                return isSameBitmap(drawableToMatch, drawable);
            }
        };
    }

    public static Matcher<View> withImageViewDrawable(final Drawable expectedDrawable) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has image drawable resource " + expectedDrawable);
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return isSameBitmap(imageView.getDrawable(), expectedDrawable);
            }
        };
    }

    private static boolean isSameBitmap(@Nullable Drawable drawable, Drawable expectedDrawable) {
        if (drawable == null || expectedDrawable == null) {
            return false;
        }
        if (drawable instanceof StateListDrawable && expectedDrawable instanceof StateListDrawable) {
            drawable = drawable.getCurrent();
            expectedDrawable = expectedDrawable.getCurrent();
        }
        if (drawable instanceof BitmapDrawable) {

            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap otherBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
            return bitmap.sameAs(otherBitmap);
        }
        return false;
    }
}

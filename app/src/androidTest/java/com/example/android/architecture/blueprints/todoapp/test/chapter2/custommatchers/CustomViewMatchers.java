package com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;

/**
 * Shows custom {@link ViewMatchers} samples.
 */
public class CustomViewMatchers {

    public static Matcher<View> todoWithTitle(final String expectedTitle) {
        return new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {

            @Override
            protected boolean matchesSafely(LinearLayout linearLayout) {
                TextView textView = linearLayout.findViewById(R.id.todo_title);
                return expectedTitle.equals(textView.getText().toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with TO-DO title: " + expectedTitle);
            }
        };
    }

    /**
     * Matches EditText hint by specific text color.
     * @param expectedColor - expected color code
     * @return {@link Matcher<View>}
     */
    public static Matcher<View> withHintColor(final int expectedColor) {
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            protected boolean matchesSafely(EditText editText) {
                return expectedColor == editText.getCurrentHintTextColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected with hint color: " + expectedColor);
            }
        };
    }
}

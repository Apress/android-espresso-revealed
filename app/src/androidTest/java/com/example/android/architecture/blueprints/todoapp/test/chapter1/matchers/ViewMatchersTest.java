package com.example.android.architecture.blueprints.todoapp.test.chapter1.matchers;

import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;

import com.example.android.architecture.blueprints.todoapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.LayoutMatchers.hasEllipsizedText;
import static androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText;
import static androidx.test.espresso.matcher.PreferenceMatchers.withKey;
import static androidx.test.espresso.matcher.PreferenceMatchers.withSummaryText;
import static androidx.test.espresso.matcher.PreferenceMatchers.withTitle;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.isTouchable;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasImeAction;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * Lists all ViewMatchers. ViewMatchers here are without functional load.
 * This is done for demonstration purposes.
 */
@RunWith(AndroidJUnit4.class)
public class ViewMatchersTest {

    @Test
    public void userProperties() {
        onView(withId(R.id.fab_add_task));
        onView(withText("All TO-DOs"));
        onView(withContentDescription(R.string.menu_filter));
        onView(hasContentDescription());
        onView(withHint(R.string.name_hint));
    }

    @Test
    public void uiProperties() {
        onView(isDisplayed());
        onView(isEnabled());
        onView(isChecked());
        onView(isSelected());
    }

    @Test
    public void objectMatcher() {
        onView(not(isChecked()));
        onView(allOf(withText("item 1"), isChecked()));
    }

    @Test
    public void hierarchy() {
        onView(withParent(withId(R.id.todo_item)));
        onView(withChild(withText("item 2")));
        onView(isDescendantOfA(withId(R.id.todo_item)));
        onView(hasDescendant(isChecked()))
                .check(matches(isDisplayed()))
                .check(matches(isFocusable()));
        onView(hasSibling(withContentDescription(R.string.menu_filter)));
    }

    @Test
    public void input() {
        onView(supportsInputMethods());
        onView(hasImeAction(EditorInfo.IME_ACTION_SEND));
    }

    @Test
    public void classMatchers() {
        onView(isAssignableFrom(CheckBox.class));
        onView(withClassName(is(FloatingActionButton.class.getCanonicalName())));
    }

    @Test
    public void rootMatchers() {
        onView(isFocusable());
        onView(withText(R.string.name_hint)).inRoot(isTouchable());
        onView(withText(R.string.name_hint)).inRoot(isDialog());
        onView(withText(R.string.name_hint)).inRoot(isPlatformPopup());
    }

    @Test
    public void preferenceMatchers() {
        onData(withSummaryText("3 days"));
        onData(withTitle(R.string.pref_title_send_notifications));
        onData(withKey("example_switch"));
        onView(isEnabled());
    }

    @Test
    public void layoutMatchers() {
        onView(hasEllipsizedText());
        onView(hasMultilineText());
    }
}

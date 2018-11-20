package com.example.android.architecture.blueprints.todoapp.test.chapter1.datainteraction;

import android.preference.PreferenceActivity;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.PreferenceMatchers.withKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * DataInteraction samples.
 */
public class DataInteractionsTest extends BaseTest {

    @Test
    public void dataInteractionSample() {
        openDrawer();
        onView(allOf(withId(R.id.design_menu_item_text),
                withText(R.string.settings_title))).perform(click());

        // Start of the flow as shown on Figure 1-8.
        onData(instanceOf(PreferenceActivity.Header.class))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .onChildView(withId(android.R.id.title))
                .check(matches(withText("General")))
                .perform(click());
        onData(withKey("email_edit_text"))
                /*we have to point explicitly what is the parent of of the General prefs list
                because there are two {@ListView}s with the same id - android.R.id.list in the hierarchy*/
                .inAdapterView(allOf(withId(android.R.id.list), withParent(withId(android.R.id.list_container))))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(android.R.id.edit)).perform(replaceText("sample@ema.il"));
        onView(withId(android.R.id.button1)).perform(click());

        // Verify new email is shown.
        onData(withKey("email_edit_text"))
                .inAdapterView(allOf(withId(android.R.id.list), withParent(withId(android.R.id.list_container))))
                .onChildView(withId(android.R.id.summary))
                .check(matches(withText("sample@ema.il")));
    }
}

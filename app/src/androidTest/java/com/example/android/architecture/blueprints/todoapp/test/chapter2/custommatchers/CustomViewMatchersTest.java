package com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers;

import android.graphics.Color;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CustomViewMatchersTest extends BaseTest {

    @Test
    public void addsNewToDoError() {
        // adding new TO-DO
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.fab_edit_task_done)).perform(click());
        onView(withId(R.id.add_task_title))
                .check(matches(hasErrorText("Title cannot be empty!")))
                .check(matches(CustomViewMatchers.withHintColor(Color.RED)));
    }
}

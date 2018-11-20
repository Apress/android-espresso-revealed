package com.example.android.architecture.blueprints.todoapp.test.chapter1.actions;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

/**
 * Demonstrates ViewActions usage.
 */
public class ViewActionsTest extends BaseTest {

    private String toDoTitle = "";
    private String toDoDescription = "";

    @Before
    public void setUp() {
        super.setUp();
        toDoTitle = TestData.getToDoTitle();
        toDoDescription = TestData.getToDoDescription();
    }

    @Test
    public void addsNewToDo() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Verify new TO-DO with title is shown in the TO-DO list.
        onView(withText(toDoTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void checksToDoStateChange() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Mark TO-DO as completed.
        onView(withId(R.id.todo_complete)).perform(click());

        // Filter out completed TO-DO.
        onView(withId(R.id.menu_filter)).perform(click());
        onView(allOf(withId(R.id.title), withText("Active"))).perform(click());
        onView(withId(R.id.todo_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.menu_filter)).perform(click());
        onView(allOf(withId(R.id.title), withText("Completed"))).perform(click());
        onView(withId(R.id.todo_title))
                .check(matches(allOf(withText(toDoTitle), isDisplayed())));
    }

    @Test
    public void editsToDo() {
        String editedToDoTitle = "Edited "+ toDoTitle;
        String editedToDoDescription = "Edited "+ toDoDescription;

        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Edit TO-DO.
        onView(withText(toDoTitle)).perform(click());
        onView(withId(R.id.fab_edit_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(replaceText(editedToDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(replaceText(editedToDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Verify edited TO-DO is shown.
        onView(withText(editedToDoTitle)).check(matches(isDisplayed()));
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter1.actions;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

/**
 * Demonstrates ViewActions usage.
 */
public class ViewActionsTest extends BaseTest {

    private String toDoTitle = "";
    private String toDoDescription = "";

    @Before
    public void setUp() throws Exception {
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
        String editedToDoTitle = "Edited " + toDoTitle;
        String editedToDoDescription = "Edited " + toDoDescription;

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

    // //Exercise 2 page 30
    @Test
    public void completeNewToDo() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Mark TO-DO as completed.
        onView(withId(R.id.todo_complete)).perform(click());

        // Verify that TO-DO checkbox is completed.
        onView(withId(R.id.todo_complete)).check(matches(isChecked()));
    }

    @Test
    public void deleteNewToDo() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Open created TO-DO.
        onView(withText(toDoTitle)).perform(click());

        // Delete created TO-DO.
        onView(withId(R.id.menu_delete)).perform(click());

        // Verify that All TO-DOs list is empty.
        onView(allOf(withId(R.id.noTasksMain), withText("You have no TO-DOs!")))
                .check(matches(isDisplayed()));
        onView(withId(R.id.noTasksIcon)).check(matches(isDisplayed()));
    }
}

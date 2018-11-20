package com.example.android.architecture.blueprints.todoapp.test.chapter1.actions;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions;

import org.junit.Test;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomClickAction.clickElementWithVisibility;
import static com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions.ClickTodoCheckBoxWithTitleViewAction.clickTodoCheckBoxWithTitle;
import static com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement;
import static com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElementIsGone;

/**
 * Demonstrates [RecyclerView] actions usage.
 */
public class RecyclerViewActionsTest extends BaseTest {

    private ViewInteraction todoSavedSnackbar = onView(withText(R.string.successfully_saved_task_message));

    @Test
    public void addNewToDos() throws Exception {
        generateToDos(12);
        onView(withId(R.id.tasks_list))
                .perform(actionOnItemAtPosition(10, scrollTo()));
        onView(withId(R.id.tasks_list))
                .perform(scrollToPosition(1));
        onView(withId(R.id.tasks_list))
                .perform(scrollToPosition(11));
        onView(withId(R.id.tasks_list))
                .perform(actionOnItemAtPosition(11, click()));
        Espresso.pressBack();
        onView(withId(R.id.tasks_list))
                .perform(scrollToPosition(2));
    }

    @Test
    public void addNewToDosChained() throws Exception {
        generateToDos(12);
        onView(withId(R.id.tasks_list))
                .perform(actionOnItemAtPosition(10, scrollTo()))
                .perform(scrollToPosition(1))
                .perform(scrollToPosition(11))
                .perform(actionOnItemAtPosition(11, click()));
        Espresso.pressBack();
        onView(withId(R.id.tasks_list)).perform(scrollToPosition(2));
    }

    @Test
    public void completeToDo() throws Exception {
        generateToDos(10);
        onView(withId(R.id.tasks_list)).perform(clickTodoCheckBoxWithTitle("item 2"));
        onView(withId(R.id.tasks_list))
                .perform(CustomRecyclerViewActions.ScrollToLastHolder.scrollToLastHolder());
    }

    /**
     * Helper function that adds needed TO-DOs amount.
     * @param count - amount of TO-DOs to add.
     * @throws Exception
     */
    private void generateToDos(int count) throws Exception {
        for (int i = 1; i <= count; i++) {
            waitForElementIsGone(todoSavedSnackbar, 3000);
            // Adding new TO-DO.
            onView(withId(R.id.fab_add_task)).perform(clickElementWithVisibility(20));
            onView(withId(R.id.add_task_title))
                    .perform(typeText("item " + i), closeSoftKeyboard());
            onView(withId(R.id.fab_edit_task_done)).perform(click());
            waitForElement(todoSavedSnackbar, 3000);
        }
        waitForElementIsGone(todoSavedSnackbar, 3000);
    }
}

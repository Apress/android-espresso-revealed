package com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

/**
 * Demonstrates custom {@link RecyclerView} actions implementation.
 */
public interface CustomRecyclerViewActions extends ViewAction {

    /**
     * RecyclerView action that performs click on TO-DO checkbox based on its title.
     */
    class ClickTodoCheckBoxWithTitleViewAction implements CustomRecyclerViewActions {

        private String toDoTitle;

        public ClickTodoCheckBoxWithTitleViewAction(String toDoTitle) {
            this.toDoTitle = toDoTitle;
        }

        public static ViewAction clickTodoCheckBoxWithTitle(final String toDoTitle) {
            return actionWithAssertions(new ClickTodoCheckBoxWithTitleViewAction(toDoTitle));
        }

        @Override
        public Matcher<View> getConstraints() {
            return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
        }

        @Override
        public String getDescription() {
            return "Complete ToDo with title: \"" + toDoTitle + "\" by clicking its checkbox.";
        }

        @Override
        public void perform(UiController uiController, View view) {
            try {
                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter instanceof TasksFragment.TasksAdapter) {
                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        View taskItemView = recyclerView.getLayoutManager().findViewByPosition(i);
                        if (taskItemView != null) {
                            TextView textView = taskItemView.findViewById(R.id.todo_title);
                            if (textView != null && textView.getText() != null) {
                                if (textView.getText().toString().equals(toDoTitle)) {
                                    CheckBox completeCheckBox = taskItemView.findViewById(R.id.todo_complete);
                                    completeCheckBox.performClick();
                                }
                            } else {
                                throw new RuntimeException(
                                        "Unable to find TO-DO item with title \"" + toDoTitle + "\"");
                            }
                        }
                    }
                }
                uiController.loopMainThreadForAtLeast(ViewConfiguration.getTapTimeout());
            } catch (RuntimeException e) {
                throw new PerformException.Builder().withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view)).withCause(e).build();
            }
        }
    }

    /**
     * ViewAction that scrolls to the last item in RecyclerView.
     */
    class ScrollToLastHolder implements CustomRecyclerViewActions {

        public static ViewAction scrollToLastHolder() {
            return actionWithAssertions(new ScrollToLastHolder());
        }

        @SuppressWarnings("unchecked")
        @Override
        public Matcher<View> getConstraints() {
            return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
        }

        @Override
        public void perform(UiController uiController, View view) {
            RecyclerView recyclerView = (RecyclerView) view;
            int itemCount = recyclerView.getAdapter().getItemCount();
            try {
                recyclerView.scrollToPosition(itemCount - 1);
                uiController.loopMainThreadUntilIdle();
            } catch (RuntimeException e) {
                throw new PerformException.Builder().withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view)).withCause(e).build();
            }
        }

        @Override
        public String getDescription() {
            return "scroll to last holder in RecyclerView";
        }
    }
}

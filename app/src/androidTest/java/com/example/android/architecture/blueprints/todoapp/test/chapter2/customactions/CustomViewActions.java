package com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions;

import android.view.View;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment;
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem;

import org.hamcrest.Matcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static junit.framework.Assert.assertFalse;


public class CustomViewActions {

    /**
     * ViewAction which asserts that TO-DO item is not in side the {@link RecyclerView}
     * adapter.
     * @param taskItem - TO-DO object.
     * @return {@link ViewAction} view action.
     */
    public static ViewAction verifyTaskNotInTheList(final TodoItem taskItem) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(RecyclerView.class);
            }

            @Override
            public String getDescription() {
                return "Expected TO-DO with title "
                        + taskItem.getTitle()
                        + " would not present in the list but it was.";
            }

            @Override
            public void perform(UiController uiController, View view) {

                boolean isExist = false;

                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = recyclerView.getAdapter();

                if (adapter instanceof TasksFragment.TasksAdapter) {
                    int amount = adapter.getItemCount();
                    for (int i = 0; i < amount; i++) {
                        View taskItemView = recyclerView.getLayoutManager().findViewByPosition(i);
                        TextView textView = taskItemView.findViewById(R.id.title);
                        if (textView != null && textView.getText() != null) {
                            if (textView.getText().toString().equals(taskItem.getTitle())) {
                                isExist = true;
                            }
                        }
                    }
                }
                assertFalse("Task with title: "
                        + taskItem.getTitle()
                        + " is present in the list but it shouldn't", isExist);
            }
        };
    }
}

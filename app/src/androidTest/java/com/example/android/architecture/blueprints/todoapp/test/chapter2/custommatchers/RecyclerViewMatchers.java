package com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers;

import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment;
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;

import static com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment.TasksAdapter;

/**
 * RecyclerView matchers that match TO-DO items in items list.
 */
public class RecyclerViewMatchers {

    public static Matcher<RecyclerView.ViewHolder> withTitle(final String taskTitle) {
        Checks.checkNotNull(taskTitle);

        return new BoundedMatcher<RecyclerView.ViewHolder, TasksAdapter.ViewHolder>(
                TasksAdapter.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(TasksAdapter.ViewHolder holder) {
                final String holderTaskTitle = holder.getHolderTask().getTitle();
                return taskTitle.equals(holderTaskTitle);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with task title: " + taskTitle);
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> withTask(final TodoItem taskItem) {
        Checks.checkNotNull(taskItem);

        return new BoundedMatcher<RecyclerView.ViewHolder, TasksFragment.TasksAdapter.ViewHolder>(
                TasksAdapter.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(TasksAdapter.ViewHolder holder) {
                final String holderTaskTitle = holder.getHolderTask().getTitle();
                final String holderTaskDesc = holder.getHolderTask().getDescription();
                return taskItem.getTitle().equals(holderTaskTitle)
                        && taskItem.getDescription().equals(holderTaskDesc);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("task with title: " + taskItem.getTitle()
                        + " and description: " + taskItem.getDescription());
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> withTaskTitleFromTextView(final String taskTitle) {
        Checks.checkNotNull(taskTitle);

        return new BoundedMatcher<RecyclerView.ViewHolder, TasksFragment.TasksAdapter.ViewHolder>(
                TasksAdapter.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(TasksAdapter.ViewHolder holder) {
                final TextView titleTextView = (TextView) holder.itemView.findViewById(R.id.title);
                return taskTitle.equals(titleTextView.getText().toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with task title: " + taskTitle);
            }
        };
    }
}

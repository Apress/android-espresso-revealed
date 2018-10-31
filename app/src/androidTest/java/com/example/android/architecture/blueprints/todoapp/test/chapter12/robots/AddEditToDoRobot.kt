package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R

fun addEditToDo(func: AddEditToDoRobot.() -> Unit) = AddEditToDoRobot().apply { func() }
class AddEditToDoRobot {

    fun title(title: String) {
        onView(withId(R.id.add_task_title))
                .perform(typeText(title), closeSoftKeyboard())
    }

    fun description(description: String) {
        onView(withId(R.id.add_task_description))
                .perform(typeText(description), closeSoftKeyboard())
    }

    fun done() {
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }

    infix fun done(func: ToDoListRobot.() -> Unit): ToDoListRobot {
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        return ToDoListRobot().apply { func() }
    }

    infix fun doneInnerClasses(func: TasksListRobotWithInnerClasses.() -> Unit): TasksListRobotWithInnerClasses {
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        return TasksListRobotWithInnerClasses().apply { func() }
    }
}

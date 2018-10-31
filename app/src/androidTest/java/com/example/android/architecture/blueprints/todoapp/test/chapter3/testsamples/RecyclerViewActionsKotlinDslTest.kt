package com.example.android.architecture.blueprints.todoapp.test.chapter3.testsamples

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.android.architecture.blueprints.todoapp.R.string.successfully_saved_task_message
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomClickAction.clickElementWithVisibility
import com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers.RecyclerViewMatchers.withTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter3.*
import org.junit.Test

class RecyclerViewActionsKotlinDslTest : BaseTest() {

    //ViewInteractions used in tests
    private val todoSavedSnackbar = viewWithText(successfully_saved_task_message)
    private val todoList = viewWithId(tasks_list)
    private val addFab = viewWithId(fab_add_task)
    private val doneFab = viewWithId(fab_edit_task_done)
    private val todoTitle = viewWithId(add_task_title)

    @Test
    fun addNewToDosChained() {
        generateToDos(12)
        todoList
                .actionAtPosition(10, scrollTo())
                .scrollToPosition(1)
                .scrollToPosition(11)
                .actionAtPosition(11, click())
        Espresso.pressBack()
        todoList
                .scrollToPosition(2)
                .actionOnItem(withTitle("item 2"), click())
    }

    @Test
    fun completeToDo() {
        generateToDos(10)
        todoList
                .clickTodoCheckBoxWithTitle("item 2")
                .scrollToLastItem()
    }

    private fun generateToDos(count: Int) {
        for (i in 1..count) {
            todoSavedSnackbar.waitForGone()
            // adding new TO-DO
            addFab.perform(clickElementWithVisibility(20))
            todoTitle.type("item $i").closeKeyboard()
            doneFab.click()
            todoSavedSnackbar.waitForGone()
        }
        todoSavedSnackbar.waitForGone()
    }
}

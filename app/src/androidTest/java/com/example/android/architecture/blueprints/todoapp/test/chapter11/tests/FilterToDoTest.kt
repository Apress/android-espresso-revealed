package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import org.junit.Test

/**
 * Validates TO-DOs filtering flows using Screen Object Pattern.
 */
class FilterToDoTest : BaseTest() {

    @Test
    fun showsActiveToDos() {
        ToDoListScreen()
                .clickAddFabButton()
                .addNewToDo(taskItem1)
                .completeTask(taskItem1)
                .clickAddFabButton()
                .addNewToDo(taskItem2)
                .showActiveTasks()
                .verifyToDoIsDisplayed(taskItem2)
                .verifyToDoItemNotShown(taskItem1)
    }

    @Test
    fun showsCompletedToDos() {
        ToDoListScreen()
                .clickAddFabButton()
                .addNewToDo(taskItem1)
                .completeTask(taskItem1)
                .clickAddFabButton()
                .addNewToDo(taskItem2)
                .showCompletedTasks()
                .verifyToDoIsDisplayed(taskItem1)
                .verifyToDoItemNotShown(taskItem2)
    }

    @Test
    fun showsAllToDos() {
        ToDoListScreen()
                .clickAddFabButton()
                .addNewToDo(taskItem1)
                .completeTask(taskItem1)
                .clickAddFabButton()
                .addNewToDo(taskItem2)
                .showAllTasks()
                .verifyToDoIsDisplayed(taskItem1)
                .verifyToDoIsDisplayed(taskItem2)
    }

    companion object {
        private var taskItem1 = TodoItem()
        private var taskItem2 = TodoItem()
    }
}

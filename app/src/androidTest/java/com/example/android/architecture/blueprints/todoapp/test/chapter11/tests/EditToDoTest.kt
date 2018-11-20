package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import org.junit.Test

/**
 * Validates TO-DOs editing flows using Screen Object Pattern.
 */
class EditToDoTest : BaseTest() {

    @Test
    fun verifiesTaskEditing() {
        ToDoListScreen()
                .clickAddFabButton()
                .addNewToDo(taskItem1)
                .verifyToDoIsDisplayed(taskItem1)
                .openTaskDetails(taskItem1.title)
                .clickEditToDoFabButton()
                .updateToDo(taskItem2)
                .verifyToDoItemNotShown(taskItem1)
                .verifyToDoIsDisplayed(taskItem2)
    }

    companion object {
        private var taskItem1 = TodoItem()
        private var taskItem2 = TodoItem()
    }
}

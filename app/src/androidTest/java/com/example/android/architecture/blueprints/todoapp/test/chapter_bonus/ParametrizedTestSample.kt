package com.example.android.architecture.blueprints.todoapp.test.chapter_bonus

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.ToDoListScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

@RunWith(value = Parameterized::class)
class ParametrizedTestSample : BaseTest() {

    @Test
    fun addMultipleTasks() {
        ToDoListScreen()
                .clickAddFabButton()
                .addNewToDo(mTaskItem)
                .verifyToDoIsDisplayed(mTaskItem)
    }

    companion object {

        private val mTaskItemsList = ArrayList<TodoItem>()

        private fun createListOfTasks() {
            for (i in 0..9) {
                mTaskItemsList.add(TodoItem())
            }
        }

        @Parameterized.Parameter
        var mTaskItem = TodoItem.new

        @Parameterized.Parameters
        fun data(): Array<Any> {
            createListOfTasks()
            return arrayOf(mTaskItemsList[0], mTaskItemsList[1], mTaskItemsList[2], mTaskItemsList[3], mTaskItemsList[4], mTaskItemsList[5], mTaskItemsList[6], mTaskItemsList[7], mTaskItemsList[8], mTaskItemsList[9])
        }
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter12

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import com.example.android.architecture.blueprints.todoapp.test.chapter12.robots.*
import org.junit.Before
import org.junit.Test

class RobotsTest : BaseTest() {

    private var toDoTitle = ""
    private var toDoDescription = ""

    @Before
    override fun setUp() {
        super.setUp()
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun robotChecksToDoStateChangeBuilder() {
        // TO-DO list screen.
        BuilderToDoListRobot()
                .checkDefaultLayout()
                .addToDo()
        // Add / Edit TO-DO screen.
        BuilderAddEditToDoRobot()
                .title(toDoTitle)
                .description(toDoDescription)
                .done()
        // TO-DO list screen.
        BuilderToDoListRobot()
                .verifyToDoShown(toDoTitle)
                .markCompleted(toDoTitle)
                .showActive()
                .verifyToDoNotShown(toDoTitle)
                .showCompleted()
                .verifyToDoShown(toDoTitle)
    }

    @Test
    fun robotChecksToDoStateChangeRobotsSeparation() {
        toDoList {
            checkDefaultLayout()
            addToDo()
        }
        addEditToDo {
            title(toDoTitle)
            description(toDoDescription)
            done()
        }
        toDoList {
            verifyToDoShown(toDoTitle)
            markCompleted(toDoTitle)
            showActive()
            verifyToDoNotShown(toDoTitle)
            showCompleted()
            verifyToDoShown(toDoTitle)
        }
    }

    @Test
    fun robotChecksToDoStateChange() {
        toDoList {
            checkDefaultLayout()
        }.addToDo {
            title(toDoTitle)
            description(toDoDescription)
        }.done {
            verifyToDoShown(toDoTitle)
            markCompleted(toDoTitle)
            showActive()
            verifyToDoNotShown(toDoTitle)
            showCompleted()
            verifyToDoShown(toDoTitle)
        }
    }

    @Test
    fun robotChecksToDoStateChangeInfix() {
        toDoList {
            checkDefaultLayout()
        } addToDo {
            title(toDoTitle)
            description(toDoDescription)
        } done {
            verifyToDoShown(toDoTitle)
            markCompleted(toDoTitle)
            showActive()
            verifyToDoNotShown(toDoTitle)
            showCompleted()
            verifyToDoShown(toDoTitle)
        }
    }

    @Test
    fun robotChecksToDoStateChangeInnerClasses() {
        tasksListInnerClasses {
            checkEmptyState()
        } addTask {
            title(toDoTitle)
            description(toDoDescription)
        } doneInnerClasses {
            verifyTaskShown(toDoTitle)
            markCompleted(toDoTitle)
            toDoListFilter { showActive() }
            verifyTaskNotShown(toDoTitle)
            toDoListFilter { showCompleted() }
            verifyTaskShown(toDoTitle)
        }
    }
}

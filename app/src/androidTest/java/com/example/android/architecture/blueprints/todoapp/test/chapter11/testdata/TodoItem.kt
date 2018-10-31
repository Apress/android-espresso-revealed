package com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata

class TodoItem {

    val title: String = "title ${System.currentTimeMillis()}"
    val description: String = "description ${System.currentTimeMillis()}"

    companion object {
        val new: TodoItem get() = TodoItem()
    }
}

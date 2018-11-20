package com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata

import java.util.*

class TodoItem {

    var title = "title ${System.currentTimeMillis()}${Random().nextInt(1000)}"
    var description = "description ${System.currentTimeMillis()}${Random().nextInt(1000)}"
}

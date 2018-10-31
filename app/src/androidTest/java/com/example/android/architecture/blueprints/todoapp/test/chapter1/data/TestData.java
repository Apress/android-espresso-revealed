package com.example.android.architecture.blueprints.todoapp.test.chapter1.data;

public class TestData {

    public static String getToDoTitle() {
        return "item " + System.currentTimeMillis();
    }

    public static String getToDoDescription() {
        return "description " + System.currentTimeMillis();
    }
}

package com.example.android.architecture.blueprints.todoapp.test.chapter15.testsuite

import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.AddToDoTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.EditToDoTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.FilterToDoTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Organising test classes into test suite.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
        AddToDoTest::class,
        EditToDoTest::class,
        FilterToDoTest::class)
class TestSuiteSample

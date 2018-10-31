package com.example.android.architecture.blueprints.todoapp.test.chapter_bonus

import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.AddToDoTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.EditToDoTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.tests.FilterToDoTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(AddToDoTest::class, EditToDoTest::class, FilterToDoTest::class)
class TestSuiteSample
/**
 * Smoke test suite.
 */

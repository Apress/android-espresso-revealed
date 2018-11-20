package com.example.android.architecture.blueprints.todoapp.test.chapter2.customfailurehandler;

import android.content.Context;
import android.view.View;

import org.hamcrest.Matcher;

import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.base.DefaultFailureHandler;

/**
 * CustomFailureHandler sample.
 */
public class CustomFailureHandler implements FailureHandler {

    private final FailureHandler delegate;

    public CustomFailureHandler(Context targetContext) {
        delegate = new DefaultFailureHandler(targetContext);
    }

    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        try {
            delegate.handle(error, viewMatcher);
        } catch (NoMatchingViewException e) {
            // For example done device dump, take screenshot, etc.
            throw e;
        }
    }
}

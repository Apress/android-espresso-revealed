package com.example.android.architecture.blueprints.todoapp.test.chapter2.screenshotwatcher;

import android.graphics.Bitmap;
import android.support.test.runner.screenshot.ScreenCapture;
import android.support.test.runner.screenshot.Screenshot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

/**
 * The rule which captures screenshot on test failure.
 */
public class ScreenshotWatcher extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description desc) {
        try {
            captureScreenshot(desc.getMethodName());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Takes screenshot and saves it in sdcard/Pictures/screenshots location.
     * Each screenshot will have name containing unique uuid.
     *
     * @param name screenshot name
     * @throws IOException if there is an IOException
     */
    private void captureScreenshot(final String name) throws IOException {
        ScreenCapture capture = Screenshot.capture();
        capture.setFormat(Bitmap.CompressFormat.PNG);
        capture.setName(name);
        capture.process();
    }
}

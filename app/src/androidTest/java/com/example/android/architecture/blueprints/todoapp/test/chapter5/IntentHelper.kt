package com.example.android.architecture.blueprints.todoapp.test.chapter5

import android.app.Activity
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import java.io.File
import java.io.FileOutputStream

object IntentHelper {

    /**
     *  Creates new activity result from an image stored in test application drawable.
     *  See {@link Activity#setResult} for more information about the result.
     */
    fun createImageResultFromDrawable(drawable: Int): Instrumentation.ActivityResult {
        val resultIntent = Intent()
        val testResources = InstrumentationRegistry.getContext().resources

        // Build a stubbed result from drawable image.
        resultIntent.data = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://${testResources.getResourcePackageName(drawable)}"
                + "/${testResources.getResourceTypeName(drawable)}"
                + "/${testResources.getResourceEntryName(drawable)}")
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent)
    }

    /**
     *  Creates new activity result from an image stored in test application assets.
     *  See {@link Activity#setResult} for more information about the result.
     */
    fun createImageResultFromAssets(imageName: String): Instrumentation.ActivityResult {
        val resultIntent = Intent()

        // Declare variables for test and application context.
        val testContext = InstrumentationRegistry.getContext()
        val appContext = InstrumentationRegistry.getTargetContext()
        /**
         * For some reason the following exception is thrown during test run if we use testContext:
         * java.io.FileNotFoundException: /data/user/0/com.example.android.architecture.blueprints.todoapp.mock.test/cache/todo_image_temp.png (Permission denied).
         * The guess is that system limits access to the test application assets or cache during instrumentation test run.
         */
        val file = File("${appContext.cacheDir}/todo_image_temp.png")

        // Read file from test assets and done it into main application cache todo_image_temp.png.
        if (!file.exists()) {
            try {
                val inputStream = testContext.assets.open(imageName)
                val fileOutputStream = FileOutputStream(file)
                val size = inputStream.available()
                val buffer = ByteArray(size)

                inputStream.read(buffer)
                inputStream.close()

                fileOutputStream.write(buffer)
                fileOutputStream.close()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        // Build a stubbed result from temp file.
        resultIntent.data = Uri.fromFile(file)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent)
    }
}

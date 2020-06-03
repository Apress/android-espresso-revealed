package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.Matchers.allOf

class GeneralScreen {

    /*
    ELEMENTS
     */

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val exampleEmail = "exampleEmail@gmail.com"
    private val emailToShareOption = allOf(
            withId(android.R.id.title),
            withText("Email to share TO-DO list"),
            isCompletelyDisplayed())
    private val cancelButton = By.res("android:id/button2")
    private val okButton = By.res("android:id/button1")
    private val typeEmailTextField = By.res("android:id/edit")



    /*
    ACTIONS
     */

    fun tapOnEmailToShareOption(): GeneralScreen {
        onView(emailToShareOption).click()
        return this
    }

    fun cancelEmailToShareDialog(): GeneralScreen{
       uiDevice.findObject(cancelButton).click()
        return this
    }

    fun addEmailAddressToShare(): GeneralScreen {
        uiDevice.findObject(typeEmailTextField)
                .click()
        uiDevice.findObject(typeEmailTextField)
                .text = exampleEmail
        uiDevice.findObject(okButton).click()
        return this
    }

    fun editEmailAdressToShare(): GeneralScreen {
        onView(emailToShareOption).click()
        return this
    }

    /*
    HELPERS
    */

    fun isGeneralScreenDisplayed(): Boolean {
        return try {
            onView(emailToShareOption).check(matches(isDisplayed()))
            true
        } catch (ex: NoMatchingViewException) {
            false
        }
    }

    fun isEmailToShareSave(): Boolean {
        return try {
            uiDevice.hasObject(By.text(exampleEmail))
            true
        } catch (ex: NoMatchingViewException) {
            false
        }
    }
}
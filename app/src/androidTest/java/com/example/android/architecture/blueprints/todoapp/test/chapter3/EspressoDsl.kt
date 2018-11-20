package com.example.android.architecture.blueprints.todoapp.test.chapter3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Inline functions
 */
fun viewWithText(text: String): ViewInteraction = Espresso.onView(ViewMatchers.withText(text))

fun viewWithText(stringId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withText(stringId))

fun viewWithId(id: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(id))

fun onAnyData(): DataInteraction = Espresso.onData(CoreMatchers.anything())

fun dataInstanceOf(clazz: Class<*>): DataInteraction = Espresso.onData(CoreMatchers.instanceOf(clazz))

/**
 * ViewActions
 */
fun ViewInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun ViewInteraction.type(text: String): ViewInteraction = perform(ViewActions.typeText(text))

fun ViewInteraction.replace(text: String): ViewInteraction = perform(ViewActions.replaceText(text))

fun ViewInteraction.closeKeyboard(): ViewInteraction = perform(ViewActions.closeSoftKeyboard())

/**
 * ViewInteraction extensions
 */
fun ViewInteraction.checkHasChildByText(text: String): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.withChild(ViewMatchers.withText(text))))

fun ViewInteraction.checkHasChildByText(id: Int): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.withChild(ViewMatchers.withText(id))))

fun ViewInteraction.checkHasChildById(id: Int): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.withChild(ViewMatchers.withId(id))))

fun ViewInteraction.checkDisplayed(): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.checkNotDisplayed(): ViewInteraction =
        check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

fun ViewInteraction.checkDoesNotExist(): ViewInteraction =
        check(ViewAssertions.doesNotExist())

fun ViewInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
        check(ViewAssertions.matches(matcher))

fun ViewInteraction.clickTodoCheckBoxWithTitle(text: String): ViewInteraction =
        perform(CustomRecyclerViewActions.ClickTodoCheckBoxWithTitleViewAction.clickTodoCheckBoxWithTitle(text))

fun ViewInteraction.scrollToLastItem(): ViewInteraction =
        perform(CustomRecyclerViewActions.ScrollToLastHolder.scrollToLastHolder())

fun ViewInteraction.pressEspressoBack() = Espresso.pressBack()

/**
 * Expand function for web view test case.
 * It contains a Thread.sleep() each time key event is sent.
 *
 * @param key - keycode from {@link KeyEvent}
 * @param milliseconds - milliseconds to sleep
 * @param count - amount of times {@link KeyEvent} should be executed
 */
fun ViewInteraction.sleepAndPressKey(key: Int, milliseconds: Long, count: Int = 1): ViewInteraction {
    for (i in 1..count) {
        /**
         * Having Thread.sleep() in tests is a bad practice.
         * Here we are using it just to solve specific issue and nothing more.
         */
        Thread.sleep(milliseconds)
        perform(ViewActions.pressKey(key))
    }
    return this
}

/**
 * Aggregated matchers
 */
fun ViewInteraction.allOf(vararg matcher: Matcher<View>): ViewInteraction {
    return check(ViewAssertions.matches(Matchers.allOf(matcher.asIterable())))
}

/**
 * DataInteraction extensions
 */
fun DataInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun DataInteraction.checkDisplayed(): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun DataInteraction.checkWithText(text: String): ViewInteraction =
        check(ViewAssertions.matches(ViewMatchers.withText(text)))

fun DataInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
        check(ViewAssertions.matches(matcher))

fun DataInteraction.childById(id: Int): DataInteraction = onChildView(withId(id))

fun DataInteraction.inAdapterById(id: Int): DataInteraction = inAdapterView(withId(id))

/**
 * RecyclerView actions
 */
fun ViewInteraction.actionAtPosition(position: Int,
                                     action: ViewAction): ViewInteraction =
        perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, action))

fun ViewInteraction.scrollToHolder(matcher: Matcher<RecyclerView.ViewHolder>): ViewInteraction =
        perform(scrollToHolder<RecyclerView.ViewHolder>(matcher))

fun ViewInteraction.actionOnItem(matcher: Matcher<RecyclerView.ViewHolder>,
                                 action: ViewAction): ViewInteraction =
        perform(actionOnHolderItem<RecyclerView.ViewHolder>(matcher, action))

fun ViewInteraction.scrollToPosition(position: Int): ViewInteraction =
        perform(scrollToPosition<RecyclerView.ViewHolder>(position))

fun ViewInteraction.scrollTo(matcher: Matcher<View>): ViewInteraction =
        perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(matcher))

/**
 * ViewMatchers
 */
fun parentWithId(id: Int): Matcher<View> = ViewMatchers.withParent(withId(id))

/**
 * Waiters
 */
private const val FOUR_SECONDS = 4000

fun ViewInteraction.wait(): ViewInteraction =
        ConditionWatchers.waitForElement(this, FOUR_SECONDS)

fun ViewInteraction.waitFullyVisible(): ViewInteraction =
        ConditionWatchers.waitForElementFullyVisible(this, FOUR_SECONDS)

fun ViewInteraction.waitForGone(): ViewInteraction =
        ConditionWatchers.waitForElementIsGone(this, FOUR_SECONDS)

fun ViewInteraction.waitForChildIsGone(viewMatcher: Matcher<View>): ViewInteraction =
        ConditionWatchers.waitForElementIsGone(this, viewMatcher, FOUR_SECONDS)


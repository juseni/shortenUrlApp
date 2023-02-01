package com.example.shortlyapphijfqp

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.shortlyapphijfqp.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private var mStringToBeTyped: String? = null

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        // Specify a valid string.
        mStringToBeTyped = "www.google.com"
    }

    @Test
    fun validateEditText() {
        onView(withId(R.id.enterText_editText))
            .perform(typeText(mStringToBeTyped), closeSoftKeyboard())
            .check(matches(withText(mStringToBeTyped)))
    }

    @Test
    fun onShortenUrlButtonClicked() {
        onView(withId(R.id.shortenButton)).perform(click())
        onView(withId(R.id.enterText_editText)).check(matches(withText("")))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.shortlyapphijfqp", appContext.packageName)
    }
}
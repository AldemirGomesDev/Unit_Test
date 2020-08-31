package com.aldemir.unittest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.aldemir.unittest.ui.login.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityInstrumentedTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun initValidString() {
        stringToBetyped = "Espresso"
    }

    @Test
    fun testEventLoginActivity() {
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

    @Test
    fun whenHandleCalculateIsCalledResult() {
        onView(withId(R.id.username)).perform( typeText("aldemir@gmail.com")).perform(closeSoftKeyboard())
        onView(withId(R.id.password)).perform( typeText("12345")).perform(closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

    }

    @Test
    fun changeText_sameActivity() {
        onView(withId(R.id.username)).perform(typeText(stringToBetyped), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText(stringToBetyped), closeSoftKeyboard())

        onView(withId(R.id.username)).check(matches(withText(stringToBetyped)))
        onView(withId(R.id.password)).check(matches(withText(stringToBetyped)))
        onView(withId(R.id.login)).perform(click())

    }
}
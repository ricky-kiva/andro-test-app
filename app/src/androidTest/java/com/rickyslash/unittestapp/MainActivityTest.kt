package com.rickyslash.unittestapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dumVolume = "504.0"
    private val dumCircumference = "100.0"
    private val dumSurfaceArea = "396.0"
    private val dumLength = "12.0"
    private val dumWidth = "7.0"
    private val dumHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty = "Field can't be empty"

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        // 'get' the 'UI component' then perform 'typeText' then 'closeSoftKeyboard'
        onView(withId(R.id.edt_length)).perform(typeText(dumLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dumWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dumHeight), closeSoftKeyboard())

        // 'get' the button 'UI component' then 'check' if the button 'is displayed'
        // then clicks it
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        // 'get' the 'circumference' button then 'check' is it's 'displayed'
        // then clicks it
        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_circumference)).perform(click())

        // 'get' the 'TextView' result then 'check' if it's displayed
        // 'check' if the 'value' is the same as the 'dummy circumference'
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dumCircumference)))
    }

    @Test
    fun assertGetSurfaceArea() {
        onView(withId(R.id.edt_length)).perform(typeText(dumLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dumWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dumHeight), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_calculate_surface_area)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_surface_area)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dumSurfaceArea)))
    }

    @Test
    fun assertGetVolume() {
        onView(withId(R.id.edt_length)).perform(typeText(dumLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dumWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dumHeight), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_calculate_volume)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_volume)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dumVolume)))
    }

    @Test
    fun assertEmptyInput() {
        onView(withId(R.id.edt_length)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_length)).perform(typeText(dumLength), closeSoftKeyboard())

        onView(withId(R.id.edt_width)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_width)).perform(typeText(dumWidth), closeSoftKeyboard())

        onView(withId(R.id.edt_height)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_height)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_height)).perform(typeText(dumHeight), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
    }
}
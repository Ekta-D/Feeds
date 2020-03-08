package com.appiness.cogni.tasktodo.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.appiness.cogni.tasktodo.R
import com.appiness.cogni.tasktodo.data.FakeData
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest
{
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    val LIST_ITEM_IN_TEST = 3
    val ROWS_IN_TEST = FakeData.response[LIST_ITEM_IN_TEST]

    @Test
    fun isViewsVisible() {
       onView(ViewMatchers.withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      onView(ViewMatchers.withId(R.id.recycle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))



    }


    @Test
    fun test_dataVisibility() {

//        onView(withId(R.id.recycle))
//            .perform(actionOnItemAtPosition<RecycleAdapter.MViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.title_page)).check(matches(withText("About Canada")))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.recycle)).check(matches(withText(ROWS_IN_TEST.title)))
    }
}
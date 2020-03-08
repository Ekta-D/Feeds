package com.appiness.cogni.tasktodo.view

import android.content.Context
import android.net.wifi.WifiManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.appiness.cogni.tasktodo.R
import com.appiness.cogni.tasktodo.data.FakeData
import com.ebayk.utils.NetworkUtils
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    val LIST_ITEM_IN_TEST = 3
    val ROWS_IN_TEST = FakeData.response[LIST_ITEM_IN_TEST]

    @Test
    fun isViewsVisible() {
        if (NetworkUtils.isNetworkConnected(activityRule.activity))
        {
            onView(withId(R.id.toolbar))
                .check(matches(isDisplayed()))
            onView(withId(R.id.recycle))
                .check(matches(isDisplayed()))
        }
        else{
            onView(withId(R.id.no_data)).check(matches(isDisplayed()))
        }
    }


    @Test
    fun test_dataVisibility() {
        var recyclerView: RecyclerView
        recyclerView = activityRule.activity.findViewById(R.id.recycle)
        var count = recyclerView.adapter!!.itemCount
        if (count > 0) {
            onView(withId(R.id.recycle)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            );
            onView(withId(R.id.recycle)).perform(
                scrollToPosition<RecyclerView.ViewHolder>(5))
        }

        else{
            onView(withId(R.id.no_data)).check(matches(withText(R.string.check_network)))
        }
    }



}
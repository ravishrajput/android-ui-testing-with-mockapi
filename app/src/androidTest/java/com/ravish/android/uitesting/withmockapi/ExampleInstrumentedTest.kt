package com.ravish.android.uitesting.withmockapi

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ravish.android.uitesting.withmockapi.commons.api.FakeApiServices
import com.ravish.android.uitesting.withmockapi.presentation.activity.HomeActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val fakeApiService: FakeApiServices = FakeApiServices()

    @Before
    fun before() {
        hiltRule.inject()
    }

    @Test
    fun testHomeActivityUsersList() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.rvUserList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.close()
    }
}
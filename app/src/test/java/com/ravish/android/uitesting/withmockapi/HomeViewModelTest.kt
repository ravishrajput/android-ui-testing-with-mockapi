package com.ravish.android.uitesting.withmockapi

import android.os.Looper
import com.ravish.android.uitesting.withmockapi.commons.api.FakeApiServices
import com.ravish.android.uitesting.withmockapi.data.network.ApiRepository
import com.ravish.android.uitesting.withmockapi.presentation.model.UIState
import com.ravish.android.uitesting.withmockapi.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val fakeApiService: FakeApiServices = FakeApiServices()

    @Inject
    lateinit var apiRepository: ApiRepository

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun before() {
        hiltRule.inject()
        homeViewModel = HomeViewModel(apiRepository)
    }

    @Test
    fun `test loadUserData Success`() = runBlockingTest {
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Success)
        assertNotNull(value.data)
        assertEquals(6, value.data?.size)
    }
}
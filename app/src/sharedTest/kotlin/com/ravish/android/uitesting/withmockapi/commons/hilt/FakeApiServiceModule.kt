package com.ravish.android.uitesting.withmockapi.commons.hilt

import com.ravish.android.uitesting.withmockapi.commons.api.FakeApiServices
import com.ravish.android.uitesting.withmockapi.data.network.ApiServices
import com.ravish.android.uitesting.withmockapi.di.ApiServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApiServiceModule::class])
abstract class FakeApiServiceModule {

    @Singleton
    @Binds
    abstract fun provideApiService(fakeApiServices: FakeApiServices): ApiServices
}
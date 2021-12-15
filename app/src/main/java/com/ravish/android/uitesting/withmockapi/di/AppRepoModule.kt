package com.ravish.android.uitesting.withmockapi.di

import com.ravish.android.uitesting.withmockapi.data.network.ApiRepository
import com.ravish.android.uitesting.withmockapi.data.network.ApiRepositoryImpl
import com.ravish.android.uitesting.withmockapi.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppRepoModule {
    @Provides
    fun providesApiRepository(apiServices: ApiServices): ApiRepository =
        ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
}
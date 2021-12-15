package com.ravish.android.uitesting.withmockapi.data.network

import com.ravish.android.uitesting.withmockapi.data.model.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getUser(): Flow<User>
}
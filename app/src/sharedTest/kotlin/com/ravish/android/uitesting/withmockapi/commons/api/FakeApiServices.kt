package com.ravish.android.uitesting.withmockapi.commons.api

import com.ravish.android.uitesting.withmockapi.commons.util.JsonLoader
import com.ravish.android.uitesting.withmockapi.data.model.UsersData
import com.ravish.android.uitesting.withmockapi.data.network.ApiServices
import javax.inject.Inject

class FakeApiServices @Inject constructor() : ApiServices {
    override suspend fun getUser(): UsersData {
        return JsonLoader.objectFromJsonFileWithType(USERS_LIST_JSON)
    }

    companion object {
        private const val USERS_LIST_JSON = "/json/users_list.json"
    }
}
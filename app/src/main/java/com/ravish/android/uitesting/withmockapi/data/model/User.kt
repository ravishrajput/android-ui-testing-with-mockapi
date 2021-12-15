package com.ravish.android.uitesting.withmockapi.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val username: String,
    var email: String,
    var imageUrl: String
): Parcelable

data class UsersData(val users: List<User>)
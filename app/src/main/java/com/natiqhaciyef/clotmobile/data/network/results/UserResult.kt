package com.natiqhaciyef.clotmobile.data.network.results

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.UserModel

data class UserResult(
    @SerializedName("user_table")
    val userTable: List<UserModel>?
)

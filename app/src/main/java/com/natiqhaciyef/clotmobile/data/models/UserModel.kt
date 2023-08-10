package com.natiqhaciyef.clotmobile.data.models

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.enums.UserTypes

data class UserModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("type")
    var type: String = UserTypes.User.name
)

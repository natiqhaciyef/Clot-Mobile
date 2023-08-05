package com.natiqhaciyef.clotmobile.data.models

import com.google.gson.annotations.SerializedName

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
    var password: String
)

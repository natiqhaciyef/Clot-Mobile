package com.natiqhaciyef.clotmobile.data.network.results

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.models.CoffeeModel

data class CoffeeResult(
    @SerializedName("coffee_table")
    val coffeeList: List<CoffeeModel>
)
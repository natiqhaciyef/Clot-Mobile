package com.natiqhaciyef.clotmobile.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("clothes_table")
data class ClothesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var details: String,
    var brand: String,
    var price: Double,
    var priceCurrency: String,
    var size: String,
    var color: String,
    var image: String,
    var type: String,
    var cargoPrice: Double,
    var season: String,
    var country: String,
    var isActive: Int,
    var date: String
)

package com.natiqhaciyef.clotmobile.data.network.services

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ClothesService {

    @GET("clothes/get_clothes.php")
    suspend fun getAllClothes(): ClothesResult

    @GET("clothes/search_clothes.php")
    suspend fun getClothesById(id: Int): ClothesModel?

    @POST("clothes/insert_clothes.php")
    @FormUrlEncoded
    suspend fun insertClothes(
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("brand") brand: String,
        @Field("price") price: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("size") size: String,
        @Field("color") color: String,
        @Field("image") image: String,
        @Field("type") type: String,
        @Field("category") category: String,
        @Field("cargo_price") cargoPrice: Double,
        @Field("season") season: String,
        @Field("country") country: String,
        @Field("is_active") isActive: Int,
    ): CRUDResponse

    @POST("clothes/delete_clothes.php")
    @FormUrlEncoded
    suspend fun deleteClothes(
        @Field("id") id: Int,
    ): CRUDResponse

    @POST("clothes/update_clothes.php")
    @FormUrlEncoded
    suspend fun updateClothes(
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("brand") brand: String,
        @Field("price") price: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("size") size: String,
        @Field("color") color: String,
        @Field("image") image: String,
        @Field("type") type: String,
        @Field("category") category: String,
        @Field("cargo_price") cargoPrice: Double,
        @Field("season") season: String,
        @Field("country") country: String,
        @Field("is_active") isActive: Int,
    ): CRUDResponse
}
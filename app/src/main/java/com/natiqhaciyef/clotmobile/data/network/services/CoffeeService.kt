package com.natiqhaciyef.clotmobile.data.network.services

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CoffeeResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CoffeeService {

    @GET("coffee/get_coffee.php")
    suspend fun getAllCoffee(): CoffeeResult

    @GET("coffee/search_coffee.php")
    suspend fun getCoffeeByTitle(title: String): CoffeeModel

    @POST("coffee/insert_coffee.php")
    @FormUrlEncoded
    suspend fun insertCoffee(
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("image") image: String,
        @Field("price") price: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("size") size: String,
        @Field("rating") rating: Double,
        @Field("country") country: String,
    ): CRUDResponse

    @POST("coffee/delete_coffee.php")
    @FormUrlEncoded
    suspend fun deleteCoffee(
        @Field("id") id: Int,
    ): CRUDResponse

    @POST("coffee/update_coffee.php")
    @FormUrlEncoded
    suspend fun updateCoffee(
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("image") image: String,
        @Field("price") price: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("size") size: String,
        @Field("rating") rating: Double,
        @Field("country") country: String,
    ): CRUDResponse

}
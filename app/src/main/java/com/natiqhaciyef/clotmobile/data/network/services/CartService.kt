package com.natiqhaciyef.clotmobile.data.network.services

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CartResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CartService {

    @GET("carts/get_carts.php")
    suspend fun getCarts(): CartResult

    @POST("carts/insert_cart.php")
    @FormUrlEncoded
    suspend fun insertCart(
        @Field("id") id: Int = 0,
        @Field("user_id") userId: Int,
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("image") image: String,
        @Field("size") size: String,
        @Field("colors") colors: String,
        @Field("total_price") totalPrice: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("total_cargo_price") totalCargoPrice: Double,
        @Field("type") type: String,
        @Field("amount") amount: Int,
        @Field("date") date: String,
    ): CRUDResponse

    @POST("carts/update_cart.php")
    @FormUrlEncoded
    suspend fun updateCart(
        @Field("id") id: Int,
        @Field("user_id") userId: Int,
        @Field("title") title: String,
        @Field("details") details: String,
        @Field("image") image: String,
        @Field("size") size: String,
        @Field("colors") colors: String,
        @Field("total_price") totalPrice: Double,
        @Field("price_currency") priceCurrency: String,
        @Field("total_cargo_price") totalCargoPrice: Double,
        @Field("type") type: String,
        @Field("amount") amount: Int,
        @Field("date") date: String
    ): CRUDResponse

    @POST("carts/delete_cart.php")
    @FormUrlEncoded
    suspend fun deleteCart(
        @Field("id") id: Int
    ): CRUDResponse
}
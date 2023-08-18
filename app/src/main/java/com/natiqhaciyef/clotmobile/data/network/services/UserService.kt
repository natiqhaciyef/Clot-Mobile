package com.natiqhaciyef.clotmobile.data.network.services

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.UserResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("users/get_user.php")
    suspend fun getUser(): UserResult


    @POST("users/insert_user.php")
    @FormUrlEncoded
    suspend fun insertUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
    ): CRUDResponse


    @POST("users/insert_user.php")
    @FormUrlEncoded
    suspend fun deleteUser(
        @Field("id") id: Int
    ): CRUDResponse


    @POST("users/update_user.php")
    @FormUrlEncoded
    suspend fun updateUser(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
    ): CRUDResponse

}
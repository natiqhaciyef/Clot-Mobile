package com.natiqhaciyef.clotmobile.data.network.services

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse

interface UserService {

    suspend fun getUser(email: String): UserModel?

    suspend fun insertUser(userModel: UserModel): CRUDResponse

    suspend fun deleteUser(id: Int): CRUDResponse

    suspend fun updateUser(userModel: UserModel): CRUDResponse

}
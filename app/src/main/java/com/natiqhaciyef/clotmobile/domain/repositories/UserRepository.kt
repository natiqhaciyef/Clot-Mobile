package com.natiqhaciyef.clotmobile.domain.repositories

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse

interface UserRepository{

    suspend fun getUser(email: String): UserModel?

    suspend fun insertUser(user: UserModel): CRUDResponse

    suspend fun updateUser(user: UserModel): CRUDResponse

    suspend fun removeUser(id: Int): CRUDResponse

}
package com.natiqhaciyef.clotmobile.domain.repositories.remote

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse

interface UserRemoteRepository{

    suspend fun getUser(email: String): UserModel?

    suspend fun insertUser(user: UserModel): CRUDResponse

    suspend fun updateUser(user: UserModel): CRUDResponse

    suspend fun removeUser(id: Int): CRUDResponse

}
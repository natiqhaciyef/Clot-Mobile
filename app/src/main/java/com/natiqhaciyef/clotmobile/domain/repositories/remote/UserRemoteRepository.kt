package com.natiqhaciyef.clotmobile.domain.repositories.remote

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.UserResult

interface UserRemoteRepository{

    suspend fun getUser(): UserResult

    suspend fun insertUser(user: UserModel): CRUDResponse

    suspend fun updateUser(user: UserModel): CRUDResponse

    suspend fun removeUser(id: Int): CRUDResponse

}
package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val service: UserService
) {

    suspend fun getUser() = withContext(Dispatchers.IO) {
        service.getUser()
    }

    suspend fun insertUser(user: UserModel) = withContext(Dispatchers.IO) {
        service.insertUser(
            name = user.name,
            email = user.email,
            phone = user.phone,
            password = user.password
        )
    }

    suspend fun updateUser(user: UserModel) = withContext(Dispatchers.IO) {
        service.updateUser(
            id = user.id,
            name = user.name,
            email = user.email,
            phone = user.phone,
            password = user.password
        )
    }

    suspend fun deleteUser(id: Int) = withContext(Dispatchers.IO) {
        service.deleteUser(id)
    }

}
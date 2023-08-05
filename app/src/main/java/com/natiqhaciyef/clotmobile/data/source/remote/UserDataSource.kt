package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.network.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource (
    private val service: UserService
) {

    suspend fun getUser(email: String) = withContext(Dispatchers.IO){
        service.getUser(email)
    }

    suspend fun insertUser(user: UserModel) = withContext(Dispatchers.IO){
        service.insertUser(user)
    }

    suspend fun updateUser(user: UserModel) = withContext(Dispatchers.IO){
        service.updateUser(user)
    }

    suspend fun deleteUser(id: Int) = withContext(Dispatchers.IO){
        service.deleteUser(id)
    }

}
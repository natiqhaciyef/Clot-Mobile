package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.source.remote.UserDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository

class UserRepositoryImpl (
    private val ds: UserDataSource
) : UserRepository {
    override suspend fun getUser(email: String) = ds.getUser(email)

    override suspend fun insertUser(user: UserModel) = ds.insertUser(user)

    override suspend fun updateUser(user: UserModel) = ds.updateUser(user)

    override suspend fun removeUser(id: Int) = ds.deleteUser(id)
}
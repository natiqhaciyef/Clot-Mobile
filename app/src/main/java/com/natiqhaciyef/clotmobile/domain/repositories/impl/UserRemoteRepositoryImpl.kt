package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.source.remote.UserRemoteDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.UserRemoteRepository

class UserRemoteRepositoryImpl (
    private val ds: UserRemoteDataSource
) : UserRemoteRepository {
    override suspend fun getUser() = ds.getUser()

    override suspend fun insertUser(user: UserModel) = ds.insertUser(user)

    override suspend fun updateUser(user: UserModel) = ds.updateUser(user)

    override suspend fun removeUser(id: Int) = ds.deleteUser(id)
}
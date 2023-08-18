package com.natiqhaciyef.clotmobile.domain.usecases.remote.user

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.UserRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepo: UserRemoteRepository
) {

    suspend operator fun invoke(email: String) = flow {
        emit(Resource.loading(null))

        val user = userRepo.getUser()
        if (user.userTable != null) {
            emit(Resource.success(user.userTable.filter { it.email == email }))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}
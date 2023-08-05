package com.natiqhaciyef.clotmobile.domain.usecases.remote.user

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepo: UserRepository
) {

    suspend operator fun invoke(email: String) = flow {
        emit(Resource.loading(null))

        val user = userRepo.getUser(email)
        if (user != null){
            emit(Resource.success(user))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}
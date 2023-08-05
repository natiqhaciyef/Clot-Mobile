package com.natiqhaciyef.clotmobile.domain.usecases.remote.user

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveUserUseCase@Inject constructor(
    private val userRepo: UserRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val response = userRepo.removeUser(id)
        if (response.success == 1){
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }
    }

}
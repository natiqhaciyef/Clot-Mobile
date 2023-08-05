package com.natiqhaciyef.clotmobile.domain.usecases.remote.user

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userRepo: UserRepository
) {

    suspend operator fun invoke(user: UserModel) = flow {
        emit(Resource.loading(null))

        val response = userRepo.insertUser(user)
        if (response.success == 1){
            emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }
    }

}
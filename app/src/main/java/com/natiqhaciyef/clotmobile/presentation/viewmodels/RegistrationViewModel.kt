package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.domain.repositories.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.LoginUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.RegisterUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.ResetPasswordUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.user.GetUserUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.user.InsertUserUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.user.RemoveUserUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    val firebaseRepo: FirebaseRepositoryImpl,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val removeUserUseCase: RemoveUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : BaseViewModel() {

    fun getUserType(
        onSuccess: (UserModel) -> Unit = { },
        onError: () -> Unit = { }
    ) {
        viewModelScope.launch {
            firebaseRepo.auth.currentUser?.let { currentUser ->
                currentUser.email?.let { email ->
                    getUser(
                        email,
                        onSuccess = onSuccess,
                        onError = onError
                    )
                }
            }
        }
    }

    fun loginUserFromFirebase(
        userModel: UserModel,
        onSuccess: (UserModel) -> Unit = { },
        onError: () -> Unit = {}
    ) {
        viewModelScope.launch {
            loginUseCase.invoke(email = userModel.email, password = userModel.password,
                onSuccess = {
                    getUser(
                        email = userModel.email,
                        onSuccess = onSuccess,
                        onError = onError
                    )
                }, onFail = {
                    onError()
                }
            )
        }
    }


    fun registrationFromFirebase(
        userModel: UserModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { }
    ) {
        viewModelScope.launch {
            registerUseCase.invoke(email = userModel.email, password = userModel.password,
                onSuccess = {
                    insertUser(userModel = userModel, onSuccess = onSuccess, onError = onError)
                }, onFail = {
                    onError()
                }
            )
        }
    }


    fun resetPasswordFromFirebase(email: String) {
        viewModelScope.launch {
            resetPasswordUseCase.invoke(email = email,
                onSuccess = {
                    getUser(email = email, onSuccess = { user ->
                        removeUser(id = user.id)
                    })
                }, onFail = {

                }
            )
        }
    }

    fun insertUser(
        userModel: UserModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertUserUseCase.invoke(userModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun updateUser(
        userModel: UserModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            updateUserUseCase.invoke(userModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun removeUser(
        id: Int,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            removeUserUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun getUser(
        email: String,
        onSuccess: (UserModel) -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            getUserUseCase.invoke(email).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            onSuccess(result.data)
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }
}
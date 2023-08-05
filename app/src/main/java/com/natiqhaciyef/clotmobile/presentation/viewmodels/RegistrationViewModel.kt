package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.LoginUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.RegisterUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.firebase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : BaseViewModel() {

    fun loginUserFromFirebase(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.invoke(email = email, password = password,
                onSuccess = {

                }, onFail = {

                }
            )
        }
    }


    fun registrationFromFirebase(email: String, password: String) {
        viewModelScope.launch {
            registerUseCase.invoke(email = email, password = password,
                onSuccess = {

                }, onFail = {

                }
            )
        }
    }


    fun resetPasswordFromFirebase(email: String) {
        viewModelScope.launch {
            resetPasswordUseCase.invoke(email = email,
                onSuccess = {

                }, onFail = {

                }
            )
        }
    }
}
package com.natiqhaciyef.clotmobile.domain.usecases.firebase

import com.natiqhaciyef.clotmobile.domain.repositories.impl.FirebaseRepositoryImpl
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val firebaseRepo: FirebaseRepositoryImpl
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        firebaseRepo.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail(it)
            }
    }
}
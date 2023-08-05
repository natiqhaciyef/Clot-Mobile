package com.natiqhaciyef.clotmobile.domain.usecases.firebase

import com.natiqhaciyef.clotmobile.domain.repositories.impl.FirebaseRepositoryImpl
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val firebaseRepo: FirebaseRepositoryImpl
) {

    suspend operator fun invoke(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        firebaseRepo.auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail(it)
            }
    }
}
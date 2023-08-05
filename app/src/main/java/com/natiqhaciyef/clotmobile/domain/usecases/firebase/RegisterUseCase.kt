package com.natiqhaciyef.clotmobile.domain.usecases.firebase

import com.natiqhaciyef.clotmobile.domain.repositories.impl.FirebaseRepositoryImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val firebaseRepo: FirebaseRepositoryImpl
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        firebaseRepo.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail(it)
            }
    }
}
package com.natiqhaciyef.clotmobile.data.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.natiqhaciyef.clotmobile.domain.repositories.impl.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideFirebaseRepository(auth: FirebaseAuth) = FirebaseRepositoryImpl(auth)
}
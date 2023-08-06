package com.natiqhaciyef.clotmobile.data.di.modules

import com.natiqhaciyef.clotmobile.data.network.services.ClothesService
import com.natiqhaciyef.clotmobile.data.network.NetworkURL
import com.natiqhaciyef.clotmobile.data.network.services.CoffeeService
import com.natiqhaciyef.clotmobile.data.network.services.UserService
import com.natiqhaciyef.clotmobile.data.network.services.VideoService
import com.natiqhaciyef.clotmobile.data.source.remote.ClothesDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.CoffeeDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.UserDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.VideoDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository
import com.natiqhaciyef.clotmobile.domain.repositories.VideoRepository
import com.natiqhaciyef.clotmobile.domain.repositories.impl.ClothesRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.CoffeeRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.UserRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.VideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okhttp = OkHttpClient.Builder().addInterceptor(logger)

    @Provides
    @Singleton
    fun provideNetworkConfiguration(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkURL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp.build())
        .build()

    @Provides
    @Singleton
    fun provideClothesService(network: Retrofit) = network.create(ClothesService::class.java)

    @Provides
    @Singleton
    fun provideCoffeeService(network: Retrofit) = network.create(CoffeeService::class.java)

    @Provides
    @Singleton
    fun provideUserService(network: Retrofit) = network.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideVideoService(network: Retrofit) = network.create(VideoService::class.java)

    @Provides
    @Singleton
    fun provideClothesDataSource(service: ClothesService) = ClothesDataSource(service)

    @Provides
    @Singleton
    fun provideCoffeeDataSource(service: CoffeeService) = CoffeeDataSource(service)

    @Provides
    @Singleton
    fun provideUserDataSource(service: UserService) = UserDataSource(service)

    @Provides
    @Singleton
    fun provideVideoDataSource(service: VideoService) = VideoDataSource(service)

    @Provides
    @Singleton
    fun provideClothesRepository(ds: ClothesDataSource) = ClothesRepositoryImpl(ds) as ClothesRepository

    @Provides
    @Singleton
    fun provideCoffeeRepository(ds: CoffeeDataSource) = CoffeeRepositoryImpl(ds) as CoffeeRepository

    @Provides
    @Singleton
    fun provideUserRepository(ds: UserDataSource) = UserRepositoryImpl(ds) as UserRepository

    @Provides
    @Singleton
    fun provideVideoRepository(ds: VideoDataSource) = VideoRepositoryImpl(ds) as VideoRepository

}
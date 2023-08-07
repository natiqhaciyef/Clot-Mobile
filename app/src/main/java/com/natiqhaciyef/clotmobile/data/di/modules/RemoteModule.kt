package com.natiqhaciyef.clotmobile.data.di.modules

import com.natiqhaciyef.clotmobile.data.network.services.ClothesService
import com.natiqhaciyef.clotmobile.data.network.NetworkURL
import com.natiqhaciyef.clotmobile.data.network.services.CoffeeService
import com.natiqhaciyef.clotmobile.data.network.services.UserService
import com.natiqhaciyef.clotmobile.data.network.services.VideoService
import com.natiqhaciyef.clotmobile.data.source.remote.ClothesRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.CoffeeRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.UserRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.VideoRemoteDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository
import com.natiqhaciyef.clotmobile.domain.repositories.UserRepository
import com.natiqhaciyef.clotmobile.domain.repositories.VideoRepository
import com.natiqhaciyef.clotmobile.domain.repositories.impl.ClothesRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.CoffeeRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.UserRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.VideoRemoteRepositoryImpl
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
    fun provideClothesDataSource(service: ClothesService) = ClothesRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideCoffeeDataSource(service: CoffeeService) = CoffeeRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideUserDataSource(service: UserService) = UserRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideVideoDataSource(service: VideoService) = VideoRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideClothesRepository(ds: ClothesRemoteDataSource) = ClothesRemoteRepositoryImpl(ds) as ClothesRepository

    @Provides
    @Singleton
    fun provideCoffeeRepository(ds: CoffeeRemoteDataSource) = CoffeeRemoteRepositoryImpl(ds) as CoffeeRepository

    @Provides
    @Singleton
    fun provideUserRepository(ds: UserRemoteDataSource) = UserRemoteRepositoryImpl(ds) as UserRepository

    @Provides
    @Singleton
    fun provideVideoRepository(ds: VideoRemoteDataSource) = VideoRemoteRepositoryImpl(ds) as VideoRepository

}
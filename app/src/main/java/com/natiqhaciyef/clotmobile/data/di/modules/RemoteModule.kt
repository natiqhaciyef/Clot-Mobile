package com.natiqhaciyef.clotmobile.data.di.modules

import com.natiqhaciyef.clotmobile.data.network.services.ClothesService
import com.natiqhaciyef.clotmobile.data.network.NetworkURL
import com.natiqhaciyef.clotmobile.data.network.services.CartService
import com.natiqhaciyef.clotmobile.data.network.services.CoffeeService
import com.natiqhaciyef.clotmobile.data.network.services.UserService
import com.natiqhaciyef.clotmobile.data.network.services.VideoService
import com.natiqhaciyef.clotmobile.data.source.remote.CartRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.ClothesRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.CoffeeRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.UserRemoteDataSource
import com.natiqhaciyef.clotmobile.data.source.remote.VideoRemoteDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.impl.CartRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CoffeeRemoteRepository
import com.natiqhaciyef.clotmobile.domain.repositories.remote.UserRemoteRepository
import com.natiqhaciyef.clotmobile.domain.repositories.remote.VideoRemoteRepository
import com.natiqhaciyef.clotmobile.domain.repositories.impl.ClothesRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.CoffeeRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.UserRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.impl.VideoRemoteRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
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
    @Provides
    @Singleton
    fun provideNetworkConfiguration(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkURL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(NetworkURL.okhttp.build())
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
    fun provideCartService(network: Retrofit) = network.create(CartService::class.java)

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
    fun provideCartDataSource(service: CartService) = CartRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideClothesRepository(ds: ClothesRemoteDataSource) = ClothesRemoteRepositoryImpl(ds) as ClothesRemoteRepository

    @Provides
    @Singleton
    fun provideCoffeeRepository(ds: CoffeeRemoteDataSource) = CoffeeRemoteRepositoryImpl(ds) as CoffeeRemoteRepository

    @Provides
    @Singleton
    fun provideUserRepository(ds: UserRemoteDataSource) = UserRemoteRepositoryImpl(ds) as UserRemoteRepository

    @Provides
    @Singleton
    fun provideVideoRepository(ds: VideoRemoteDataSource) = VideoRemoteRepositoryImpl(ds) as VideoRemoteRepository

    @Provides
    @Singleton
    fun provideCartRepository(ds: CartRemoteDataSource) = CartRemoteRepositoryImpl(ds) as CartRemoteRepository

}
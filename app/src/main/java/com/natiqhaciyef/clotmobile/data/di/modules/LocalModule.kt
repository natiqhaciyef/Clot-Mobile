package com.natiqhaciyef.clotmobile.data.di.modules

import android.app.Application
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.natiqhaciyef.clotmobile.common.helpers.MetaDataReader
import com.natiqhaciyef.clotmobile.common.helpers.MetaDataReaderImpl
import com.natiqhaciyef.clotmobile.data.db.ClotDatabase
import com.natiqhaciyef.clotmobile.data.db.dao.ClothesDao
import com.natiqhaciyef.clotmobile.data.source.local.ClothesLocalDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.impl.ClothesLocalRepositoryImpl
import com.natiqhaciyef.clotmobile.domain.repositories.local.ClothesLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideVideoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app)
            .build()
    }

    @Provides
    @Singleton
    fun provideMetaDataReader(app: Application): MetaDataReader {
        return MetaDataReaderImpl(app)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ClotDatabase::class.java, "clot_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideClothesDao(db: ClotDatabase) = db.getClothesDao()

    @Provides
    @Singleton
    fun provideClothesDataSource(dao: ClothesDao) = ClothesLocalDataSource(dao)

    @Provides
    @Singleton
    fun provideClothesRepository(ds: ClothesLocalDataSource) = ClothesLocalRepositoryImpl(ds) as ClothesLocalRepository
}
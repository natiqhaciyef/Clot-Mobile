package com.natiqhaciyef.clotmobile.data.source.local

import com.natiqhaciyef.clotmobile.data.db.dao.ClothesDao
import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClothesLocalDataSource(private val dao: ClothesDao) {

    suspend fun getAllClothes(): List<ClothesEntity>? = withContext(Dispatchers.IO){
        dao.getAllClothes()
    }

    suspend fun getClothesByDate(date: String): List<ClothesEntity>? = withContext(Dispatchers.IO){
        dao.getClothesByDate(date)
    }

    suspend fun insertClothes(clothesEntity: ClothesEntity) = withContext(Dispatchers.IO){
        dao.insertClothes(clothesEntity)
    }

    suspend fun removeClothes(clothesEntity: ClothesEntity) = withContext(Dispatchers.IO){
        dao.removeClothes(clothesEntity)
    }

}
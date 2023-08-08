package com.natiqhaciyef.clotmobile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.clotmobile.data.db.dao.ClothesDao
import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity

@Database(entities = [ClothesEntity::class], version = 1)
abstract class ClotDatabase : RoomDatabase(){
    abstract fun getClothesDao(): ClothesDao
}
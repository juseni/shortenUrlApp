package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.ShortenDao
import com.example.data.db.entities.ShortenEntity

@Database(entities = [ShortenEntity::class], version = 1)
abstract class ShortenDataBase: RoomDatabase() {

    abstract fun getShortenDao(): ShortenDao
}
package com.example.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entities.ShortenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShortenDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShortenUrl(shortenEntity: ShortenEntity)

    @Query("SELECT * FROM shorten_urls_table")
    suspend fun getAllShortenUrls(): List<ShortenEntity>

    @Query("DELETE FROM shorten_urls_table WHERE shortenId == :shortenId")
    suspend fun deleteShortenByUrl(shortenId: Int): Int
}
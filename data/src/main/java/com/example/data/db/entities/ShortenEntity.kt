package com.example.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shorten_urls_table",
    indices = [Index(value = ["code", "original_link"], unique = true)]
)
data class ShortenEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo var shortenId: Int = 0,
    @ColumnInfo var code: String? = null,
    @ColumnInfo var short_link: String? = null,
    @ColumnInfo var full_short_link: String? = null,
    @ColumnInfo var short_link2: String? = null,
    @ColumnInfo var full_short_ink2: String? = null,
    @ColumnInfo var share_link: String? = null,
    @ColumnInfo var full_share_link: String? = null,
    @ColumnInfo var original_link: String? = null
)
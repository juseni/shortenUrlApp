package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.ShortenDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    fun provideRoom(context: Context): ShortenDataBase {
        return synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ShortenDataBase::class.java,
                "shorten_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            instance
        }
    }

    @Singleton
    @Provides
    fun provideShortenDao(db: ShortenDataBase) = db.getShortenDao()
}
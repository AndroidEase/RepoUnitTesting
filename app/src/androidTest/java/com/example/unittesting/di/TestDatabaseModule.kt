package com.example.unittesting.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.unittesting.roomdatabase.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
class TestDatabaseModule {

    @Singleton
    @Provides
    fun provideTestDB(@ApplicationContext context: Context) : QuoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}
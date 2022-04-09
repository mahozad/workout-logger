package ir.mahozad.workout_logger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.mahozad.workout_logger.data.AppDatabase
import javax.inject.Singleton

const val DATABASE_NAME = "workout-database.db"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideUserDao(database: AppDatabase) = database.getUserDao()

    @Provides
    fun provideWorkoutDao(database: AppDatabase) = database.getWorkoutDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()
}

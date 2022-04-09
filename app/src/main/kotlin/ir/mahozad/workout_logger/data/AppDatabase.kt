package ir.mahozad.workout_logger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.mahozad.workout_logger.data.dao.UserDao
import ir.mahozad.workout_logger.data.dao.WorkoutDao
import ir.mahozad.workout_logger.data.entity.User
import ir.mahozad.workout_logger.data.entity.Workout

@Database(entities = [User::class, Workout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getWorkoutDao(): WorkoutDao
}

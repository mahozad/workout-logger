package ir.mahozad.workout_logger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.mahozad.workout_logger.user.UserDao
import ir.mahozad.workout_logger.workout.WorkoutDao

@Database(entities = [User::class, Workout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getWorkoutDao(): WorkoutDao
}

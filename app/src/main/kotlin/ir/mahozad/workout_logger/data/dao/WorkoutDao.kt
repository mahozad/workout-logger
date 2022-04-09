package ir.mahozad.workout_logger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import ir.mahozad.workout_logger.data.entity.Workout

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout): Long
}

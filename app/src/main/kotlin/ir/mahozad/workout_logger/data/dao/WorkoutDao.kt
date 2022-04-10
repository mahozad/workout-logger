package ir.mahozad.workout_logger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.mahozad.workout_logger.data.entity.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout): Long

    @Query("SELECT * FROM Workout")
    fun getAll(): Flow<List<Workout>>
}

package ir.mahozad.workout_logger.workout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.data.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout): Long

    @Query("SELECT * FROM Workout JOIN User ON Workout.userId = User.id")
    fun getAll(): Flow<Map<User, List<Workout>>>
}

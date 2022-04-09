package ir.mahozad

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout): Long
}

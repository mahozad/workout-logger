package ir.mahozad.workout_logger.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Workout")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val total: Int,
    val correct: Int,
    val userId: Int
)

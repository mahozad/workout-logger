package ir.mahozad.workout_logger.data.repository

import ir.mahozad.workout_logger.data.dao.WorkoutDao
import ir.mahozad.workout_logger.data.entity.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    suspend fun addWorkout(workout: Workout) = withContext(Dispatchers.IO) {
        workoutDao.insert(workout)
    }
}

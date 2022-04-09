package ir.mahozad.workout_logger

import ir.mahozad.Workout
import ir.mahozad.WorkoutDao
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    suspend fun addWorkout(workout: Workout) = workoutDao.insert(workout)
}

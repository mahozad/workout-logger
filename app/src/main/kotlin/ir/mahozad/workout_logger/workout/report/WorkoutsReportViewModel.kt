package ir.mahozad.workout_logger.workout.report

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.data.Workout
import ir.mahozad.workout_logger.workout.WorkoutRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WorkoutsReportViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun getAllWorkouts(): Flow<Map<User, List<Workout>>> {
        return workoutRepository.getAllWorkouts()
    }
}

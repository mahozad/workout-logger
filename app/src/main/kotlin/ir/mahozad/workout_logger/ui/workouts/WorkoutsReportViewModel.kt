package ir.mahozad.workout_logger.ui.workouts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.data.repository.WorkoutRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WorkoutsReportViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun getAllWorkouts(): Flow<List<Workout>> {
        return workoutRepository.getAllWorkouts()
    }
}

package ir.mahozad.workout_logger.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    fun addWorkout(total: Int, correct: Int) {
        viewModelScope.launch(dispatcher) {
            val workout = Workout(0, total, correct)
            workoutRepository.addWorkout(workout)
        }
    }
}

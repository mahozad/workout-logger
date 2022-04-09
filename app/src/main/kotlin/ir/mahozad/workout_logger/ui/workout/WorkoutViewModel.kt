package ir.mahozad.workout_logger.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.data.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {
    fun addWorkout(total: Int, correct: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val workout = Workout(0, total, correct)
            workoutRepository.addWorkout(workout)
        }
    }
}
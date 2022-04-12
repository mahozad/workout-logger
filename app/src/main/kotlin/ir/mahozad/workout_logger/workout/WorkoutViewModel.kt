package ir.mahozad.workout_logger.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val addResult: MutableStateFlow<Result> = MutableStateFlow(Result.Ongoing)
    val shouldFinish: StateFlow<Result> = addResult

    fun addWorkout(total: Int, correct: Int) {
        addResult.value = Result.Ongoing
        viewModelScope.launch(dispatcher) {
            val workout = Workout(0, total, correct)
            workoutRepository.addWorkout(workout)
            addResult.value = Result.Success
        }
    }
}

sealed class Result {
    object Ongoing : Result()
    object Success : Result()
    object Failure : Result()
}

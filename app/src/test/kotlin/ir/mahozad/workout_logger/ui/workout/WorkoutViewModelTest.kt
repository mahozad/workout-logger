package ir.mahozad.workout_logger.ui.workout

import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.data.repository.WorkoutRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verifyBlocking

@ExtendWith(MockitoExtension::class)
class WorkoutViewModelTest {

    @Mock lateinit var workoutRepository: WorkoutRepository

    @Test fun `Adding a workout should call the proper repository method`(): Unit = runBlocking {
        val viewModel = WorkoutViewModel(workoutRepository)
        val workout = Workout(0, 23, 19)
        viewModel.addWorkout(workout.total, workout.correct)
        verifyBlocking(workoutRepository) {
            addWorkout(workout)
        }
    }
}

package ir.mahozad.workout_logger.ui.workout

import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.data.repository.WorkoutRepository
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class WorkoutViewModelTest {

    @Mock lateinit var workoutRepository: WorkoutRepository

    @Test fun `Adding a workout should call the proper repository method`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = WorkoutViewModel(workoutRepository, dispatcher)
        val workout = Workout(0, 23, 19)
        viewModel.addWorkout(workout.total, workout.correct)
        advanceUntilIdle() // Required: insert a delay in viewModel before repository call to see why
        verify(workoutRepository)/* OR verifyBlocking */.addWorkout(workout)
    }
}

package ir.mahozad.workout_logger.workout

import ir.mahozad.workout_logger.data.Workout
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
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
        val workout = Workout(0, 23, 19, 1)
        viewModel.addWorkout(workout.total, workout.correct, workout.userId)
        advanceUntilIdle() // Required: insert a delay in viewModel before repository call to see why
        verify(workoutRepository)/* OR verifyBlocking */.addWorkout(workout)
    }

    /**
     * See [this post](https://stackoverflow.com/q/62110761/8583692).
     */
    @Test fun `Adding a valid workout should eventually return success as result`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = WorkoutViewModel(workoutRepository, dispatcher)
        val workout = Workout(0, 23, 19, 1)
        val results = mutableListOf<Result>()
        val job = launch(dispatcher) { viewModel.shouldFinish.toList(results) }
        viewModel.addWorkout(workout.total, workout.correct, workout.userId)
        runCurrent()
        assertThat(results).isEqualTo(listOf(Result.Ongoing, Result.Success))
        job.cancel()
    }

    @Test fun `After adding a valid workout, calling add again should return proper results`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = WorkoutViewModel(workoutRepository, dispatcher)
        val workout1 = Workout(0, 23, 19, 1)
        val workout2 = Workout(0, 20, 13, 1)
        val results = mutableListOf<Result>()
        val job = launch(dispatcher) { viewModel.shouldFinish.toList(results) }
        viewModel.addWorkout(workout1.total, workout1.correct, workout1.userId)
        viewModel.addWorkout(workout2.total, workout2.correct, workout2.userId)
        runCurrent()
        assertThat(results).isEqualTo(
            listOf(
                Result.Ongoing,
                Result.Success,
                Result.Ongoing,
                Result.Success
            )
        )
        job.cancel()
    }
}

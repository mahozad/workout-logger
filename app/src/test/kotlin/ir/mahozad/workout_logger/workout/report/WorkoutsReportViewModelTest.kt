package ir.mahozad.workout_logger.workout.report

import ir.mahozad.workout_logger.workout.WorkoutRepository
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn as returns
import org.mockito.kotlin.whenever as every

@ExtendWith(MockitoExtension::class)
class WorkoutsReportViewModelTest {

    @Mock lateinit var workoutRepository: WorkoutRepository

    @Test fun `Initially, getAllWorkouts should be empty`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = WorkoutsReportViewModel(workoutRepository, dispatcher)
        every(workoutRepository.getAllWorkouts()) returns emptyFlow()
        val workouts = viewModel.getAllWorkouts()
        assertThat(workouts.toList()).isEmpty()
    }
}

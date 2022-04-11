package ir.mahozad.workout_logger.workout

import ir.mahozad.workout_logger.data.Workout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.doReturn as returns
import org.mockito.kotlin.whenever as every

@ExtendWith(MockitoExtension::class)
class WorkoutRepositoryTest {

    @Mock lateinit var workoutDao: WorkoutDao

    @Test fun `Adding a workout should call the proper dao method`() = runTest {
        val workout = Workout(0, 23, 19)
        val repository = WorkoutRepository(workoutDao)
        repository.addWorkout(workout)
        verify(workoutDao).insert(workout)
    }

    @Test fun `After adding a workout getAllWorkouts should return it`() = runTest {
        val databaseWorkout = Workout(1, 24, 19)
        every(workoutDao.getAll()) returns flowOf(listOf(databaseWorkout))
        val repository = WorkoutRepository(workoutDao)
        val workout = Workout(0, 24, 19)
        repository.addWorkout(workout)
        val workouts = repository.getAllWorkouts()
        assertThat(workouts.first()).isEqualTo(listOf(workout.copy(id = 1)))
    }
}

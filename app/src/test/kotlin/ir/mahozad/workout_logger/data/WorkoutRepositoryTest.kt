package ir.mahozad.workout_logger.data

import ir.mahozad.workout_logger.data.dao.WorkoutDao
import ir.mahozad.workout_logger.data.entity.Workout
import ir.mahozad.workout_logger.data.repository.WorkoutRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class WorkoutRepositoryTest {

    @Mock lateinit var workoutDao: WorkoutDao

    @Test fun `Adding a workout should call the proper dao method`(): Unit = runBlocking {
        val workout = Workout(0, 23, 19)
        val repository = WorkoutRepository(workoutDao)
        repository.addWorkout(workout)
        verify(workoutDao).insert(workout)
    }
}

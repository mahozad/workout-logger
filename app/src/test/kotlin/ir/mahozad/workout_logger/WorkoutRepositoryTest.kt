package ir.mahozad.workout_logger

import ir.mahozad.Workout
import ir.mahozad.WorkoutDao
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

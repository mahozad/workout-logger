package ir.mahozad.workout_logger.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.data.dao.WorkoutDao
import ir.mahozad.workout_logger.data.entity.Workout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutDaoTest {

    private lateinit var workoutDao: WorkoutDao
    private lateinit var database: AppDatabase

    @Before fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        workoutDao = database.getWorkoutDao()
    }

    @After fun tearDown() {
        database.close()
    }

    @Test fun whenDatabaseIsEmptyGetAllShouldReturnEmpty(): Unit = runBlocking {
        val workouts = workoutDao.getAll().take(1)
        assertThat(workouts.first()).isNullOrEmpty()
    }

    @Test fun whenDatabaseIsEmptyInsertingASingleWorkoutShouldSucceed(): Unit = runBlocking {
        val workout = Workout(0, 23, 19)
        val workoutId = workoutDao.insert(workout)
        assertThat(workoutId).isEqualTo(1)
    }

    @Test fun insertingTwoWorkoutsShouldSucceed(): Unit = runBlocking {
        val workout1 = Workout(0, 23, 19)
        val workout2 = Workout(0, 20, 13)
        val workout1Id = workoutDao.insert(workout1)
        val workout2Id = workoutDao.insert(workout2)
        assertThat(workout1Id).isEqualTo(1)
        assertThat(workout2Id).isEqualTo(2)
    }

    @Test fun insertingAWorkoutWithExistingIdShouldFail(): Unit = runBlocking {
        val existingWorkouts = listOf(
            Workout(0, 23, 19),
            Workout(0, 20, 13),
            Workout(0, 24, 22),
            Workout(0, 13, 10)
        )
        for (workout in existingWorkouts) workoutDao.insert(workout)
        val workout = Workout(3, 10, 7)
        val exception = runCatching { workoutDao.insert(workout) }
            .exceptionOrNull() ?: error("The insertion did not fail")
        assertThat(exception).isInstanceOf(SQLiteConstraintException::class.java)
        assertThat(exception.message).contains("UNIQUE constraint failed")
    }
}

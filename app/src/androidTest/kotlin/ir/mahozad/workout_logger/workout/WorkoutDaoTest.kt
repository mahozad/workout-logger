package ir.mahozad.workout_logger.workout

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.data.AppDatabase
import ir.mahozad.workout_logger.data.Workout
import ir.mahozad.workout_logger.user.UserDao
import ir.mahozad.workout_logger.users
import ir.mahozad.workout_logger.workouts
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutDaoTest {

    private lateinit var workoutDao: WorkoutDao
    private lateinit var userDao: UserDao
    private lateinit var database: AppDatabase

    @Before fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        workoutDao = database.getWorkoutDao()
        userDao = database.getUserDao()
    }

    @After fun tearDown() {
        database.close()
    }

    @Test fun whenDatabaseIsEmptyGetAllShouldReturnEmpty() = runTest {
        val workouts = workoutDao.getAll().take(1)
        assertThat(workouts.first()).isNullOrEmpty()
    }

    @Test fun whenThereIsASingleWorkoutGetAllShouldReturnWorkoutWithProperUser() = runTest {
        userDao.insert(users[0])
        workoutDao.insert(workouts[0])
        val insertedWorkouts = workoutDao.getAll().take(1)
        assertThat(insertedWorkouts.first().entries.single().key).isEqualTo(users[0].copy(id = 1))
        assertThat(insertedWorkouts.first().entries.single().value.single()).isEqualTo(workouts[0].copy(id = 1))
    }

    @Test fun whenDatabaseIsEmptyInsertingASingleWorkoutShouldSucceed() = runTest {
        val workoutId = workoutDao.insert(workouts[0])
        assertThat(workoutId).isEqualTo(1)
    }

    @Test fun insertingTwoWorkoutsShouldSucceed() = runTest {
        val workout1Id = workoutDao.insert(workouts[0])
        val workout2Id = workoutDao.insert(workouts[1])
        assertThat(workout1Id).isEqualTo(1)
        assertThat(workout2Id).isEqualTo(2)
    }

    @Test fun insertingAWorkoutWithExistingIdShouldFail() = runTest {
        for (workout in workouts)
            workoutDao.insert(workout)
        val workout = Workout(3, 10, 7, 1)
        val exception =
            runCatching { workoutDao.insert(workout) }
            .exceptionOrNull() ?: error("The insertion did not fail")
        assertThat(exception).isInstanceOf(SQLiteConstraintException::class.java)
        assertThat(exception.message).contains("UNIQUE constraint failed")
    }
}

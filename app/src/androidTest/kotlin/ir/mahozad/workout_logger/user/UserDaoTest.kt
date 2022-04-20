package ir.mahozad.workout_logger.user

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.data.AppDatabase
import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.users
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var userDao: UserDao
    private lateinit var database: AppDatabase

    @Before fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        userDao = database.getUserDao()
    }

    @After fun tearDown() {
        database.close()
    }

    @Test fun whenDatabaseIsEmptyGetAllUsersShouldReturnEmpty() = runTest {
        val users = userDao.getAllUsers().take(1)
        assertThat(users.first()).isNullOrEmpty()
    }

    @Test fun whenDatabaseIsEmptyInsertingASingleUserShouldSucceed() = runTest {
        userDao.insert(users[0])
        val users = userDao.getAllUsers().first()
        assertThat(users).isEqualTo(listOf(users[0].copy(id = 1)))
    }

    @Test fun insertingTwoUsersShouldSucceed() = runTest {
        userDao.insert(users[0])
        userDao.insert(users[1])
        val users = userDao.getAllUsers().first()
        assertThat(users).isEqualTo(listOf(users[0].copy(id = 1), users[1].copy(id = 2)))
    }

    @Test fun insertingAUserEntityWithExistingIdShouldFail() = runTest {
        for (user in users.take(4))
            userDao.insert(user)
        val user = User(3, "John", "Smith", Sex.MALE, "24")
        val exception = runCatching { userDao.insert(user) }
            .exceptionOrNull() ?: error("The insertion did not fail")
        assertThat(exception).isInstanceOf(SQLiteConstraintException::class.java)
        assertThat(exception.message).contains("UNIQUE constraint failed")
    }
}

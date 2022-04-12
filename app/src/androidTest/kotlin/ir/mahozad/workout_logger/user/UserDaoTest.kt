package ir.mahozad.workout_logger.user

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.mahozad.workout_logger.data.AppDatabase
import ir.mahozad.workout_logger.data.Sex
import ir.mahozad.workout_logger.data.User
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
        val user = User(0, "John", "Smith", Sex.MALE, "24")
        userDao.insert(user)
        val users = userDao.getAllUsers().first()
        assertThat(users).isEqualTo(listOf(user.copy(id = 1)))
    }

    @Test fun insertingTwoUsersShouldSucceed() = runTest {
        val user1 = User(0, "John", "Smith", Sex.MALE, "24")
        val user2 = User(0, "Jane", "Smith", Sex.FEMALE, "25")
        userDao.insert(user1)
        userDao.insert(user2)
        val users = userDao.getAllUsers().first()
        assertThat(users).isEqualTo(listOf(user1.copy(id = 1), user2.copy(id = 2)))
    }

    @Test fun insertingAUserEntityWithExistingIdShouldFail() = runTest {
        val existingUsers = listOf(
            User(0, "A", "E", Sex.MALE, "24"),
            User(0, "B", "F", Sex.MALE, "24"),
            User(0, "C", "G", Sex.MALE, "24"),
            User(0, "D", "H", Sex.MALE, "24")
        )
        for (user in existingUsers) userDao.insert(user)
        val user = User(3, "John", "Smith", Sex.MALE, "24")
        val exception = runCatching { userDao.insert(user) }
            .exceptionOrNull() ?: error("The insertion did not fail")
        assertThat(exception).isInstanceOf(SQLiteConstraintException::class.java)
        assertThat(exception.message).contains("UNIQUE constraint failed")
    }
}

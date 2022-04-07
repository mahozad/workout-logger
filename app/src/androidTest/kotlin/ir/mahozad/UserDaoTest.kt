package ir.mahozad

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
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

    @Test fun whenDatabaseIsEmptyGetAllUsersShouldReturnEmpty(): Unit = runBlocking {
        val users = userDao.getAllUsers().take(1)
        assertThat(users.toList().single()).isNull()
    }

    @Test fun whenDatabaseIsEmptyInsertingASingleUserShouldSucceed(): Unit = runBlocking {
        val user = User(1, "John", "Smith", "Man", "24")
        userDao.insert(user)
        val users = userDao.getAllUsers().take(1)
        assertThat(users.toList()).isEqualTo(listOf(user))
    }
}

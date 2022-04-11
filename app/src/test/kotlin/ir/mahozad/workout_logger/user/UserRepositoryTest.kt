package ir.mahozad.workout_logger.user

import ir.mahozad.workout_logger.data.User
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn as returns
import org.mockito.kotlin.whenever as every

@ExtendWith(MockitoExtension::class)
class UserRepositoryTest {

    @Mock lateinit var userDao: UserDao

    @Test fun `Initially getAllUsers should return empty flow`() = runTest {
        every(userDao.getAllUsers()) returns emptyFlow()
        val repository = UserRepository(userDao)
        val users = repository.getAllUsers()
        assertThat(users.toList()).isEqualTo(emptyList<User>())
    }

    @Test fun `After adding a user getAllUsers should return it`() = runTest {
        val databaseUser = User(1, "John", "Smith", "Man", "24")
        every(userDao.getAllUsers()) returns flowOf(listOf(databaseUser))
        val repository = UserRepository(userDao)
        val user = User(0, "John", "Smith", "Man", "24")
        repository.addUser(user)
        val users = repository.getAllUsers()
        assertThat(users.first()).isEqualTo(listOf(user.copy(id = 1)))
    }
}

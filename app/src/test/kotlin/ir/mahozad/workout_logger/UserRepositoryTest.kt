package ir.mahozad.workout_logger

import ir.mahozad.User
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserRepositoryTest {
    @Test fun `Initially getAllUsers should return empty flow`(): Unit = runBlocking {
        val repository = UserRepository()
        val users = repository.getAllUsers()
        assertThat(users.toList()).isEqualTo(emptyList<User>())
    }

    @Test fun `After adding a user getAllUsers should return it`(): Unit = runBlocking {
        val repository = UserRepository()
        val user = User(0, "John", "Smith", "Man", "24")
        repository.addUser(user)
        val users = repository.getAllUsers()
        assertThat(users.toList()).isEqualTo(listOf(user))
    }
}

package ir.mahozad

import ir.mahozad.workout_logger.UserRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AddUserViewModelTest {

    @Test fun `Initially, getAllUsers should be empty`(): Unit = runBlocking {
        val userRepository = UserRepository()
        val viewModel = AddUserViewModel(userRepository)
        val users = viewModel.getAllUsers()
        assertThat(users.toList()).isEmpty()
    }

    @Test fun `Adding a new user should succeed`(): Unit = runBlocking {
        val userRepository = UserRepository()
        val viewModel = AddUserViewModel(userRepository)
        val user = User("John", "Smith", "Man", "24")
        val wasSuccessful = viewModel.addUser(user)
        val users = viewModel.getAllUsers()
        assertThat(wasSuccessful).isTrue()
        assertThat(users.toList()).isEqualTo(listOf(user))
    }
}

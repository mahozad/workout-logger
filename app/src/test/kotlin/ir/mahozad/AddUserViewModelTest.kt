package ir.mahozad

import ir.mahozad.workout_logger.UserRepository
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn as returns
import org.mockito.kotlin.whenever as every

@ExtendWith(MockitoExtension::class)
class AddUserViewModelTest {

    @Mock lateinit var userRepository: UserRepository

    @Test fun `Initially, getAllUsers should be empty`(): Unit = runBlocking {
        every(userRepository.getAllUsers()) returns emptyFlow()
        val viewModel = AddUserViewModel(userRepository)
        val users = viewModel.getAllUsers()
        assertThat(users.toList()).isEmpty()
    }

    @Test fun `Adding a new user should succeed`(): Unit = runBlocking {
        val repositoryUser = User(0, "John", "Smith", "Man", "24")
        every(userRepository.getAllUsers()) returns flowOf(repositoryUser)
        val user = User(0, "John", "Smith", "Man", "24")
        val viewModel = AddUserViewModel(userRepository)
        val wasSuccessful = viewModel.addUser(user)
        val users = viewModel.getAllUsers()
        assertThat(wasSuccessful).isTrue()
        assertThat(users.toList()).isEqualTo(listOf(user))
    }
}

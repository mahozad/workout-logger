package ir.mahozad.workout_logger.user.add

import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.user.UserRepository
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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

    @Test fun `Initially, getAllUsers should be empty`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = AddUserViewModel(userRepository, dispatcher)
        every(userRepository.getAllUsers()) returns emptyFlow()
        val users = viewModel.getAllUsers()
        assertThat(users.toList()).isEmpty()
    }

    @Test fun `Adding a new user should succeed`() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val repositoryUser = User(0, "John", "Smith", "Man", "24")
        every(userRepository.getAllUsers()) returns flowOf(listOf(repositoryUser))
        val user = User(0, "John", "Smith", "Man", "24")
        val viewModel = AddUserViewModel(userRepository, dispatcher)
        val wasSuccessful = viewModel.addUser(user)
        advanceUntilIdle() // Required: insert a delay in viewModel before repository call to see why
        val users = viewModel.getAllUsers()
        assertThat(wasSuccessful).isTrue()
        assertThat(users.first()).isEqualTo(listOf(user))
    }
}

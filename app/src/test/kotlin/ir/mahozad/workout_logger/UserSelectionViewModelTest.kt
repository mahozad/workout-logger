package ir.mahozad.workout_logger

import kotlinx.coroutines.flow.emptyFlow
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
class UserSelectionViewModelTest {

    @Mock lateinit var userRepository: UserRepository

    @Test fun `Initially, getAllUsers should be empty`(): Unit = runBlocking {
        every(userRepository.getAllUsers()) returns emptyFlow()
        val viewModel = UserSelectionViewModel(userRepository)
        val users = viewModel.getAllUsers()
        assertThat(users.toList()).isEmpty()
    }
}

package ir.mahozad

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AddUserViewModelTest {

    @Test fun `Initially, getAllUsers should be empty`(): Unit = runBlocking {
        val viewModel = AddUserViewModel()
        val users = viewModel.getAllUsers()
        assertThat(users.toList()).isEmpty()
    }

    @Test fun `Adding a new user should succeed`(): Unit = runBlocking {
        val viewModel = AddUserViewModel()
        val user = User("John", "Smith", "Man", "24")
        val wasSuccessful = viewModel.addUser(user)
        val users = viewModel.getAllUsers()
        assertThat(wasSuccessful).isTrue()
        assertThat(users.toList()).isEqualTo(listOf(user))
    }
}

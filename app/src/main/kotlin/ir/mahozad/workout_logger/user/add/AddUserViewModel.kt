package ir.mahozad.workout_logger.user.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.User
import ir.mahozad.workout_logger.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun addUser(user: User): Boolean {
        viewModelScope.launch(dispatcher) {
            userRepository.addUser(user)
        }
        return true
    }

    fun getAllUsers() = userRepository.getAllUsers()
}

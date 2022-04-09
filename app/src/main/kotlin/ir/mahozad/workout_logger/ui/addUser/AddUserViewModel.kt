package ir.mahozad.workout_logger.ui.addUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.entity.User
import ir.mahozad.workout_logger.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun addUser(user: User): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
        return true
    }

    fun getAllUsers() = userRepository.getAllUsers()
}

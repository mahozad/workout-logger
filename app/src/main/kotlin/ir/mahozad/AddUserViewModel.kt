package ir.mahozad

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.UserRepository
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    suspend fun addUser(user: User): Boolean {
        userRepository.addUser(user)
        return true
    }

    fun getAllUsers() = userRepository.getAllUsers()
}

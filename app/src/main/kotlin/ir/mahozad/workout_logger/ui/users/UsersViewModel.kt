package ir.mahozad.workout_logger.ui.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahozad.workout_logger.data.entity.User
import ir.mahozad.workout_logger.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun getAllUsers(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
}
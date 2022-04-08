package ir.mahozad.workout_logger

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSelectionViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun getAllUsers() = userRepository.getAllUsers()
}

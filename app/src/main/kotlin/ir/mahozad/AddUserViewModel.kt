package ir.mahozad

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(): ViewModel() {

    private var users = flowOf<User>()

    suspend fun addUser(user: User): Boolean {
        val newList = users.toList() + user
        users = flowOf(*newList.toTypedArray())
        return true
    }

    fun getAllUsers() = users
}

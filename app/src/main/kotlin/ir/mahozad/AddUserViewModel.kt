package ir.mahozad

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class AddUserViewModel: ViewModel() {

    private var users = flowOf<User>()

    suspend fun addUser(user: User): Boolean {
        val newList = users.toList() + user
        users = flowOf(*newList.toTypedArray())
        return true
    }

    fun getAllUsers() = users
}

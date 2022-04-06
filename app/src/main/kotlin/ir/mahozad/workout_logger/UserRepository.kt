package ir.mahozad.workout_logger

import ir.mahozad.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class UserRepository @Inject constructor() {

    private var users = flowOf<User>()

    fun getAllUsers(): Flow<User> {
        return users
    }

    suspend fun addUser(user: User) {
        val newList = users.toList() + user
        users = flowOf(*newList.toTypedArray())
    }
}

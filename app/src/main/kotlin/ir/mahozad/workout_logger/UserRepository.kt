package ir.mahozad.workout_logger

import ir.mahozad.User
import ir.mahozad.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers() = userDao.getAllUsers()

    suspend fun addUser(user: User) = userDao.insert(user)
}

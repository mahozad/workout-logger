package ir.mahozad.workout_logger.data.repository

import ir.mahozad.workout_logger.data.dao.UserDao
import ir.mahozad.workout_logger.data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers() = userDao.getAllUsers()

    suspend fun addUser(user: User) = userDao.insert(user)
}

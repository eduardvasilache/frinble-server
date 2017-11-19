package services

import models.User
import repositories.UserRepository
import javax.inject.Singleton

@Singleton
class UserService(private val userRepository: UserRepository) {

    suspend fun getAllUsers(): List<User> = userRepository.getAllUsers()

}
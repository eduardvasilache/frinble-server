package services

import models.User
import repositories.UsersRepository
import javax.inject.Singleton

@Singleton
class UsersService(private val usersRepository: UsersRepository) {

    suspend fun getAllUsers(): List<User> = usersRepository.getAllUsers()

    suspend fun createTestUser(): Boolean = usersRepository.createTestUser()

}
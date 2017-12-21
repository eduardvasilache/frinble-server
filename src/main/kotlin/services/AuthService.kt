package services

import http.exceptions.UserNotFoundException
import models.Account
import repositories.UsersRepository
import javax.inject.Singleton

@Singleton
class AuthService(private val usersRepository: UsersRepository) {

    suspend fun register(email: String, password: String): Account {
        val id = usersRepository.register(email, password)
        return usersRepository.getAccountById(id) ?: throw UserNotFoundException()
    }

}
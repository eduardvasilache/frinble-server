package services

import models.Account
import repositories.UsersRepository
import javax.inject.Singleton

@Singleton
class UsersService(private val usersRepository: UsersRepository) {

    suspend fun getAllUsers(): List<Account> = usersRepository.getAccounts()

}
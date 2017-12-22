package services

import entities.toAccount
import http.api.UserNotFoundException
import models.Account
import repositories.AccountsRepository
import javax.inject.Singleton

@Singleton
class UsersService(private val accountsRepository: AccountsRepository) {

    suspend fun getAllUsers(): List<Account> = accountsRepository.getAccounts().map { it.toAccount() }

    suspend fun getUserById(id: Long): Account = accountsRepository.getAccountById(id)?.toAccount()
            ?: throw UserNotFoundException()

}
package services

import entities.toAccount
import http.api.InvalidCredentialsException
import http.api.UserNotFoundException
import models.Account
import org.mindrot.jbcrypt.BCrypt
import repositories.AccountsRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class AuthService(private val accountsRepository: AccountsRepository,
                  private val emailService: EmailService) {

    suspend fun register(email: String, password: String): Account {
        val salt = BCrypt.gensalt()
        val hash = BCrypt.hashpw(password, salt)
        val emailConfirmationToken = UUID.randomUUID().toString()

        val id = accountsRepository.register(email, hash, emailConfirmationToken)
        val account = accountsRepository.getAccountById(id) ?: throw UserNotFoundException()

        if (account.email != null) {
            emailService.sendConfirmationEmail(account.email, emailConfirmationToken)
        }

        return account.toAccount()
    }

    suspend fun confirmEmail(token: String): Boolean {
        return accountsRepository.confirmEmail(token)
    }

    suspend fun login(email: String, password: String): Account {
        val account = accountsRepository.getAccountByEmail(email) ?: throw UserNotFoundException()

        if (!BCrypt.checkpw(password, account.passwordHash)) {
            throw InvalidCredentialsException()
        }

        return account.toAccount()
    }

}
package services

import entities.toAccount
import http.api.InvalidCredentialsException
import http.api.UserNotFoundException
import models.Account
import org.mindrot.jbcrypt.BCrypt
import repositories.AccountsRepository
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Singleton

@Singleton
class AuthService(private val accountsRepository: AccountsRepository,
                  private val emailService: EmailService) {

    suspend fun register(email: String, password: String): Account {
        val salt = BCrypt.gensalt()
        val hash = BCrypt.hashpw(password, salt)
        val token = UUID.randomUUID().toString()

        val userId = accountsRepository.register(email, hash, token)
        val account = accountsRepository.getAccountById(userId) ?: throw UserNotFoundException()

        if (account.email != null) {
            emailService.sendConfirmationEmail(account.email, token)
        }

        return account.toAccount()
    }

    suspend fun confirmEmail(emailToken: String): Boolean {
        return accountsRepository.confirmEmail(emailToken)
    }

    suspend fun login(email: String, password: String): Account {
        val account = accountsRepository.getAccountByEmail(email) ?: throw UserNotFoundException()

        if (!BCrypt.checkpw(password, account.passwordHash)) {
            throw InvalidCredentialsException()
        }

        return account.toAccount()
    }

    suspend fun sendUpdatePasswordChallenge(email: String): Boolean {
        val account = accountsRepository.getAccountByEmail(email) ?: throw UserNotFoundException()
        val challenge = (100000..999999).random().toString()
        val challengeExpiryDate = ZonedDateTime.now().plusHours(1)

        accountsRepository.saveUpdatePasswordChallenge(account.id, challenge, challengeExpiryDate)

        if (account.email != null) {
            emailService.sendUpdatePasswordEmail(account.email, challenge)
            return true
        }

        return false
    }

    suspend fun updatePassword(email: String, password: String, token: String): Boolean {
        val account = accountsRepository.getAccountByEmail(email) ?: throw UserNotFoundException()

        if (account.updatePasswordChallenge == null || account.updatePasswordChallengeExpiryDate == null) {
            return false
        }

        if (ZonedDateTime.now().isAfter(account.updatePasswordChallengeExpiryDate)) {
            return false
        }

        if (account.updatePasswordChallenge == token) {
            val salt = BCrypt.gensalt()
            val hash = BCrypt.hashpw(password, salt)
            return accountsRepository.updatePassword(email, hash)
        }

        return false
    }

}

private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start
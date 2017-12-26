package services

import io.vertx.ext.mail.MailClient
import io.vertx.ext.mail.MailMessage
import utils.sendMail
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class EmailService(private val mailClientProvider: Provider<MailClient>) {

    suspend fun sendConfirmationEmail(email: String, token: String) {
        val message = MailMessage()
        message.subject = "Confirm you email address"
        message.from = "hello@frinble.com (Frinble)"
        message.to = listOf(email)
        message.html = "The token to confirm your email address is $token"

        sendEmail(message)
    }

    suspend fun sendResetPasswordEmail(email: String, token: String) {

    }

    private suspend fun sendEmail(message: MailMessage) {
        val mailClient = mailClientProvider.get()
        mailClient.sendMail(message)
    }

}
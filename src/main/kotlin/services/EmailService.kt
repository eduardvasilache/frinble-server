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

    suspend fun sendUpdatePasswordEmail(email: String, code: String) {
        val message = MailMessage()
        message.subject = "Instructions to change your password"
        message.from = "hello@frinble.com (Frinble)"
        message.to = listOf("eduard.vasilache@mready.net")
        message.html = "The code to change your password is $code"

        sendEmail(message)
    }

    private suspend fun sendEmail(message: MailMessage) {
        val mailClient = mailClientProvider.get()
        mailClient.sendMail(message)
    }

}
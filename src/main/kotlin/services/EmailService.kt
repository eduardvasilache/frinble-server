package services

import javax.inject.Singleton

@Singleton
class EmailService {

    suspend fun sendConfirmationEmail(email: String, token: String) {
        sendEmail()
    }

    suspend fun sendResetPasswordEmail(email: String, token: String) {
        sendEmail()
    }

    private suspend fun sendEmail() {

    }

}
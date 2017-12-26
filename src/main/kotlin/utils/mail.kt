package utils

import io.vertx.ext.mail.MailClient
import io.vertx.ext.mail.MailMessage
import io.vertx.ext.mail.MailResult
import io.vertx.kotlin.coroutines.awaitResult

suspend fun MailClient.sendMail(email: MailMessage) = awaitResult<MailResult> { sendMail(email, it) }
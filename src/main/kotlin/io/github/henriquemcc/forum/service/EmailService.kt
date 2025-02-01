package io.github.henriquemcc.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun notificar() {
        val message = SimpleMailMessage()
        message.subject = ""
        message.text = ""
        message.setTo("")
        javaMailSender.send(message)
    }
}
package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun notificar(autor: Usuario, topico: Topico) {
        val message = SimpleMailMessage()
        message.subject = "[Alura] Resposta ao tópico \"${topico.titulo}\" recebida"
        message.text = "Olá ${autor.nome}, o seu tópico \"${topico.titulo}\" foi respondido. Vamos lá conferir?"
        message.setTo(autor.email)
        javaMailSender.send(message)
    }
}
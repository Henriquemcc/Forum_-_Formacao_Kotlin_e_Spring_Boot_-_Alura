package io.github.henriquemcc.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import java.time.LocalDateTime

@Entity
@EnableAutoConfiguration
data class Resposta(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val autor: Usuario,

    @ManyToOne
    var topico: Topico? = null,

    val solucao: Boolean,
)

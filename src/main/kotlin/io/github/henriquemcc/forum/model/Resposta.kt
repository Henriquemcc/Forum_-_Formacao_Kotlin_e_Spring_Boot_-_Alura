package io.github.henriquemcc.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Resposta(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val autor: Usuario? = null,

    @ManyToOne
    var topico: Topico? = null,

    val solucao: Boolean? = null,
)

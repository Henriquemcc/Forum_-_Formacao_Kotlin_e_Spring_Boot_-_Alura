package io.github.henriquemcc.forum.dto

import io.github.henriquemcc.forum.model.StatusTopico
import java.time.LocalDateTime

class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
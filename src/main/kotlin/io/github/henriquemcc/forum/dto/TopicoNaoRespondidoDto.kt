package io.github.henriquemcc.forum.dto

import java.time.LocalDateTime

data class TopicoNaoRespondidoDto(
    val titulo: String,
    val curso: String,
    val autor: String,
    val dataCriacao: LocalDateTime
)
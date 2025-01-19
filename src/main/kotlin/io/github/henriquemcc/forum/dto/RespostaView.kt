package io.github.henriquemcc.forum.dto

import java.time.LocalDateTime

class RespostaView(
    val id: Long,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val solucao: Boolean?,
)
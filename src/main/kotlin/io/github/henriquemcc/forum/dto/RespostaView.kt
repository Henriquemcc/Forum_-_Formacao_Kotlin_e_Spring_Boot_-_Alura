package io.github.henriquemcc.forum.dto

import java.time.LocalDateTime

class RespostaView(
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val solucao: Boolean,
)
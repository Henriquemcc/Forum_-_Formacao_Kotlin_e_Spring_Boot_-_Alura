package io.github.henriquemcc.forum.dto

import jakarta.validation.constraints.NotEmpty

data class AtualizarRespostaForm(
    @field:NotEmpty
    val mensagem: String,
)
package io.github.henriquemcc.forum.dto

import io.github.henriquemcc.forum.model.Topico
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NovaRespostaForm(

    @field:NotEmpty
    val mensagem: String,

    @field:NotNull
    val idAutor: Long,

    val idTopico: Long? = null
)
package io.github.henriquemcc.forum.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class Curso(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String,
    val categoria: String
)

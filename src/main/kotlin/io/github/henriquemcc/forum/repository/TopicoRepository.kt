package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
    fun findByCursoNome(nome: String, paginacao: Pageable): Page<Topico>
}
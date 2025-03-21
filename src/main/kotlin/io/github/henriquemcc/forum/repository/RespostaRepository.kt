package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page

interface RespostaRepository : JpaRepository<Resposta, Long> {
    fun findByTopicoTitulo(titulo: String, paginacao: Pageable): Page<Resposta>
}
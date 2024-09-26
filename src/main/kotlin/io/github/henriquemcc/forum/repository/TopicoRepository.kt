package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.dto.TopicoNaoRespondidoDto
import io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {
    fun findByCursoNome(nome: String, paginacao: Pageable): Page<Topico>

    @Query("SELECT new io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>

    @Query("SELECT new io.github.henriquemcc.forum.dto.TopicoNaoRespondidoDto(t.titulo, t.curso.nome, t.autor.nome, t.dataCriacao) from Topico t where t.respostas is empty ORDER BY t.dataCriacao")
    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto>
}
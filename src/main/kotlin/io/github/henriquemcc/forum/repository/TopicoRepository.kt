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

//    @Query("SELECT new io.github.henriquemcc.forum.dto.TopicoNaoRespondidoDto(t.titulo, curso.nome, autor.nome, t.dataCriacao)" +
//            "FROM Topico t" +
//            "JOIN t.curso curso" +
//            "JOIN t.autor autor" +
//            "WHERE t.respostas IS EMPTY" +
//            "GROUP BY t.dataCriacao"
//            )
//    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto>

//    @Query("SELECT t from Topico t where t.respostas is empty")
//    fun relatorioTopicosNaoRespondidos(): List<Topico>

//    @Query("SELECT new io.github.henriquemcc.forum.dto.TopicoView(t.id, t.titulo, t.mensagem, t.status, t.dataCriacao) from Topico t where t.respostas is empty")
//    fun relatorioTopicosNaoRespondidos(): List<TopicoView>

    @Query("SELECT new io.github.henriquemcc.forum.dto.TopicoNaoRespondidoDto(t.titulo, t.curso.nome, t.autor.nome, t.dataCriacao) from Topico t where t.respostas is empty ORDER BY t.dataCriacao")
    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto>
}
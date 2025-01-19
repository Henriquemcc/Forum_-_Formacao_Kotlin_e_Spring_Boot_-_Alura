package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.TopicoNaoRespondidoDto
import io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto
import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val notFoundMessage: String = "Topico nao encontrado!",
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<Topico> {
        return when {
            nomeCurso == null -> topicoRepository.findAll(paginacao)
            else -> topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }
    }

    fun buscarPorId(id: Long): Topico {
        return topicoRepository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun cadastrar(topico: Topico): Topico {
        topicoRepository.save(topico)
        return topico
    }

    fun atualizar(topico: Topico, idTopico: Long): Topico {
        val topicoAnterior = topicoRepository.findById(idTopico).orElseThrow { NotFoundException(notFoundMessage) }
        topicoAnterior.titulo = topico.titulo
        topicoAnterior.mensagem = topico.mensagem
        return topicoAnterior
    }

    fun deletar(id: Long) {
        topicoRepository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoRepository.relatorio()
    }

    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto> {
        return topicoRepository.relatorioTopicosNaoRespondidos()
    }
}
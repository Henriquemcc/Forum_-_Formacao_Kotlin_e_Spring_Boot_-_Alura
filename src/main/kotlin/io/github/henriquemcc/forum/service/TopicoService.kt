package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.*
import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.mapper.TopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico nao encontrado!",
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t -> topicoViewMapper.map(t) }
    }

    fun buscarPorIdTopico(id: Long): Topico {
        return repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun buscarPorIdTopicoView(id: Long): TopicoView {
        return topicoViewMapper.map(buscarPorIdTopico(id))
    }

    fun cadastrar(topico: Topico): Topico {
        repository.save(topico)
        return topico
    }

    fun cadastrar(topico: NovoTopicoForm): TopicoView {
        val topicoCadastrado = cadastrar(topicoFormMapper.map(topico))
        return topicoViewMapper.map(topicoCadastrado)
    }

    fun atualizar(form: AtualizarTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }

    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto> {
        return repository.relatorioTopicosNaoRespondidos()
    }
}
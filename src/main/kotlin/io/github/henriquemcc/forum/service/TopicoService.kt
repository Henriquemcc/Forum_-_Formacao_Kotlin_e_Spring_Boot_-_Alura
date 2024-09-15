package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarTopicoForm
import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.mapper.TopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.repository.TopicoRepository
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico nao encontrado!",
) {

    fun listarListTopico(): List<Topico> {
        return repository.findAll()
    }

    fun listarListTopicoView(): List<TopicoView> {
        return listarListTopico().map { t -> topicoViewMapper.map(t) }
    }

    fun buscarPorIdTopico(id: Long): Topico {
        return repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
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
}
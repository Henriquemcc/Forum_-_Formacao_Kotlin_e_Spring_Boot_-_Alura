package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarTopicoForm
import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.mapper.TopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicos: MutableList<Topico> = mutableListOf(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listarListTopico(): List<Topico> {
        return topicos
    }

    fun listarListTopicoView(): List<TopicoView> {
        return listarListTopico().map { t -> topicoViewMapper.map(t) }
    }

    fun buscarPorIdTopico(id: Long): Topico {
        return topicos.first {
            t -> t.id == id
        }
    }

    fun buscarPorIdTopicoView(id: Long): TopicoView {
        return topicoViewMapper.map(buscarPorIdTopico(id))
    }

    fun cadastrar(topico: Topico) {
        if (topico.id == null)
            topico.id = topicos.size.toLong() + 1
        topicos.add(topico)
    }

    fun cadastrar(topico: NovoTopicoForm) {
        cadastrar(topicoFormMapper.map(topico))
    }

    fun atualizar(form: AtualizarTopicoForm) {
        val topico = topicos.first {
            it.id == form.id
        }
        topicos.remove(topico)
        topicos.add(Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        ))
    }

    fun deletar(id: Long) {
        val topico = topicos.first {
            it.id == id
        }
        topicos.remove(topico)
    }
}
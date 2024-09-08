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

    fun cadastrar(topico: Topico): Topico {
        if (topico.id == null)
            topico.id = topicos.size.toLong() + 1
        topicos.add(topico)
        return topico
    }

    fun cadastrar(topico: NovoTopicoForm): TopicoView {
        val topicoCadastrado = cadastrar(topicoFormMapper.map(topico))
        return topicoViewMapper.map(topicoCadastrado)
    }

    fun atualizar(form: AtualizarTopicoForm): TopicoView {
        val topicoRemovido = topicos.first {
            it.id == form.id
        }
        topicos.remove(topicoRemovido)
        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topicoRemovido.autor,
            curso = topicoRemovido.curso,
            respostas = topicoRemovido.respostas,
            status = topicoRemovido.status,
            dataCriacao = topicoRemovido.dataCriacao
        )
        topicos.add(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.first {
            it.id == id
        }
        topicos.remove(topico)
    }
}
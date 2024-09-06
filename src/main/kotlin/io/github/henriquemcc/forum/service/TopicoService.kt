package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.mapper.TopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicos: MutableList<Topico> = mutableListOf(),
) {

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.first {
            t -> t.id == id
        }
    }

    fun cadastrar(topico: Topico) {
        if (topico.id == null)
            topico.id = topicos.size.toLong() + 1
        topicos.add(topico)
    }
}
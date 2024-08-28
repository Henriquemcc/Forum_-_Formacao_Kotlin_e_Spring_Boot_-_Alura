package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: List<Topico> = mutableListOf()) {

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.first {
            t -> t.id == id
        }
    }

    fun cadastrar(topico: Topico) {
        topicos.plus(topico)
    }
}
package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService {
    fun listar(): List<Topico> {
        val topico = Topico(
            id = 1,
            titulo = "Dúvida Kotlin",
            mensagem = "Variáveis no Kotlin",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação",
            ),
            autor = Usuario(
                id = 1,
                nome = "Ana da Silva",
                email = "ana@email.com"
            ),
        )

        return listOf<Topico>(topico, topico, topico)
    }
}
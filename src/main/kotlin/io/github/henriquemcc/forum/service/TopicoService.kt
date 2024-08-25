package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
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

        val topico2 = Topico(
            id = 2,
            titulo = "Dúvida Kotlin 2",
            mensagem = "Variáveis no Kotlin 2",
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

        val topico3 = Topico(
            id = 3,
            titulo = "Dúvida Kotlin 3",
            mensagem = "Variáveis no Kotlin 3",
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

        topicos = listOf(topico, topico2, topico3)
    }

    fun listar(): List<Topico> {
        return topicos
    }
}
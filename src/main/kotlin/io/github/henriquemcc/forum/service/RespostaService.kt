package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostas: MutableList<Resposta> = mutableListOf(),
) {

    init {
        val autor = Usuario(id = 1, nome = "Ana da Silva", email = "ana@email.com")
        val curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação")
        val topico = Topico(id = 1, titulo = "Duvida Kotlin", "Variáveis no Kotlin", curso = curso, autor = autor)
        respostas.add(Resposta(id = 1, mensagem = "Resposta 1", autor = autor, topico = topico, solucao = false))
        respostas.add(Resposta(id = 2, mensagem = "Resposta 2", autor = autor, topico = topico, solucao = false))
    }

    fun listar(): List<Resposta> {
        return respostas
    }

    fun buscarPorId(id: Long): Resposta {
        return respostas.first { r -> r.id == id }
    }
}
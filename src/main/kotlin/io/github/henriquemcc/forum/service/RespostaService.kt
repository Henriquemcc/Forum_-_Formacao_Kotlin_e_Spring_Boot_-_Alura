package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Resposta
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostas: MutableList<Resposta> = mutableListOf(),
    private val topicoService: TopicoService,
) {

    fun listarPorIdTopico(idTopico: Long): List<Resposta> {
        return respostas.filter { r-> r.topico == topicoService.buscarPorId(idTopico) }
    }

    fun buscarPorIdResposta(idTopico: Long, idResposta: Long): Resposta {
        return respostas.first { r -> (r.topico == topicoService.buscarPorId(idTopico) && r.id == idResposta) }
    }

    fun cadastrar(resposta: Resposta) {
        if (resposta.id == null)
            resposta.id = respostas.size.toLong() + 1
        respostas.add(resposta)
    }
}
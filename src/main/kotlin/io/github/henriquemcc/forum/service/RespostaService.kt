package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.mapper.RespostaFormMapper
import io.github.henriquemcc.forum.mapper.RespostaViewMapper
import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostas: MutableList<Resposta> = mutableListOf(),
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper,
) {

    fun listar(): List<RespostaView> {
        return respostas.map {
            r -> respostaViewMapper.map(r)
        }
    }

    fun buscarPorId(id: Long): RespostaView {
        val resposta = respostas.first { r -> r.id == id }
        return respostaViewMapper.map(resposta)
    }

    fun cadastrar(dto: NovaRespostaForm) {
        respostas.add(respostaFormMapper.map(dto))
    }
}
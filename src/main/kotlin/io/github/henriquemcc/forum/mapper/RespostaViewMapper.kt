package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper : Mapper<Resposta, RespostaView> {

    override fun map(t: Resposta): RespostaView {
        return RespostaView(
            id = t.id ?: 0,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            solucao = t.solucao
        )
    }
}
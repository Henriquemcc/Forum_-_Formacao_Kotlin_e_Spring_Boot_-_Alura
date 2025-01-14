package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class AtualizarRespostaFormMapper: Mapper<AtualizarRespostaForm, Resposta> {
    override fun map(t: AtualizarRespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem
        )
    }
}
package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.AtualizarTopicoForm
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class AtualizarTopicoFormMapper: Mapper<AtualizarTopicoForm, Topico> {
    override fun map(t: AtualizarTopicoForm): Topico {
        return Topico(
            mensagem = t.mensagem,
            titulo = t.titulo
        )
    }
}
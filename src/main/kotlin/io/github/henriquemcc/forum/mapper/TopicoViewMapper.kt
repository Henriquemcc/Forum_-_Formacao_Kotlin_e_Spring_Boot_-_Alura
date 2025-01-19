package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao,
            dataAlteracao = t.dataAlteracao
        )
    }
}
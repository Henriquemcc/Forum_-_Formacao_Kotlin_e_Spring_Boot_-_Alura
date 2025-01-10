package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.service.TopicoService
import io.github.henriquemcc.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    val usuarioService: UsuarioService,
    val topicoService: TopicoService,
) : Mapper<NovaRespostaForm, Resposta> {

    override fun map(t: NovaRespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem,
            autor = usuarioService.buscarPorId(t.idAutor),
            solucao = false
        )
    }
}
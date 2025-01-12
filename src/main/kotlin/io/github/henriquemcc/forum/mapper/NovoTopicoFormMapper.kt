package io.github.henriquemcc.forum.mapper

import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.model.Topico
import io.github.henriquemcc.forum.service.CursoService
import io.github.henriquemcc.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) : Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }
}

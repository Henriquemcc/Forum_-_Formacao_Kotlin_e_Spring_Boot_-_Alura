package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.NovoTopicoDto
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicos: MutableList<Topico> = mutableListOf(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) {

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.first {
            t -> t.id == id
        }
    }

    fun cadastrar(dto: NovoTopicoDto) {
        topicos.add(Topico(
            id = topicos.size.toLong() + 1,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        ))
    }
}
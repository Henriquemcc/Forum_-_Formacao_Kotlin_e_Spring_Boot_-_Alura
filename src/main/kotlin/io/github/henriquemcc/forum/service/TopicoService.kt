package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicos: MutableList<Topico> = mutableListOf(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) {

    fun listar(): List<TopicoView> {
        return topicos.map {
            t -> TopicoView(id = t.id, titulo = t.titulo, mensagem = t.mensagem,
            status = t.status, dataCriacao = t.dataCriacao)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.first {
            t -> t.id == id
        }
        return TopicoView(id = topico.id, titulo = topico.titulo, mensagem = topico.mensagem, status = topico.status, dataCriacao = topico.dataCriacao)
    }

    fun cadastrar(dto: NovoTopicoForm) {
        topicos.add(Topico(
            id = topicos.size.toLong() + 1,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        ))
    }
}
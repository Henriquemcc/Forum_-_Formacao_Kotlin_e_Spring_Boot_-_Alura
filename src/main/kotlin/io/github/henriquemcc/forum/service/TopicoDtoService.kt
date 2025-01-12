package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarTopicoForm
import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.mapper.AtualizarTopicoFormMapper
import io.github.henriquemcc.forum.mapper.NovoTopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoDtoService(
    private val topicoService: TopicoService,
    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoFormMapper: NovoTopicoFormMapper,
    private val atualizarTopicoFormMapper: AtualizarTopicoFormMapper
) {
    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        return topicoService.listar(nomeCurso, paginacao).map { t -> topicoViewMapper.map(t) }
    }

    fun buscarPorId(id: Long): TopicoView {
        return topicoViewMapper.map(topicoService.buscarPorId(id))
    }

    fun cadastrar(novoTopicoForm: NovoTopicoForm): TopicoView {
        return topicoViewMapper.map(topicoService.cadastrar(novoTopicoFormMapper.map(novoTopicoForm)))
    }

    fun atualizar(atualizarTopicoForm: AtualizarTopicoForm, idTopico: Long): TopicoView {
        return topicoViewMapper.map(topicoService.atualizar(atualizarTopicoFormMapper.map(atualizarTopicoForm), idTopico))
    }

    fun deletar(id: Long) {
        topicoService.deletar(id)
    }
}
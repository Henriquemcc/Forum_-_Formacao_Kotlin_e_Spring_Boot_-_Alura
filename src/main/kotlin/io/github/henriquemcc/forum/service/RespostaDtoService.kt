package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import io.github.henriquemcc.forum.mapper.AtualizarRespostaFormMapper
import io.github.henriquemcc.forum.mapper.NovaRespostaFormMapper
import io.github.henriquemcc.forum.mapper.RespostaViewMapper
import org.springframework.stereotype.Service

@Service
class RespostaDtoService(
    private val respostaService: RespostaService,
    private val respostaViewMapper: RespostaViewMapper,
    private val novaRespostaFormMapper: NovaRespostaFormMapper,
    private val atualizarRespostaFormMapper: AtualizarRespostaFormMapper,
    private val topicoService: TopicoService
) {
    fun listarPorIdTopico(idTopico: Long): List<RespostaView> {
        return respostaService.listarPorIdTopico(idTopico).map { r -> respostaViewMapper.map(r) }
    }

    fun buscarPorIdRespostaIdTopico(idTopico: Long, idResposta: Long): RespostaView {
        return respostaViewMapper.map(respostaService.buscarPorIdRespostaIdTopico(idTopico, idResposta))
    }

    fun cadastrar(novaRespostaForm: NovaRespostaForm, idTopico: Long? = null): RespostaView {
        val resposta = novaRespostaFormMapper.map(novaRespostaForm)
        if (idTopico != null) resposta.topico = topicoService.buscarPorId(idTopico)
        return respostaViewMapper.map(respostaService.cadastrar(resposta))
    }

    fun atualizar(atualizarRespostaForm: AtualizarRespostaForm, idResposta: Long): RespostaView {
        return respostaViewMapper.map(respostaService.atualizar(atualizarRespostaFormMapper.map(atualizarRespostaForm), idResposta))
    }

    fun deletar(id: Long) {
        respostaService.deletar(id)
    }

    fun listar(tituloTopico: String?, paginacao: Pageable): Page<RespostaView> {
        return respostaService.listar(tituloTopico, paginacao).map { r -> respostaViewMapper.map(r) }
    }

    fun buscarPorId(id: Long): RespostaView {
        return respostaViewMapper.map(respostaService.buscarPorId(id))
    }
}
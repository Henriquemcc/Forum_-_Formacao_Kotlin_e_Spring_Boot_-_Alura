package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
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

    fun buscarPorId(idTopico: Long, idResposta: Long): RespostaView {
        return respostaViewMapper.map(respostaService.buscarPorId(idTopico, idResposta))
    }

    fun cadastrar(novaRespostaForm: NovaRespostaForm, idTopico: Long): RespostaView {
        val resposta = novaRespostaFormMapper.map(novaRespostaForm)
        resposta.topico = topicoService.buscarPorId(idTopico)
        return respostaViewMapper.map(respostaService.cadastrar(resposta))
    }

    fun atualizar(atualizarRespostaForm: AtualizarRespostaForm, idResposta: Long): RespostaView {
        return respostaViewMapper.map(respostaService.atualizar(atualizarRespostaFormMapper.map(atualizarRespostaForm), idResposta))
    }

    fun deletar(id: Long) {
        respostaService.deletar(id)
    }
}
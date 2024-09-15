package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.mapper.RespostaFormMapper
import io.github.henriquemcc.forum.mapper.RespostaViewMapper
import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val topicoService: TopicoService,
    private val respostaViewMapper: RespostaViewMapper,
    private val respostaFormMapper: RespostaFormMapper,
    private val notFoundMessage: String = "Resposta nao encontrada!",
) {

    fun listarPorIdTopicoListResposta(idTopico: Long): List<Resposta> {
        return repository.findAll().filter { r-> r.topico == topicoService.buscarPorIdTopico(idTopico) }
    }

    fun listarPorIdTopicoListRespostaView(idTopico: Long): List<RespostaView> {
        return listarPorIdTopicoListResposta(idTopico).map { r -> respostaViewMapper.map(r) }
    }

    fun buscarPorIdResposta(idTopico: Long, idResposta: Long): Resposta {
        return repository.findAll().first { r -> (r.topico == topicoService.buscarPorIdTopico(idTopico) && r.id == idResposta) }
    }

    fun buscarPorIdRespostaView(idTopico: Long, idResposta: Long): RespostaView {
        return respostaViewMapper.map(buscarPorIdResposta(idTopico, idResposta))
    }

    fun cadastrar(form: NovaRespostaForm, idTopico: Long): RespostaView {
        val resposta = respostaFormMapper.map(form)
        resposta.topico = topicoService.buscarPorIdTopico(idTopico)
        val respostaCadastrada = cadastrar(resposta)
        return respostaViewMapper.map(respostaCadastrada)
    }

    fun cadastrar(resposta: Resposta): Resposta {
        repository.save(resposta)
        return resposta
    }

    fun atualizar(form: AtualizarRespostaForm, idResposta: Long): RespostaView? {
        val resposta = repository.findById(idResposta).orElseThrow{NotFoundException(notFoundMessage)}
        resposta.mensagem = form.mensagem
        return respostaViewMapper.map(resposta)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}
package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.repository.RespostaRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val notFoundMessage: String = "Resposta nao encontrada!",
    private val topicoService: TopicoService
) {
    fun listarPorIdTopico(idTopico: Long): List<Resposta> {
        return respostaRepository.findAll().filter { r -> r.topico == topicoService.buscarPorId(idTopico) }
    }

    fun buscarPorIdRespostaIdTopico(idTopico: Long, idResposta: Long): Resposta {
        return respostaRepository.findAll().first{ r -> (r.topico == topicoService.buscarPorId(idTopico) && r.id == idResposta)}
    }

    fun cadastrar(resposta: Resposta): Resposta {
        respostaRepository.save(resposta)
        return resposta
    }

    fun atualizar(resposta: Resposta, idResposta: Long): Resposta {
        val respostaAnterior = respostaRepository.findById(idResposta).orElseThrow { NotFoundException(notFoundMessage) }
        respostaAnterior.mensagem = resposta.mensagem
        return respostaAnterior
    }

    fun deletar(id: Long) {
        respostaRepository.deleteById(id)
    }

    fun listar(tituloTopico: String?, paginacao: Pageable): Page<Resposta> {
        return when {
            tituloTopico == null -> respostaRepository.findAll(paginacao)
            else -> respostaRepository.findByTopicoTitulo(tituloTopico, paginacao)
        }
    }

    fun buscarPorId(id: Long): Resposta {
        return respostaRepository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
    }
}
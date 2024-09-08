package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.mapper.RespostaFormMapper
import io.github.henriquemcc.forum.mapper.RespostaViewMapper
import io.github.henriquemcc.forum.model.Resposta
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostas: MutableList<Resposta> = mutableListOf(),
    private val topicoService: TopicoService,
    private val respostaViewMapper: RespostaViewMapper,
    private val respostaFormMapper: RespostaFormMapper
) {

    fun listarPorIdTopicoListResposta(idTopico: Long): List<Resposta> {
        return respostas.filter { r-> r.topico == topicoService.buscarPorIdTopico(idTopico) }
    }

    fun listarPorIdTopicoListRespostaView(idTopico: Long): List<RespostaView> {
        return listarPorIdTopicoListResposta(idTopico).map { r -> respostaViewMapper.map(r) }
    }

    fun buscarPorIdResposta(idTopico: Long, idResposta: Long): Resposta {
        return respostas.first { r -> (r.topico == topicoService.buscarPorIdTopico(idTopico) && r.id == idResposta) }
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
        if (resposta.id == null)
            resposta.id = respostas.size.toLong() + 1
        respostas.add(resposta)
        return resposta
    }

    fun atualizar(form: AtualizarRespostaForm, idResposta: Long): RespostaView? {
        val respostaRemovida = respostas.first {
            it.id == idResposta
        }
        respostas.remove(respostaRemovida)
        val respostaAtualizada = Resposta(
            id = respostaRemovida.id,
            mensagem = form.mensagem,
            dataCriacao = respostaRemovida.dataCriacao,
            autor = respostaRemovida.autor,
            topico = respostaRemovida.topico,
            solucao = respostaRemovida.solucao
        )
        respostas.add(respostaAtualizada)

        return respostaViewMapper.map(respostaAtualizada)
    }

    fun deletar(id: Long) {
        val resposta = respostas.first {
            it.id == id
        }
        respostas.remove(resposta)
    }
}
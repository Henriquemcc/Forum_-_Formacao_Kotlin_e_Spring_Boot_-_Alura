package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.mapper.RespostaFormMapper
import io.github.henriquemcc.forum.mapper.RespostaViewMapper
import io.github.henriquemcc.forum.service.RespostaService
import io.github.henriquemcc.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos/{idTopico}/respostas")
class RespostaController(
    private val service: RespostaService,
    private val respostaViewMapper: RespostaViewMapper,
    private val respostaFormMapper: RespostaFormMapper,
    private val topicoService: TopicoService,
    ) {

    @GetMapping
    fun listarPorIdTopico(@PathVariable idTopico: Long): List<RespostaView> {
        return service.listarPorIdTopico(idTopico).map { r -> respostaViewMapper.map(r) }
    }

    @GetMapping("/{idResposta}")
    fun buscarPorIdResposta(@PathVariable idTopico: Long, @PathVariable idResposta: Long): RespostaView {
        return respostaViewMapper.map(service.buscarPorIdResposta(idTopico, idResposta))
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: NovaRespostaForm, @PathVariable idTopico: Long) {
        val resposta = respostaFormMapper.map(form)
        resposta.topico = topicoService.buscarPorId(idTopico)
        service.cadastrar(resposta)
    }
}
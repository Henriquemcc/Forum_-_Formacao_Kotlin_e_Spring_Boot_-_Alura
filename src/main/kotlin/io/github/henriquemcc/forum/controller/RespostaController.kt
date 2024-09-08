package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos/{idTopico}/respostas")
class RespostaController(private val service: RespostaService) {

    @GetMapping
    fun listarPorIdTopico(@PathVariable idTopico: Long): List<RespostaView> {
        return service.listarPorIdTopicoListRespostaView(idTopico)
    }

    @GetMapping("/{idResposta}")
    fun buscarPorIdResposta(@PathVariable idTopico: Long, @PathVariable idResposta: Long): RespostaView {
        return service.buscarPorIdRespostaView(idTopico, idResposta)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: NovaRespostaForm, @PathVariable idTopico: Long) {
        service.cadastrar(form, idTopico)
    }
}
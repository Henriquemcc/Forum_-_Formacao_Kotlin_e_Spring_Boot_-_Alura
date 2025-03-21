package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.service.RespostaDtoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos/{idTopico}/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostaPorTopicoController(private val respostaDtoService: RespostaDtoService) {

    @GetMapping
    fun listarPorIdTopico(@PathVariable idTopico: Long): List<RespostaView> {
        return respostaDtoService.listarPorIdTopico(idTopico)
    }

    @GetMapping("/{idResposta}")
    fun buscarPorIdResposta(@PathVariable idTopico: Long, @PathVariable idResposta: Long): RespostaView {
        return respostaDtoService.buscarPorIdRespostaIdTopico(idTopico, idResposta)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovaRespostaForm,
        @PathVariable idTopico: Long,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RespostaView> {
        val respostaView = respostaDtoService.cadastrar(form, idTopico)
        val uri = uriBuilder.path("/topicos/${idTopico}/respostas").build().toUri()
        return ResponseEntity.created(uri).body(respostaView)
    }

    @PutMapping("/{idResposta}")
    @Transactional
    fun atualizar(
        @RequestBody @Valid form: AtualizarRespostaForm,
        @PathVariable idResposta: Long
    ): ResponseEntity<RespostaView> {
        val respostaView = respostaDtoService.atualizar(form, idResposta)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping("/{idResposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable idResposta: Long) {
        respostaDtoService.deletar(idResposta)
    }
}
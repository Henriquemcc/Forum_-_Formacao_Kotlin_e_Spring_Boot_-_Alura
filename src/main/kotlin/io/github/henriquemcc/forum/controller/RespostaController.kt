package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

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
    fun cadastrar(@RequestBody @Valid form: NovaRespostaForm, @PathVariable idTopico: Long, uriBuilder: UriComponentsBuilder): ResponseEntity<RespostaView> {
        val respostaView = service.cadastrar(form, idTopico)
        val uri = uriBuilder.path("/topicos/${idTopico}/respostas").build().toUri()
        return ResponseEntity.created(uri).body(respostaView)
    }

    @PutMapping("/{idResposta}")
    fun atualizar(@RequestBody @Valid form: AtualizarRespostaForm, @PathVariable idResposta: Long): ResponseEntity<RespostaView> {
        val respostaView = service.atualizar(form, idResposta)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping("/{idResposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable idResposta: Long) {
        service.deletar(idResposta)
    }
}
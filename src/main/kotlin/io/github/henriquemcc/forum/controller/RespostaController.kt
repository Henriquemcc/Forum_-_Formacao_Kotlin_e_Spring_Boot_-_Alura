package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.AtualizarRespostaForm
import io.github.henriquemcc.forum.dto.NovaRespostaForm
import io.github.henriquemcc.forum.dto.RespostaView
import io.github.henriquemcc.forum.service.RespostaDtoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostaController(
    private val respostaDtoService: RespostaDtoService
) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) tituloTopico: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<RespostaView> {
        return respostaDtoService.listar(tituloTopico, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): RespostaView {
        return respostaDtoService.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid novaRespostaForm: NovaRespostaForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RespostaView> {
        val respostaView = respostaDtoService.cadastrar(novaRespostaForm)
        val uri = uriBuilder.path("/respostas/${respostaView.id}").build().toUri()
        return ResponseEntity.created(uri).body(respostaView)
    }

    @PutMapping("/{idResposta}")
    @Transactional
    fun atualizar(
        @RequestBody @Valid atualizarRespostaForm: AtualizarRespostaForm,
        @PathVariable idResposta: Long
    ): ResponseEntity<RespostaView> {
        val respostaView = respostaDtoService.atualizar(atualizarRespostaForm, idResposta)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping("/{idResposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable idResposta: Long) {
        respostaDtoService.deletar(idResposta)
    }
}
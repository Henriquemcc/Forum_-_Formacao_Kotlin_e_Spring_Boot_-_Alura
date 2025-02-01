package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.*
import io.github.henriquemcc.forum.service.TopicoDtoService
import io.github.henriquemcc.forum.service.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearerAuth")
class TopicoController(
    private val topicoService: TopicoService,
    private val topicoDtoService: TopicoDtoService
) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView> {
        return topicoDtoService.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return topicoDtoService.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = topicoDtoService.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizarTopicoForm, @PathVariable id: Long): ResponseEntity<TopicoView> {
        val topicoView = topicoDtoService.atualizar(form, id)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoService.relatorio()
    }

    @GetMapping("/relatorioTopicosNaoRespondidos")
    fun relatorioTopicosNaoRespondidos(): List<TopicoNaoRespondidoDto> {
        return topicoService.relatorioTopicosNaoRespondidos()
    }
}
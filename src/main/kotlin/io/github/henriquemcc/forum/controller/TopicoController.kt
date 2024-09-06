package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.NovoTopicoForm
import io.github.henriquemcc.forum.dto.TopicoView
import io.github.henriquemcc.forum.mapper.TopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    ) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar().map { t -> topicoViewMapper.map(t) }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return topicoViewMapper.map(service.buscarPorId(id))
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: NovoTopicoForm) {
        service.cadastrar(topicoFormMapper.map(form))
    }
}
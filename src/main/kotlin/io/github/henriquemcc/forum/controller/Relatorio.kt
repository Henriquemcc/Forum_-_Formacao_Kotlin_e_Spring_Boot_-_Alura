package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto
import io.github.henriquemcc.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Relatorio(
    private val topicoService: TopicoService
) {
    @GetMapping
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoService.relatorio()
    }
}
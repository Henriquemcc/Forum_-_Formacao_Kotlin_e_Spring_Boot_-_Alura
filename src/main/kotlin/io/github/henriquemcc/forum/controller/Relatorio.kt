package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto
import io.github.henriquemcc.forum.service.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/relatorios")
@SecurityRequirement(name = "bearerAuth")
class Relatorio(
    private val topicoService: TopicoService
) {
    @GetMapping
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoService.relatorio()
    }
}
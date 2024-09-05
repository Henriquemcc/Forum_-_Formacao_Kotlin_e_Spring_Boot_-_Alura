package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.model.Resposta
import io.github.henriquemcc.forum.service.RespostaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/respostas")
class RespostaController(private val service: RespostaService) {

    @GetMapping
    fun listar(): List<Resposta> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Resposta {
        return service.buscarPorId(id)
    }
}
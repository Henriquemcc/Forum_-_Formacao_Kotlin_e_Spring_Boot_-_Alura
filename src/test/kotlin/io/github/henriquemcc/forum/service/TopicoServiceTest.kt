package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.TopicoTest
import io.github.henriquemcc.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.PageImpl

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))
    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }

    private val topicoService = TopicoService(topicoRepository)
}
package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.mapper.AtualizarTopicoFormMapper
import io.github.henriquemcc.forum.mapper.NovoTopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.CursoTest
import io.github.henriquemcc.forum.model.TopicoTest
import io.github.henriquemcc.forum.model.TopicoViewTest
import io.github.henriquemcc.forum.model.UsuarioTest
import io.github.henriquemcc.forum.repository.CursoRepository
import io.github.henriquemcc.forum.repository.TopicoRepository
import io.github.henriquemcc.forum.repository.UsuarioRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicoDtoServiceTest {
    private val topicos = PageImpl(listOf(TopicoTest.build()))
    private val paginacao: Pageable = mockk()
    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }
    private val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    private val novoTopicoFormMapper: NovoTopicoFormMapper = mockk()
    private val atualizarTopicoFormMapper: AtualizarTopicoFormMapper = mockk()
    private val topicoService = TopicoService(topicoRepository)
    private val topicoDtoService = TopicoDtoService(topicoService, topicoViewMapper, novoTopicoFormMapper, atualizarTopicoFormMapper)

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoDtoService.listar("Kotlin avançado", paginacao)
        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll() }
    }
}
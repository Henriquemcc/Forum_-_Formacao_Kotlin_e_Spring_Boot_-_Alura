package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.exception.NotFoundException
import io.github.henriquemcc.forum.mapper.AtualizarTopicoFormMapper
import io.github.henriquemcc.forum.mapper.NovoTopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.TopicoTest
import io.github.henriquemcc.forum.model.TopicoViewTest
import io.github.henriquemcc.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoDtoServiceTest {
    private val topicos = PageImpl(listOf(TopicoTest.build()))
    private val paginacao: Pageable = mockk()
    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
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

    @Test
    fun `deve listar todos os topicos quando nome do curso for nulo`() {
        topicoDtoService.listar(null, paginacao)
        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar not found exception quando o topico nao for achado`() {
        every { topicoRepository.findById(any()) } returns  Optional.empty()
        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }
        assertThat(atual.message).isEqualTo("Topico nao encontrado!")
    }
}
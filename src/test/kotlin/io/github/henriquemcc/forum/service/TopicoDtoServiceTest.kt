package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.mapper.AtualizarTopicoFormMapper
import io.github.henriquemcc.forum.mapper.NovoTopicoFormMapper
import io.github.henriquemcc.forum.mapper.TopicoViewMapper
import io.github.henriquemcc.forum.model.CursoTest
import io.github.henriquemcc.forum.model.TopicoTest
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

    // Topico
    private val topicos = PageImpl(listOf(TopicoTest.build()))
    private val paginacao: Pageable = mockk()
    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }
    private val topicoService = TopicoService(topicoRepository)
    private val topicoViewMapper = TopicoViewMapper()

    // Curso
    private val curso = CursoTest.build()
    private val cursoRepository: CursoRepository = mockk {
        every { getReferenceById(any()) } returns curso
    }
    private val cursoService = CursoService(cursoRepository)

    // Usuario
    private val usuario = UsuarioTest.build()
    private val usuarioRepository: UsuarioRepository = mockk {
        every { getReferenceById(any()) } returns usuario
        every { findByEmail(any()) } returns usuario
    }
    private val usuarioService = UsuarioService(usuarioRepository)

    // Mappers
    private val novoTopicoFormMapper = NovoTopicoFormMapper(cursoService, usuarioService)
    private val atualizarTopicoFormMapper = AtualizarTopicoFormMapper()

    // TopicoDtoService
    private val topicoDtoService = TopicoDtoService(topicoService, topicoViewMapper, novoTopicoFormMapper, atualizarTopicoFormMapper)

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        every { topicoViewMapper.map(any()) } returns TopicoViewTest.build()
        topicoDtoService.listar("Kotlin avançado", paginacao)
        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any())}
        verify(exactly = 0) { topicoRepository.findAll() }
    }
}
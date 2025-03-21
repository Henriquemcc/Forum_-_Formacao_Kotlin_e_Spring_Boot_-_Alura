package io.github.henriquemcc.forum.integration

import io.github.henriquemcc.forum.configuration.DatabaseContainerConfiguration
import io.github.henriquemcc.forum.dto.TopicoPorCategoriaDto
import io.github.henriquemcc.forum.model.TopicoTest
import io.github.henriquemcc.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner::class) // https://stackoverflow.com/questions/72958037/autowired-not-working-inside-testcontainers
class TopicoRepositoryTest: DatabaseContainerConfiguration() {

    private val topico = TopicoTest.build()

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @Test
    fun `deve gerar um relatorio`() {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()
        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
    }

    @Test
    fun `deve listar topico pelo nome do curso`() {
        topicoRepository.save(topico)
        val topico = topicoRepository.findByCursoNome(topico.curso?.nome!!, PageRequest.of(0, 5))
        assertThat(topico).isNotNull
    }
}
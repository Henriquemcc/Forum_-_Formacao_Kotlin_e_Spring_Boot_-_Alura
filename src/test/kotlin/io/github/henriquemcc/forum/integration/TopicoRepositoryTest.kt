package io.github.henriquemcc.forum.integration

import io.github.henriquemcc.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired

class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository
}
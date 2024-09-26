package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import io.github.henriquemcc.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (private val repository: CursoRepository){

    fun buscarPorId(id: Long): Curso {
        return repository.getReferenceById(id)
    }
}

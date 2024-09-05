package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService (private val cursos: MutableList<Curso> = mutableListOf()){
    init {
        cursos.add(Curso(
            id = 1,
            nome = "kotlin",
            categoria = "Programação"
        ))
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.first{
            it.id == id
        }
    }
}

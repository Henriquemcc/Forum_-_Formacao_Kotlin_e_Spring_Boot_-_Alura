package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {

}
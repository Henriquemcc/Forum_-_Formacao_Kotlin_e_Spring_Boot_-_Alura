package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long> {

}
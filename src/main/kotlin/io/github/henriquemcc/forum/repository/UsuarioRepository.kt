package io.github.henriquemcc.forum.repository

import io.github.henriquemcc.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

}
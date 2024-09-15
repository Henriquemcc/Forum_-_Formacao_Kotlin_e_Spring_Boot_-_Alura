package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Usuario
import io.github.henriquemcc.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }
}

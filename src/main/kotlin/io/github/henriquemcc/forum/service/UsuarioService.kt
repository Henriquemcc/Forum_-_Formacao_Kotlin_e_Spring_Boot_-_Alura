package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Usuario
import io.github.henriquemcc.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository): UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }


}

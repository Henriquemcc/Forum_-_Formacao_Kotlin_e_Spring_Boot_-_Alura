package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarios: MutableList<Usuario> = mutableListOf()) {

    init {
        usuarios.add(Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "ana@email.com.br"
        ))
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.first {
            it.id == id
        }
    }
}

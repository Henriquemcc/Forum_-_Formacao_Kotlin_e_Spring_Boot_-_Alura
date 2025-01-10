package io.github.henriquemcc.forum.service

import io.github.henriquemcc.forum.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {

    override fun getAuthorities() = usuario.role

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.email
}
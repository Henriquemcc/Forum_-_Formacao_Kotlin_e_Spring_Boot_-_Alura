package io.github.henriquemcc.forum.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.henriquemcc.forum.config.JWTUtil
import io.github.henriquemcc.forum.model.Credentials
import io.github.henriquemcc.forum.service.UserDetail
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Fíltro utilizado para o usuário realizar o login na aplicação, através da rota '/login'.
 */
class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    /**
     * Realiza uma tentativa de autenticação do usuário através da rota /login.
     */
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val (username, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username, password)
        return authManager.authenticate(token)
    }

    /**
     * Quando um usuário é autenticado corretamente, através da rota '/login', este método chama a função generateToken, responsável por gerar um token de autenticação, e o envia para o cliente através de uma resposta HTTP.
     */
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val user = (authResult.principal as UserDetail)
        val token = jwtUtil.generateToken(user.username, user.authorities)
        response.addHeader("Authorization", "Bearer $token")
    }
}

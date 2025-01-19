package io.github.henriquemcc.forum.security

import io.github.henriquemcc.forum.config.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Fíltro utilizado para validar o token de acesso das requisições.
 */
class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {

    /**
     * Realiza a execução do filtro.
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwtUtil.isValid(jwt)){
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    /**
     * Extraí o token de autenticação da string passada por parâmetro.
     */
    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }

}

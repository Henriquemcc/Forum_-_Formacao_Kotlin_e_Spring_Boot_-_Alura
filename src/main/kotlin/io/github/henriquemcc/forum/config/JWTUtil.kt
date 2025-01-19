package io.github.henriquemcc.forum.config

import io.github.henriquemcc.forum.model.Role
import io.github.henriquemcc.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jwts.SIG
import io.jsonwebtoken.security.Keys.hmacShaKeyFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val usuarioService: UsuarioService
) {

    private val expiration: Long = 86400000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    /**
     * Realiza a geração do token JWT.
     */
    fun generateToken(username: String, authorities: List<Role>): String? {
        return Jwts.builder().subject(username).claim("role", authorities).expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(hmacShaKeyFor(secret.toByteArray()), SIG.HS512).compact()
    }

    // https://cursos.alura.com.br/forum/topico-sobre-o-metodo-isvalid-e-getauthentication-441882
    /**
     * Verifica se um token JWT é válido.
     */
    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().verifyWith(hmacShaKeyFor(secret.toByteArray())).build().parseSignedClaims(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    // https://cursos.alura.com.br/forum/topico-sobre-o-metodo-isvalid-e-getauthentication-441882
    /**
     * Obtém a autenticação do usuário.
     */
    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val username = Jwts.parser().verifyWith(hmacShaKeyFor(secret.toByteArray())).build().parseSignedClaims(jwt).payload.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }


}
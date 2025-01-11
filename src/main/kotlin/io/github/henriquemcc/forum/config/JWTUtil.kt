package io.github.henriquemcc.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jwts.SIG
import io.jsonwebtoken.security.Keys.hmacShaKeyFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    private val expiration: Long = 86400000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String): String? {
        return Jwts.builder().subject(username).expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(hmacShaKeyFor(secret.toByteArray()), SIG.HS512).compact()
    }

    // https://cursos.alura.com.br/forum/topico-sobre-o-metodo-isvalid-e-getauthentication-441882
    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().verifyWith(hmacShaKeyFor(secret.toByteArray())).build().parseSignedClaims(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    // https://cursos.alura.com.br/forum/topico-sobre-o-metodo-isvalid-e-getauthentication-441882
    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val username = Jwts.parser().verifyWith(hmacShaKeyFor(secret.toByteArray())).build().parseSignedClaims(jwt)
        return UsernamePasswordAuthenticationToken(username, null, null)
    }


}
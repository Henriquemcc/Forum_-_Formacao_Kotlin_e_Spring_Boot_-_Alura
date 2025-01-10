package io.github.henriquemcc.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jwts.SIG
import io.jsonwebtoken.security.Keys.hmacShaKeyFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String): String? {
        return Jwts.builder().subject(username).expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(hmacShaKeyFor(secret.toByteArray()), SIG.HS512).compact()
    }
}
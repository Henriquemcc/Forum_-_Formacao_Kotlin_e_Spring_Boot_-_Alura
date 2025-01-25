package io.github.henriquemcc.forum.config

import io.github.henriquemcc.forum.security.JWTAuthenticationFilter
import io.github.henriquemcc.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
// https://cursos.alura.com.br/forum/topico-websecurityconfigureradapter-deprecated-nova-implementacao-226360
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
class SecurityConfiguration(
    // https://cursos.alura.com.br/forum/topico-para-resolver-o-authenticationmanager-441881
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
) {


    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        // https://springdoc.org/#getting-started
        // https://stackoverflow.com/questions/75782147/error-403-openapi-with-springboot-security-filter-chain
        val swaggerUiPaths = arrayOf("/swagger-ui.html", "/v3/api-docs/**", "swagger-ui/**")

        // https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
        http.csrf { it.disable() }
        http.authorizeHttpRequests {
            it.requestMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
            it.requestMatchers(HttpMethod.POST, "/login").permitAll()
            it.requestMatchers(HttpMethod.GET, *swaggerUiPaths).permitAll()
            it.anyRequest().authenticated()
        }
        http.addFilterBefore(
            JWTLoginFilter(
                authManager = authenticationConfiguration.authenticationManager,
                jwtUtil = jwtUtil
            ), UsernamePasswordAuthenticationFilter::class.java
        )
        http.addFilterBefore(
            JWTAuthenticationFilter(jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter::class.java
        )
        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        return http.build()

    }
}
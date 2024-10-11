package io.github.henriquemcc.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
// https://cursos.alura.com.br/forum/topico-websecurityconfigureradapter-deprecated-nova-implementacao-226360
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
class SecurityConfiguration {

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        // https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
        return http.
        csrf {it.disable ()}.
        authorizeHttpRequests {
            it.requestMatchers(HttpMethod.GET, "/topicos").hasAuthority("LEITURA_ESCRITA").
            anyRequest().authenticated()
        }.
        sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.
        httpBasic(Customizer.withDefaults()).build()
    }

}
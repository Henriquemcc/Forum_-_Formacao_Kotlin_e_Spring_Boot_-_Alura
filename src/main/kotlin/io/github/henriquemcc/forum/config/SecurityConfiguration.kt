package io.github.henriquemcc.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
// https://cursos.alura.com.br/forum/topico-websecurityconfigureradapter-deprecated-nova-implementacao-226360
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
class SecurityConfiguration {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain? {

        // https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
        http.authorizeHttpRequests { authorize ->
            authorize.anyRequest().authenticated()
        }
        http.sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        http.formLogin { form ->
            form.disable().httpBasic {}
        }

        return http.build()
    }

}
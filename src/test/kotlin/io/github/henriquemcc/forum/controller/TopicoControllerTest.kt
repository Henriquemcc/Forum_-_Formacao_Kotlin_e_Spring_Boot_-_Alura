package io.github.henriquemcc.forum.controller

import io.github.henriquemcc.forum.config.JWTUtil
import io.github.henriquemcc.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers


// https://github.com/spring-projects/spring-security-samples/blob/main/servlet/spring-boot/java/hello-security-explicit/src/test/java/example/HelloSecurityExplicitApplicationTests.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var token: String

    @BeforeEach
    fun setup() {
        token = gerarToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity()).build()
    }

    companion object {
        private const val RECURSO = "/topicos/"
    }

    private fun gerarToken(): String {
        val authorities = mutableListOf(Role(1, "LEITURA_ESCRITA"))
        return jwtUtil.generateToken("ana@email.com", authorities)!!
    }

    @Test
    fun `deve retornar codigo 400 quando chamar topicos sem token`() {
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }
}
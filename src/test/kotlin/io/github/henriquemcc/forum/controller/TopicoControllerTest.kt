package io.github.henriquemcc.forum.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

// https://github.com/spring-projects/spring-security-samples/blob/main/servlet/spring-boot/java/hello-security-explicit/src/test/java/example/HelloSecurityExplicitApplicationTests.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc
}
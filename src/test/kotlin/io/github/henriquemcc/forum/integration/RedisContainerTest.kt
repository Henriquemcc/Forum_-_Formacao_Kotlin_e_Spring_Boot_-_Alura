package io.github.henriquemcc.forum.integration

import io.github.henriquemcc.forum.configuration.DatabaseContainerConfiguration
import io.github.henriquemcc.forum.configuration.RedisConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner::class)
@Import(RedisConfig::class)
class RedisContainerTest: DatabaseContainerConfiguration() {
    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    @Test
    fun `deve conectar ao container do Redis e realizar operacoes`() {
        val chave = "test-key"
        val valor = "test-value"
        redisTemplate.opsForValue().set(chave, valor)
        val valorObtido = redisTemplate.opsForValue().get(chave)
        assertEquals(valor, valorObtido, "O valor obtido deve bater com o valor atribuído")
    }

    @Test
    fun `o container do Redis deve estar rodando`() {
        assertTrue(redisContainer.isRunning, "O container do Redis deve estar rodando")
    }
}
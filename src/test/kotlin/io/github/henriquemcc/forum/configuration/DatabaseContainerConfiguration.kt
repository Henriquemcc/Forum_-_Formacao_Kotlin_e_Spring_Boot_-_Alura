package io.github.henriquemcc.forum.configuration

import com.redis.testcontainers.RedisContainer
import org.junit.ClassRule
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container

abstract class DatabaseContainerConfiguration {
    companion object {

        /**
         * Cria o container do MySQL no Docker.
         */
        @JvmField
        @Container
        @ClassRule // https://blogs.oracle.com/mysql/post/testing-mysql-applications-with-java-and-testcontainers#:~:text=Could%20not%20Copy-,%40ClassRule,-static%20MySQLContainer%3C!%2D%2D%3F%2D%2D%3E%20mySQLContainer
        val mysqlContainer = MySQLContainer<Nothing>("mysql:9.1.0").apply{
            withDatabaseName("testedb")
            withUsername("myuser")
            withPassword("secret")
        }

        /**
         * Cria o container do Redis no Docker.
         */
        @JvmField
        @Container
        @ClassRule
        val redisContainer = RedisContainer("redis:7.4.2").apply {
            withExposedPorts(6379)
            waitingFor(Wait.forListeningPort())
        }


        /**
         * Define as propriedades equivalentes ao 'application.properties'.
         */
        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {

            // MySQL
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.datasource.driverClassName", mysqlContainer::getDriverClassName)

            // Redis
            registry.add("spring.redis.host", redisContainer::getHost)
            registry.add("spring.redis.port", redisContainer::getFirstMappedPort)
        }
    }
}
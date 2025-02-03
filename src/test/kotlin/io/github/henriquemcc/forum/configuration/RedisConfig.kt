package io.github.henriquemcc.forum.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfig {
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6379))
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): StringRedisTemplate {
        return StringRedisTemplate(factory)
    }
}
package com.dine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("Starting to create redis template object...");
        RedisTemplate redisTemplate = new RedisTemplate();
        //set redis template connection factory object
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //set redis template serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}

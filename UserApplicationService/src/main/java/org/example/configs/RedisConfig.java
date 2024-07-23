package org.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.host.url}")
    String host;

    @Value("${redis.host.port}")
    Integer port;

    @Value("${redis.auth.password}")
    String password;

    @Bean
    //lettuse is one of the redis client
    //other is also jeddice which is one of famous client
    public LettuceConnectionFactory getConnection(){
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration(host,port);
        configuration.setPassword(password);

        LettuceConnectionFactory lettuceConnectionFactory =new LettuceConnectionFactory(configuration);

        return lettuceConnectionFactory;
    }

    //Redis is key value database
    //key to be string
    //value to be a user object for it`
    @Bean
    public RedisTemplate<String, Object> getTemplate(){

        RedisTemplate<String, Object> redisTemplate= new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnection());

        //for converting data into string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        return redisTemplate;

    }

}

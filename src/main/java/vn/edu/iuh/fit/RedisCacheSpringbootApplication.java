package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import vn.edu.iuh.fit.repositories.BookRepository;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class RedisCacheSpringbootApplication {
    @Autowired
    private BookRepository bookRepository;
    @Bean
  CommandLineRunner a(){
        return args -> {
            System.out.println(bookRepository.findAll());
        };
  }
    public static void main(String[] args) {
        SpringApplication.run(RedisCacheSpringbootApplication.class, args);
    }

}

# Redis Cache Spring Boot
## Sơ đồ mô tả cách thức hoạt động Redis
![Redis Diagram](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/2b6920a1-d615-44f4-8cdf-fc34d4024ff4)
## Gọi dữ liệu từ database (39 ms)
![image](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/69523631-f3b5-4251-a07c-83ea877c8680)
## Gọi Redis Cache (8 ms)
![image](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/e2a03671-7b67-46a1-841b-dd7f04ee0a17)
```java
public Book findBookById(long id){
    System.out.println("called findProductById() from DB");
    return (Book) template.opsForHash().get(HASH_KEY, id);
}
```
## 
![image](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/4c2f8a02-096a-4eaf-acc5-f059057eaf6b)
- Dữ liệu chỉ được gọi 1 lần đến CSDL. Sau đó dữ liệu sẽ được lưu vào Redis
## Trang tải Redis[Redis for Windows (win-3.2.100)](https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100)
## Thư viện
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation group: 'redis.clients', name: 'jedis'

}
```
## RedisConfig
```java
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> template() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

}
```


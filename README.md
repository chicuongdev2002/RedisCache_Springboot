# Redis Cache Spring Boot
## Sơ đồ mô tả cách thức hoạt động Redis
![Redis Diagram](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/2b6920a1-d615-44f4-8cdf-fc34d4024ff4)
## Gọi dữ liệu từ database (39 ms)
![Database Call](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/db1cae88-26e5-4419-8a74-18d3cf3b2d31)
## Gọi Redis Cache (8 ms)
![Redis Cache Call](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/812ccfce-275a-40e2-b10c-2e234b94c635)

```java
public Book findBookById(long id){
    System.out.println("called findProductById() from DB");
    return (Book) template.opsForHash().get(HASH_KEY, id);
}
```
## 
![image](https://github.com/chicuongdev2002/RedisCache_Springboot/assets/124854803/4c2f8a02-096a-4eaf-acc5-f059057eaf6b)
- Dữ liệu chỉ được gọi 1 lần đến CSDL. Sau đó dữ liệu sẽ được lưu vào Redis
- Trang tải Redis[Redis for Windows (win-3.2.100)](https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100)
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


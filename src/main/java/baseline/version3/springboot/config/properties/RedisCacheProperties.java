package baseline.version3.springboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis.cache")
public record RedisCacheProperties(String prefix, Integer ttl) {
}

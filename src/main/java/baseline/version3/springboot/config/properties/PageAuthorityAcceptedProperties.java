package baseline.version3.springboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "page-admin.accepted")
public record PageAuthorityAcceptedProperties(
        List<String> staticPaths
) {
    public PageAuthorityAcceptedProperties {
        staticPaths = staticPaths == null? List.of(
                "/framework/**",
                "/library/**",
                "/favicon.ico",
                "/img/**",
                "/js/**",
                "/css/**"
        ) : staticPaths;
    }
}

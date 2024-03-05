package baseline.version3.springboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.List;

@ConfigurationProperties(prefix = "page-admin.denied")
public record PageAuthorityDeniedProperties(
        List<String> httpMethods
) {
    public PageAuthorityDeniedProperties {
        httpMethods = httpMethods == null? List.of(
                HttpMethod.HEAD.name(),
                HttpMethod.TRACE.name(),
                HttpMethod.OPTIONS.name()
        ) : httpMethods;
    }
}

package baseline.version3.springboot.pageAdmin.page.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.List;

@ConfigurationProperties(prefix = "page-admin.accepted")
public record DynamicPageAuthorityAcceptedProperties(
        List<String> httpMethods,
        List<String> staticPaths
) {
    public DynamicPageAuthorityAcceptedProperties {
        httpMethods = httpMethods == null? List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()
        ) : httpMethods;
        staticPaths = staticPaths == null? List.of(
                "/framework/**",
                "/library/**",
                "/img/**",
                "/favicon.ico"
        ) : staticPaths;
    }
}

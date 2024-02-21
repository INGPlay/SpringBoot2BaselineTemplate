package baseline.version3.springboot.config;

import baseline.version3.springboot.pageAdmin.ipAccess.interceptor.IpAccessInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final IpAccessInterceptor ipAccessInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipAccessInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/framework/**", "/img/**", "/library/**", "/error");
    }
}

package baseline.version3.springboot.config;

import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ParentPageService parentPageService;
    private final SubPageService subPageService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}

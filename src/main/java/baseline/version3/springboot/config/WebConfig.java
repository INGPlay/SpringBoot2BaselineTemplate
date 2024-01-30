package baseline.version3.springboot.config;

import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ParentPageService parentPageService;
    private final SubPageService subPageService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        subPageService.findList(new SubPageRequest.RequestDynamicQuery()).forEach(
                subPage -> {
                    String url = subPage.concatPagePath();
                    String viewName = subPage.concatPagePath().replace("/", "");
                    registry
                            .addViewController(url)
                            .setViewName(viewName);

                    log.info("Url : '{}' | View : {}", url, viewName);
                }
        );
    }
}

package baseline.version3.springboot.config;

import baseline.version3.springboot.config.converter.StringToActiveStatusConverter;
import baseline.version3.springboot.config.converter.StringToOAuthTypeConverter;
import baseline.version3.springboot.config.converter.StringToRoleEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToActiveStatusConverter());
        registry.addConverter(new StringToOAuthTypeConverter());
        registry.addConverter(new StringToRoleEnumConverter());
    }
}

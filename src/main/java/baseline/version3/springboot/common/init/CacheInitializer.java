package baseline.version3.springboot.common.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CacheInitializer implements InitializingBean {

    private final CacheManager cacheManager;
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Cache Clear Start");
        log.info("{}", cacheManager.getCacheNames());
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        log.info("Cache Clear End");
    }
}

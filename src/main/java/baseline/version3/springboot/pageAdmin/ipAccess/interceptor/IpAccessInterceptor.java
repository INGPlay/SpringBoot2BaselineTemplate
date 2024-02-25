package baseline.version3.springboot.pageAdmin.ipAccess.interceptor;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.common.util.HttpUtil;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessRequest;
import baseline.version3.springboot.pageAdmin.ipAccess.service.IpAccessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class IpAccessInterceptor implements HandlerInterceptor {

    private final IpAccessService ipAccessService;

    /**
     * -Djava.net.preferIPv4Stack=true
     * 위 VM Option 필요
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = HttpUtil.getClientIp();

        if (clientIp.equals("127.0.0.1")) {
            return true;
        }

        if (ipAccessService.isAppliedIp(clientIp)) {
            log.warn("Forbidden access, URI: {}, IP: {}", request.getRequestURI(), clientIp);
            response.sendError(403, "IP Forbidden");
            return false;
        }

        return true;
    }
}

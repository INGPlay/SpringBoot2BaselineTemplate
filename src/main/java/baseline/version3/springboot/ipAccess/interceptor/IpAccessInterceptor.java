package baseline.version3.springboot.ipAccess.interceptor;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.common.util.IpUtil;
import baseline.version3.springboot.ipAccess.domain.IpAccessRequest;
import baseline.version3.springboot.ipAccess.repository.IpAccessRepository;
import baseline.version3.springboot.ipAccess.service.IpAccessService;
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
    private final IpAccessRepository ipAccessRepository;

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
        String clientIp = IpUtil.getClientIp(request);
        if (clientIp.equals("127.0.0.1")) {
            return true;
        }

        IpAccessRequest.RequestDynamicQuery requestDynamicQuery = IpAccessRequest.RequestDynamicQuery
                .builder()
                .applyStatus(ApplyStatus.APPLY)
                .build();

        boolean isMatches = ipAccessService.findList(requestDynamicQuery).stream()
                .map(ipAccess -> new IpAddressMatcher(ipAccess.getIpAddress()))
                .anyMatch(ip -> ip.matches(clientIp));

        if (isMatches) {
            log.warn("Forbidden access, URI: {}, IP: {}", request.getRequestURI(), clientIp);
            response.sendError(403, "IP Forbidden");
            return false;
        }

        return true;
    }
}

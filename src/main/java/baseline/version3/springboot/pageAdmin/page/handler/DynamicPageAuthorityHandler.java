package baseline.version3.springboot.pageAdmin.page.handler;

import baseline.version3.springboot.config.security.authenticationManager.AccountContext;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.page.properties.DynamicPageAuthorityAcceptedProperties;
import baseline.version3.springboot.pageAdmin.page.service.SubPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicPageAuthorityHandler {

    private final SubPageService subPageService;

    private final DynamicPageAuthorityAcceptedProperties dynamicPageAuthorityAcceptedProperties;

    public boolean isPageAuthorization(HttpServletRequest request, Authentication authentication) {

        if (checkHttpMethods(request)){
            return false;
        }

        return checkPageAuthority(request, authentication);
    }

    private boolean checkPageAuthority(HttpServletRequest request, Authentication authentication) {
        // GET이 아니면 true -> Method Security로 권한 체크
        if (isNotGet(request)) {
            return true;
        }

        if (checkStaticPaths(request)) {
            return true;
        }

        // URI를 통해 해당하는 페이지 찾기
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(request.getRequestURI());
        Optional<SubPageResponse.Response> subPageResponse = subPageService.findOne(requestDynamicQueryOne);

        // 등록해놓은 페이지가 아닌 경우
        if (subPageResponse.isEmpty()){
            return true;
        }
        SubPageResponse.Response subPage = subPageResponse.get();
        Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(authentication.getPrincipal());

        // 등록해놓은 페이지에 제한사항이 없을 경우
        if (!StringUtils.hasText(subPage.pageAuthorityCode())){
            return true;

        // 제한사항은 있는데, 사용자의 권한이 없을 경우
        } else if (authorities == null){
            return false;
        }

        // 사용자의 권한 중 페이지의 권한이 포함되어 있는지 확인
        return authorities.contains(new SimpleGrantedAuthority(getRoleString(subPage.pageAuthorityCode())));
    }

    private static boolean isNotGet(HttpServletRequest request) {
        if (!request.getMethod().equalsIgnoreCase(HttpMethod.GET.name())){
            return true;
        }
        return false;
    }

    private boolean checkStaticPaths(HttpServletRequest request) {
        // GET 방식만 체크한다.
        return dynamicPageAuthorityAcceptedProperties.staticPaths().stream().anyMatch(
                        p -> antPathMatcher().match(p, request.getRequestURI())
                );
    }

    private boolean checkHttpMethods(HttpServletRequest request) {
        // 허용되지 않는 메소드는 False
        return dynamicPageAuthorityAcceptedProperties.httpMethods().stream().noneMatch(
                httpMethod -> httpMethod.equals(request.getMethod())
        );
    }

    private static Collection<? extends GrantedAuthority> getGrantedAuthorities(Object principal) {
        Collection<? extends GrantedAuthority> authorities = null;
        // 로그인 방식에 따른 처리
        if (principal instanceof AccountContext accountContext){
            authorities = accountContext.getAuthorities();

        } else if (principal instanceof OidcUser oidcUser){
            List<String> roles = (List<String>) (((Map<String, Object>) oidcUser.getAttribute("realm_access")).get("roles"));
            authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        } else {
            assert false : "지원하지 않는 유저 토큰입니다.";
        }
        return authorities;
    }

    @Bean
    private AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }

    private String getRoleString(String roleCode){
        return "ROLE_" + roleCode;
    }
}

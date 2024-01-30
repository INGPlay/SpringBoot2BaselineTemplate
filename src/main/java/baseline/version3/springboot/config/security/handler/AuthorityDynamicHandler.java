package baseline.version3.springboot.config.security.handler;

import baseline.version3.springboot.config.security.authenticationManager.AccountContext;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorityDynamicHandler {

    private final SubPageService subPageService;

    public boolean isAuthorization(HttpServletRequest request, Authentication authentication){

        String requestRoute = request.getRequestURI();

        // 해당하는 URI 찾기
        Optional<SubPageResponse.Response> response = subPageService.findList(new SubPageRequest.RequestDynamicQuery()).stream().filter(
                subPage -> antPathMatcher().match(subPage.concatPagePath(), requestRoute)
        ).findAny();

        if (response.isEmpty()){
            return true;
        }

        // 로그인 방식에 따른 처리
        Object principal = authentication.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = null;
        SubPageResponse.Response subPage = response.get();
        if (principal instanceof AccountContext){
            AccountContext accountContext = (AccountContext) principal;
            authorities = accountContext.getAuthorities();

        } else if (principal instanceof OidcUser){
            OidcUser oidcUser = (OidcUser) principal;
            List<String> roles = (List<String>) (((Map<String, Object>) oidcUser.getAttribute("realm_access")).get("roles"));
            authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        } else if (principal instanceof OAuth2User){
            OAuth2User oauth2User = (OAuth2User) principal;
            authorities = oauth2User.getAuthorities();
        } else {
            assert false : "지원하지 않는 유저 토큰입니다.";
        }

        return authorities.contains(new SimpleGrantedAuthority(getRoleString(subPage.pageAuthorityCode())));
    }

    @Bean
    private AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }

    private String getRoleString(String roleCode){
        return "ROLE_" + roleCode;
    }
}

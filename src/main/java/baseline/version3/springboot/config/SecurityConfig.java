package baseline.version3.springboot.config;

import baseline.version3.springboot.config.properties.PageAuthorityDeniedProperties;
import baseline.version3.springboot.config.security.handler.CustomAuthenticationFailureHandler;
import baseline.version3.springboot.config.security.handler.CustomAuthenticationSuccessHandler;
import baseline.version3.springboot.config.security.handler.CustomLogoutHandler;
import baseline.version3.springboot.config.security.handler.KeycloakLogoutHandler;
import baseline.version3.springboot.config.properties.PageAuthorityAcceptedProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PageAuthorityAcceptedProperties pageAuthorityAcceptedProperties;
    private final PageAuthorityDeniedProperties pageAuthorityDeniedProperties;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutHandler customLogoutHandler;
    private final KeycloakLogoutHandler keycloakLogoutHandler;

//    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring()
                .requestMatchers(String.valueOf(pageAuthorityAcceptedProperties.staticPaths()).split(","))
                // 경로 잘 확인할 것. 정적 파일이 다 들어가지 않으면 통과된 정적파일이 URI로 들어가서 반환됨
        );
    }

    @Bean
    @Primary
    public HttpSecurity commonSecurity(HttpSecurity httpSecurity) throws Exception {

        String collect = pageAuthorityDeniedProperties.httpMethods().stream()
                .map(httpMethod -> httpMethod.strip().toUpperCase())
                .collect(Collectors.joining(", "));
        log.info("Denied Methods : {}", collect);

        return httpSecurity
                .authorizeRequests(a -> {
                            // Http 메소드 제한
                            pageAuthorityDeniedProperties.httpMethods().forEach(
                                method -> a.requestMatchers(HttpMethod.valueOf(method.strip().toUpperCase())).denyAll()
                            );
                            // 페이지별 동적 권한 핸들러
                            a.anyRequest().access("@dynamicPageAuthorityHandler.isPageAuthorization(request, authentication)");
                        }
                )
                .logout(l -> l
                        .logoutUrl("/account/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID", "remember-me")
                )
                .sessionManagement(m -> m
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .exceptionHandling(e ->
                        e.accessDeniedPage("/error")
                )
                .headers(h -> h
                        .referrerPolicy(referrerPolicy ->
                                referrerPolicy
                                        .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                        )
                        .contentSecurityPolicy(contentSecurityPolicy ->
                                contentSecurityPolicy
                                        .policyDirectives("script-src 'self'")
                        )
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin
                        )
                );
    }

    @Bean
    @ConditionalOnProperty(value = "custom.account.type", havingValue = "form", matchIfMissing = false)
    public SecurityFilterChain filterChain(@Qualifier("commonSecurity") HttpSecurity commonSecurity) throws Exception {
        commonSecurity
                .formLogin(f -> f
                        .loginPage("/account/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/account/login?fail")
                        .usernameParameter("accountName")
                        .passwordParameter("accountPassword")
                        .loginProcessingUrl("/account/login-process")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                )
//                .oauth2Login(o -> o
//                        .loginPage("/account/login")
//                        .userInfoEndpoint(u -> u
//                                .userService(customOAuth2UserService)
//                        )
//                        .failureUrl("/account/login?fail")
//                        .successHandler(customAuthenticationSuccessHandler)
//                        .failureHandler(customAuthenticationFailureHandler)
//                )
                .rememberMe(r -> r
                                .rememberMeParameter("remember-me")         // 기본 파라미터명은 remember-me
                                .tokenValiditySeconds(3600)              // Default 는 14일
                                .alwaysRemember(false)                    // 리멤버 미 기능이 활성화되지 않아도 항상 실행
//                        .userDetailsService(customUserDetailsService)
                )
                .sessionManagement(m -> m
//                        .sessionFixation().changeSessionId()
                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                        .invalidSessionUrl("/invalid")
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(false)
//                        .expiredUrl("expired")
                );

        return commonSecurity.build();
    }

    @Bean
    @ConditionalOnProperty(value = "custom.account.type", havingValue = "keycloak", matchIfMissing = false)
    public SecurityFilterChain keycloakChain(@Qualifier("commonSecurity") HttpSecurity commonSecurity) throws Exception {
        commonSecurity
                .oauth2Login()
                .and()
                .logout(l -> l
                        .addLogoutHandler(keycloakLogoutHandler)
                )
                .sessionManagement(m -> m
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                );

        return commonSecurity.build();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER\nROLE_MANAGER > ROLE_USER");

        return roleHierarchy;
    }

    @Bean
    @ConditionalOnProperty(value = "custom.account.type", havingValue = "keycloak", matchIfMissing = false)
    public GrantedAuthoritiesMapper userAuthoritiesMapperForKeycloak() {
        return authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            var authority = authorities.iterator().next();
            boolean isOidc = authority instanceof OidcUserAuthority;

            if (isOidc) {
                var oidcUserAuthority = (OidcUserAuthority) authority;
                var userInfo = oidcUserAuthority.getUserInfo();

                if (userInfo.hasClaim("realm_access")) {
                    var realmAccess = userInfo.getClaimAsMap("realm_access");
                    var roles = (Collection<String>) realmAccess.get("roles");
                    mappedAuthorities.addAll(generateAuthoritiesFromClaim(roles));
                }
            } else {
                var oauth2UserAuthority = (OAuth2UserAuthority) authority;
                Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();

                if (userAttributes.containsKey("realm_access")) {
                    var realmAccess =  (Map<String,Object>) userAttributes.get("realm_access");
                    var roles =  (Collection<String>) realmAccess.get("roles");
                    mappedAuthorities.addAll(generateAuthoritiesFromClaim(roles));
                }
            }

            return mappedAuthorities;
        };
    }

    Collection<GrantedAuthority> generateAuthoritiesFromClaim(Collection<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}

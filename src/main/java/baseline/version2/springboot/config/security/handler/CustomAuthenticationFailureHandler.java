package baseline.version2.springboot.config.security.handler;

import com.mall.spring_shopping.account.domain.RecordLoginDTO;
import com.mall.spring_shopping.account.domain.subType.LoginResultEnum;
import com.mall.spring_shopping.account.repository.AccountRepository;
import com.mall.spring_shopping.account.service.AccountService;
import com.mall.spring_shopping.common.entity.Account;
import com.mall.spring_shopping.exceptionHandler.exception.UnauthorizedOAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        super.onAuthenticationFailure(request, response, exception);
    }
}

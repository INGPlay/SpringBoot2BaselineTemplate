package baseline.version2.springboot.config.security.handler;

import com.mall.spring_shopping.account.domain.RecordLoginDTO;
import com.mall.spring_shopping.account.domain.subType.LoginResultEnum;
import com.mall.spring_shopping.account.service.AccountService;
import com.mall.spring_shopping.config.security.authenticationManager.AccountContext;
import com.mall.spring_shopping.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }
}

package baseline.version3.springboot.project.init;

import baseline.version3.springboot.project.account.domain.AccountRequest;
import baseline.version3.springboot.project.account.domain.AccountSub;
import baseline.version3.springboot.project.account.repository.AccountRepository;
import baseline.version3.springboot.project.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class AccountInitializer implements InitializingBean {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (isInAccountName("admin")) {
            log.info("Init Admin");

            AccountRequest.RegisterAccountDTO managerDTO = new AccountRequest.RegisterAccountDTO();
            managerDTO.setAccountRole(AccountSub.RoleEnum.ADMIN);
            managerDTO.setAccountName("admin");
            managerDTO.setAccountPassword("admin");
            managerDTO.setAccountDisplayName("ADMIN");
            managerDTO.setOauthType(AccountSub.OAuthTypeEnum.LOCAL);
            accountService.createAccount(managerDTO);
        }
    }

    private boolean isInAccountName(String accountName) {
        return accountRepository.findByAccountName(accountName).isEmpty();
    }
}

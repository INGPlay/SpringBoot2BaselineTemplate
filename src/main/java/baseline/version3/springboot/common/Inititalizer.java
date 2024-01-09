package baseline.version3.springboot.common;

import baseline.version3.springboot.project.account.domain.AccountRequest;
import baseline.version3.springboot.project.account.domain.AccountSub;
import baseline.version3.springboot.project.account.repository.AccountRepository;
import baseline.version3.springboot.project.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Inititalizer implements InitializingBean {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (isInAccountName("admin")) {
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

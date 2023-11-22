package baseline.version2.springboot.account.service;

import baseline.version2.springboot.account.domain.RegisterAccountDTO;
import baseline.version2.springboot.account.domain.subType.OAuthTypeEnum;
import baseline.version2.springboot.account.repository.AccountRepository;
import baseline.version2.springboot.account.repository.querydsl.QueryAccountRepository;
import baseline.version2.springboot.common.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;
    private final QueryAccountRepository queryAccountRepository;

    public Boolean isInDb(String accountName){
        return accountRepository.findByAccountName(accountName).isPresent();
    }

    public Long insertLocalAccount(RegisterAccountDTO registerAccountDTO){
        Account account = new Account();
        account.setAccountName(registerAccountDTO.getAccountName());
        account.setAccountPassword(passwordEncoder.encode(registerAccountDTO.getAccountPassword()));
        account.setAccountDisplayName(registerAccountDTO.getAccountDisplayName());
        account.setAccountRole(registerAccountDTO.getAccountRole());
        account.setOauthType(OAuthTypeEnum.LOCAL);

        Account savedAccount = accountRepository.save(account);

        return savedAccount.getAccountId();
    }
}

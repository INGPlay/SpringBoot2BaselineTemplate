package baseline.version3.springboot.account.service;

import baseline.version3.springboot.account.domain.AccountRequest;
import baseline.version3.springboot.account.domain.AccountMapper;
import baseline.version3.springboot.account.repository.AccountRepository;
import baseline.version3.springboot.account.repository.querydsl.AccountQRepository;
import baseline.version3.springboot.common.entity.Account;
import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;
    private final AccountQRepository accountQRepository;

    public Boolean isInDb(String accountName){
        return accountRepository.findByAccountName(accountName).isPresent();
    }

    public Long createAccount(AccountRequest.RegisterAccountDTO registerAccountDTO){
        Account account = accountMapper.createAccount(registerAccountDTO);

        Account savedAccount = accountRepository.save(account);

        return savedAccount.getAccountId();
    }

    public Long updateAccount(String accountName, AccountRequest.UpdateAccountDTO updateAccountDTO){
        Account account = accountRepository.findByAccountName(accountName).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        accountMapper.updateAccount(updateAccountDTO, account);
        Account updateAccount = accountRepository.save(account);
        return updateAccount.getAccountId();
    }

    public void deleteAccount(String accountName){
        accountRepository.deleteByAccountName(accountName);
    }
}

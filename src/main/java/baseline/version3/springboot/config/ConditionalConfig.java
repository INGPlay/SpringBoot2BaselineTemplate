package baseline.version3.springboot.config;

import baseline.version3.springboot.common.Initializer;
import baseline.version3.springboot.project.account.repository.AccountRepository;
import baseline.version3.springboot.project.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConditionalConfig {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @ConditionalOnProperty(value = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
    public Initializer initializer() {
        return new Initializer(accountRepository, accountService);
    }
}

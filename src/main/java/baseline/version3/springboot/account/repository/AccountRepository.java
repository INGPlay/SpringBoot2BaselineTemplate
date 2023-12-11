package baseline.version3.springboot.account.repository;

import baseline.version3.springboot.common.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByAccountName(String accountName);

    public void deleteByAccountName(String accountName);
}
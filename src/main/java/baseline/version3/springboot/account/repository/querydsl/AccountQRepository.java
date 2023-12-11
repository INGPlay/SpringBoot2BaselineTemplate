package baseline.version3.springboot.account.repository.querydsl;

import baseline.version3.springboot.account.domain.AccountSub;
import baseline.version3.springboot.common.entity.Account;
import baseline.version3.springboot.common.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountQRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QAccount account = QAccount.account;

    public Optional<Account> selectAccountOne(String accountName, AccountSub.OAuthTypeEnum oauthType){

        return Optional.ofNullable(
                jpaQueryFactory
                        .select(account)
                        .from(account)
                        .where(
                                account.accountName.eq(accountName),
                                account.oauthType.eq(oauthType)
                        ).fetchOne()
        );
    }
}

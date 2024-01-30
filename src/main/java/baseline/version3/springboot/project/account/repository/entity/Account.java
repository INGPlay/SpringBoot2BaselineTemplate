package baseline.version3.springboot.project.account.repository.entity;

import baseline.version3.springboot.project.account.domain.AccountSub;
import baseline.version3.springboot.common.entity.base.DateTimeBase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "account")
@Getter @Setter
public class Account extends DateTimeBase {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(unique = true)
    private String accountName;

    @Column
    private String accountPassword;

    @Column
    private String accountDisplayName;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AccountSub.OAuthTypeEnum oauthType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AccountSub.RoleEnum accountRole;
}

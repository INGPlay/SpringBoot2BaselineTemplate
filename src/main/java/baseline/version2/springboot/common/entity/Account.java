package baseline.version2.springboot.common.entity;

import baseline.version2.springboot.account.domain.subType.OAuthTypeEnum;
import baseline.version2.springboot.account.domain.subType.RoleEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter @Setter
public class Account {
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
    private OAuthTypeEnum oauthType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RoleEnum accountRole;
}

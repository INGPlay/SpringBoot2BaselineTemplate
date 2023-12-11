package baseline.version3.springboot.account.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.Date;

public class AccountResponse{
    @Getter
    public static class AccountVO {

        private Long accountId;

        private String accountName;

        private String accountDisplayName;

        private AccountSub.RoleEnum accountRole;
        private AccountSub.OAuthTypeEnum oauthType;
        private Date registerDate;
        private Date lastModifyDate;


        @QueryProjection
        public AccountVO(Long accountId, String accountName, String accountDisplayName, AccountSub.RoleEnum accountRole, AccountSub.OAuthTypeEnum oauthType, Date registerDate, Date lastModifyDate) {
            this.accountId = accountId;
            this.accountName = accountName;
            this.accountDisplayName = accountDisplayName;
            this.accountRole = accountRole;
            this.oauthType = oauthType;
            this.registerDate = registerDate;
            this.lastModifyDate = lastModifyDate;
        }
    }
}

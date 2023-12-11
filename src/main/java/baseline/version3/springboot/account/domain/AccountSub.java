package baseline.version3.springboot.account.domain;

import baseline.version3.springboot.common.domain.subType.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AccountSub {
    @Getter
    @AllArgsConstructor
    public enum OAuthTypeEnum implements EnumBase {
        LOCAL("로컬"),
        KAKAO("카카오"),
        GOOGLE("구글"),
        NAVER("네이버")
        ;

        private String text;
    }

    @Getter
    @AllArgsConstructor
    public enum RoleEnum implements EnumBase {
        ADMIN("관리자"),
        MANAGER("게시자"),
        USER("이용자")
        ;

        private final String text;
    }

}

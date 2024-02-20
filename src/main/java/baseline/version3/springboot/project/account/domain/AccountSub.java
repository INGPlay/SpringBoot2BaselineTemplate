package baseline.version3.springboot.project.account.domain;

import baseline.version3.springboot.common.domain.subType.EnumBase;
import com.fasterxml.jackson.annotation.JsonCreator;
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

        @JsonCreator
        public static OAuthTypeEnum from(String s){
            return OAuthTypeEnum.valueOf(s);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum RoleEnum implements EnumBase {
        ADMIN("관리자"),
        MANAGER("게시자"),
        USER("이용자")
        ;

        private final String text;

        @JsonCreator
        public static RoleEnum from(String s){
            return RoleEnum.valueOf(s);
        }
    }

}

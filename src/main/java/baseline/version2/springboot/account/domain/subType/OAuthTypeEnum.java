package baseline.version2.springboot.account.domain.subType;

import baseline.version2.springboot.common.subType.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OAuthTypeEnum implements EnumBase {
    LOCAL("로컬"),
    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버");
    
    private String text;
}

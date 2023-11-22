package baseline.version2.springboot.account.domain.subType;

import baseline.version2.springboot.common.subType.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum implements EnumBase {
    ADMIN("관리자"),
    MANAGER("게시자"),
    USER("이용자")
    ;

    private final String text;
}

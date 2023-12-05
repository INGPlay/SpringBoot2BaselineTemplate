package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.account.domain.AccountSub;
import baseline.version2.springboot.common.domain.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToRoleEnumConverter implements Converter<String, AccountSub.RoleEnum> {
    @Override
    public AccountSub.RoleEnum convert(String s) {
        return EnumBase.getEnumFromString(AccountSub.RoleEnum.class, s);
    }
}

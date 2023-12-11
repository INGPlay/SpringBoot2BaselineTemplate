package baseline.version3.springboot.config.converter;

import baseline.version3.springboot.account.domain.AccountSub;
import baseline.version3.springboot.common.domain.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToRoleEnumConverter implements Converter<String, AccountSub.RoleEnum> {
    @Override
    public AccountSub.RoleEnum convert(String s) {
        return EnumBase.getEnumFromString(AccountSub.RoleEnum.class, s);
    }
}

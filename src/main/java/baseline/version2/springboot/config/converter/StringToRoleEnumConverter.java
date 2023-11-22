package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.account.domain.subType.RoleEnum;
import baseline.version2.springboot.common.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToRoleEnumConverter implements Converter<String, RoleEnum> {
    @Override
    public RoleEnum convert(String s) {
        return EnumBase.getEnumFromString(RoleEnum.class, s);
    }
}

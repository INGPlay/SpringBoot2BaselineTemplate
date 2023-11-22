package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.account.domain.subType.OAuthTypeEnum;
import baseline.version2.springboot.common.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToOAuthTypeConverter implements Converter<String, OAuthTypeEnum> {
    @Override
    public OAuthTypeEnum convert(String s) {
        return EnumBase.getEnumFromString(OAuthTypeEnum.class, s);
    }
}

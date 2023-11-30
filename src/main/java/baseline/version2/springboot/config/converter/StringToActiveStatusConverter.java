package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.common.domain.subType.CommonSub;
import baseline.version2.springboot.common.domain.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToActiveStatusConverter implements Converter<String, CommonSub.ActiveStatus> {
    @Override
    public CommonSub.ActiveStatus convert(String s) {
        return EnumBase.getEnumFromString(CommonSub.ActiveStatus.class, s);
    }
}

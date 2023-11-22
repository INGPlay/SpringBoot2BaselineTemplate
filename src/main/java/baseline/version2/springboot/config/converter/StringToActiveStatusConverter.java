package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.common.subType.ActiveStatus;
import baseline.version2.springboot.common.subType.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToActiveStatusConverter implements Converter<String, ActiveStatus> {
    @Override
    public ActiveStatus convert(String s) {
        return EnumBase.getEnumFromString(ActiveStatus.class, s);
    }
}

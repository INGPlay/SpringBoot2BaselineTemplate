package baseline.version2.springboot.config.converter;

import baseline.version2.springboot.common.ActiveStatusEnum;
import baseline.version2.springboot.common.EnumBase;
import org.springframework.core.convert.converter.Converter;

public class StringToActiveStatusConverter implements Converter<String, ActiveStatusEnum> {
    @Override
    public ActiveStatusEnum convert(String s) {
        return EnumBase.getEnumFromString(ActiveStatusEnum.class, s);
    }
}

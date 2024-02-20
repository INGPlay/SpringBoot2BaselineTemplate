package baseline.version3.springboot.common.domain.subType;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public enum ApplyStatus implements EnumBase {

    APPLY("적용"),
    NOT_APPLY("미적용")
    ;

    private final String text;

    @Override
    public String getText() {
        return this.text;
    }

    @JsonCreator
    public static ApplyStatus from(String s){
        if (StringUtils.hasText(s)){
            return ApplyStatus.valueOf(s.toUpperCase());
        }

        return null;
    }
}

package baseline.version2.springboot.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActiveStatusEnum implements EnumBase {
    ACTIVE("활성화"),
    INACTIVE("비활성화")
    ;

    private final String text;
    @Override
    public String getText() {
        return this.text;
    }
}

package baseline.version2.springboot.common.domain.subType;

import lombok.AllArgsConstructor;

public class CommonSub {
    @AllArgsConstructor
    public enum ActiveStatus implements EnumBase {
        ACTIVE("활성화"),
        INACTIVE("비활성화")
        ;

        private final String text;
        @Override
        public String getText() {
            return this.text;
        }
    }
}

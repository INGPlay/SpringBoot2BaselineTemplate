package baseline.version3.springboot.pageAdmin.domain.pageAuthority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PageAuthorityRequest {

    @Getter @Setter
    public static class RequestInsert{
        // 권한 코드
        @NotNull
        @Size(min = 1, max = 64)
        private String pageAuthorityCode;

        // 권한 이름
        @Size(max = 128)
        private String pageAuthorityName;

        // 권한 묘사
        @Size(max = 256)
        private String pageAuthorityDescription;
    }

    @Getter @Setter
    public static class RequestUpdate{

        @NotNull
        private Long pageAuthorityId;

        // 권한 이름
        @Size(max = 128)
        private String pageAuthorityName;

        // 권한 묘사
        @Size(max = 256)
        private String pageAuthorityDescription;
    }

    @Getter @Setter
    public static class RequestDelete{
        @NotNull
        private Long pageAuthorityId;
    }

    @Getter
    public static class RequestDynamicQueryOne {
        private String pageAuthorityCode;

        public RequestDynamicQueryOne(String pageAuthorityCode) {
            this.pageAuthorityCode = pageAuthorityCode;
        }
    }
}

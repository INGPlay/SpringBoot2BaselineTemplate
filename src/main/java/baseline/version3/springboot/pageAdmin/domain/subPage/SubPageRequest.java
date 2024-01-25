package baseline.version3.springboot.pageAdmin.domain.subPage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class SubPageRequest {

    @Getter @Setter
    public static class RequestInsert {

        @NotNull
        private Long parentPageId;

        @Size(max = 128)
        private String subPageTitle;

        @Size(max = 1024)
        private String subPageDescription;

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String subPagePath;

        // 권한 코드
        @Size(max = 64)
        private String pageAuthorityCode;

        public void setSubPagePath(String subPagePath) {
            this.subPagePath = subPagePath.strip();
        }
    }

    @Getter @Setter
    public static class RequestUpdate {
        @NotNull
        private Long parentPageId;
        @NotNull
        private Long subPageId;

        @Size(max = 128)
        private String subPageTitle;

        @Size(max = 1024)
        private String subPageDescription;

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String subPageRootPath;

        // 권한 코드
        @Size(max = 64)
        private String pageAuthorityCode;

        public void setSubPageRootPath(String subPageRootPath) {
            this.subPageRootPath = subPageRootPath.strip();
        }
    }

    @Getter @Setter
    public static class RequestDelete {
        @NotNull
        private Long subPageId;
    }

    @Getter @Setter
    public static class RequestDynamicQuery {
        private Long parentPageId;
    }

    @Getter @Setter
    public static class RequestDynamicQueryOne {
        private Long parentPageId;
        private Long subPageId;
        private String subPagePath;
        private String notSubPagePath;
    }
}

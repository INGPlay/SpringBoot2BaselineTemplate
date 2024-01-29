package baseline.version3.springboot.pageAdmin.domain.parentPage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class ParentPageRequest {

    @Getter @Setter
    public static class RequestInsert {

        @NotNull
        @Size(min = 1, max = 128)
        private String parentPageTitle;

        @Size(max = 1024)
        private String parentPageDescription;

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String parentPageRootPath;

        // 권한 코드
        @Size(max = 64)
        private String pageAuthorityCode;

        public void setParentPageRootPath(String parentPageRootPath) {
            this.parentPageRootPath = parentPageRootPath.strip();
        }
    }

    @Getter @Setter
    public static class RequestUpdate {
        @NotNull
        private Long parentPageId;

        @Size(max = 128)
        private String parentPageTitle;

        @Size(max = 1024)
        private String parentPageDescription;

        // 권한 코드
        @Size(max = 64)
        private String pageAuthorityCode;
    }

    @Getter @Setter
    public static class RequestDelete {
        @NotNull
        private Long parentPageId;
    }

    @Getter @Setter
    public static class RequestDynamicQuery {
        private Long parentPageId;
        private Long notParentPageId;
        private String parentPageRootPath;
    }

    @Getter
    public static class RequestDynamicQueryOne {
        private Long parentPageId;
        private String parentPageRootPath;

        public RequestDynamicQueryOne(Long parentPageId) {
            this.parentPageId = parentPageId;
        }

        public RequestDynamicQueryOne(String parentPageRootPath) {
            this.parentPageRootPath = parentPageRootPath;
        }
    }
}

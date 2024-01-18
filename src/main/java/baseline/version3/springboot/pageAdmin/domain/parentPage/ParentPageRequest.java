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

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String parentPageIndexPath;

        public String getParentPageIndexPath() {
            // 인덱스 경로가 없다면, 루트 경로 반환
            if (this.parentPageIndexPath == null || this.parentPageIndexPath.isEmpty()){
                return this.parentPageRootPath;
            }
            return parentPageIndexPath;
        }

        public void setParentPageRootPath(String parentPageRootPath) {
            this.parentPageRootPath = parentPageRootPath.strip();
        }

        public void setParentPageIndexPath(String parentPageIndexPath) {
            this.parentPageIndexPath = parentPageIndexPath.strip();
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

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String parentPageRootPath;

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String parentPageIndexPath;

        public String getParentPageIndexPath() {
            // 인덱스 경로가 없다면, 루트 경로 반환
            if (this.parentPageIndexPath == null || this.parentPageIndexPath.isEmpty()){
                return this.parentPageRootPath;
            }
            return parentPageIndexPath;
        }

        public void setParentPageRootPath(String parentPageRootPath) {
            this.parentPageRootPath = parentPageRootPath.strip();
        }

        public void setParentPageIndexPath(String parentPageIndexPath) {
            this.parentPageIndexPath = parentPageIndexPath.strip();
        }
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
}

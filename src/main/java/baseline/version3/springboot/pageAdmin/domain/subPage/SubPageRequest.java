package baseline.version3.springboot.pageAdmin.domain.subPage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@RequiredArgsConstructor
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

        public void setSubPagePath(String subPagePath) {
            this.subPagePath = subPagePath.strip();
        }

    }

    @Getter @Setter
    public static class RequestUpdate {
        @NotNull
        private Long subPageId;

        @Size(max = 128)
        private String subPageTitle;

        @Size(max = 1024)
        private String subPageDescription;

        @Pattern(regexp = "^/[a-zA-Z1-9/!@#$%^&*-]{0,}$", message = "첫 글자가 슬래쉬(/)가 아니거나, 허용되지 않은 기호를 사용하였습니다.")
        @Size(max = 128)
        private String subPagePath;

        public void setSubPagePath(String subPagePath) {
            this.subPagePath = subPagePath.strip();
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

    @Getter
    public static class RequestDynamicQueryOne {
        private Long parentPageId;
        private Long subPageId;
        private String subPagePath;
        public RequestDynamicQueryOne(Long subPageId) {
            this.subPageId = subPageId;
        }

        public RequestDynamicQueryOne(Long parentPageId, String subPagePath) {
            this.parentPageId = parentPageId;
            this.subPagePath = subPagePath;
        }
    }
}

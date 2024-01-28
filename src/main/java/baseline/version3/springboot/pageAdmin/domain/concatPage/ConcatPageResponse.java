package baseline.version3.springboot.pageAdmin.domain.concatPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class ConcatPageResponse {
    @Getter
    @AllArgsConstructor
    public static class Response {
        // ParentPage
        private Long parentPageId;
        private String parentPageTitle;
        private String parentPageDescription;
        private String parentPageRootPath;
        private String parentPageIndexPath;

        // PageAuthority
        // 권한 코드
        private String pageAuthorityCode;
        // 권한 이름
        private String pageAuthorityName;
        // 권한 묘사
        private String pageAuthorityDescription;

        // SubPage
        private Long subPageId;
        private String subPageTitle;
        private String subPageDescription;
        private String subPagePath;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date subPageRegisterDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date subPageLastModifyDate;


        // not in constructor parameters
        private String concatPagePath;

        public Response(Long parentPageId, String parentPageTitle, String parentPageDescription, String parentPageRootPath, String parentPageIndexPath, String pageAuthorityCode, String pageAuthorityName, String pageAuthorityDescription, Long subPageId, String subPageTitle, String subPageDescription, String subPagePath, Date subPageRegisterDate, Date subPageLastModifyDate) {
            this.parentPageId = parentPageId;
            this.parentPageTitle = parentPageTitle;
            this.parentPageDescription = parentPageDescription;
            this.parentPageRootPath = parentPageRootPath;
            this.parentPageIndexPath = parentPageIndexPath;
            this.pageAuthorityCode = pageAuthorityCode;
            this.pageAuthorityName = pageAuthorityName;
            this.pageAuthorityDescription = pageAuthorityDescription;
            this.subPageId = subPageId;
            this.subPageTitle = subPageTitle;
            this.subPageDescription = subPageDescription;
            this.subPagePath = subPagePath;
            this.subPageRegisterDate = subPageRegisterDate;
            this.subPageLastModifyDate = subPageLastModifyDate;

            this.concatPagePath = parentPageRootPath.concat(subPagePath).replaceAll("/+", "/");
        }
    }
}

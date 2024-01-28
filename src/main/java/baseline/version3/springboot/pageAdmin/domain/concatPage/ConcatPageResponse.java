package baseline.version3.springboot.pageAdmin.domain.concatPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

public class ConcatPageResponse {

    public static record Response (
            // ParentPage
            Long parentPageId,
            String parentPageTitle,
            String parentPageDescription,
            String parentPageRootPath,
            String parentPageIndexPath,

            // PageAuthority
            // 권한 코드
            String pageAuthorityCode,
            // 권한 이름
            String pageAuthorityName,
            // 권한 묘사
            String pageAuthorityDescription,

            // SubPage
            Long subPageId,
            String subPageTitle,
            String subPageDescription,
            String subPagePath,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date subPageRegisterDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date subPageLastModifyDate
    ){
        public String concatPagePath() {
            return parentPageRootPath.concat(subPagePath).replaceAll("/+", "/");
        }
    }
}

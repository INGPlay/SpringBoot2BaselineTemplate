package baseline.version3.springboot.pageAdmin.domain.subPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class SubPageResponse {
    
    public record Response(
            Long subPageId,
            String subPageTitle,
            String subPageDescription,
            String subPagePath,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date registerDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date lastModifyDate,

            Long parentPageId,
            String parentPageTitle,
            String parentPageRootPath,
            String parentPageDescription,

            String pageAuthorityCode,
            String pageAuthorityName,
            String concatPagePath
    ) {
        public Response(Long subPageId, String subPageTitle, String subPageDescription, String subPagePath, Date registerDate, Date lastModifyDate, Long parentPageId, String parentPageTitle, String parentPageRootPath, String parentPageDescription, String pageAuthorityCode, String pageAuthorityName) {
            this(subPageId, subPageTitle, subPageDescription, subPagePath, registerDate, lastModifyDate, parentPageId, parentPageTitle, parentPageRootPath, parentPageDescription, pageAuthorityCode, pageAuthorityName,
                    parentPageRootPath.concat(subPagePath).replaceAll("/+", "/"));
        }

        public Response(Long subPageId, String subPageTitle, String subPageDescription, String subPagePath, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        Date registerDate, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
                        Date lastModifyDate, Long parentPageId, String parentPageTitle, String parentPageRootPath, String parentPageDescription, String pageAuthorityCode, String pageAuthorityName, String concatPagePath) {

            assert false : "다른 생성자를 사용하세요.";

            this.subPageId = subPageId;
            this.subPageTitle = subPageTitle;
            this.subPageDescription = subPageDescription;
            this.subPagePath = subPagePath;
            this.registerDate = registerDate;
            this.lastModifyDate = lastModifyDate;
            this.parentPageId = parentPageId;
            this.parentPageTitle = parentPageTitle;
            this.parentPageRootPath = parentPageRootPath;
            this.parentPageDescription = parentPageDescription;
            this.pageAuthorityCode = pageAuthorityCode;
            this.pageAuthorityName = pageAuthorityName;
            this.concatPagePath = concatPagePath;
        }
    }
}

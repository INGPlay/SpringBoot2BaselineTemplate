package baseline.version3.springboot.pageAdmin.page.domain.subPage;

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

    }
}

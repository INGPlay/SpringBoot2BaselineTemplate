package baseline.version3.springboot.pageAdmin.domain.parentPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

public class ParentPageResponse {

    public record Response(
            Long parentPageId,
            String parentPageTitle,
            String parentPageDescription,
            String parentPageRootPath,
            Long countSubPageList,

            String pageAuthorityCode,
            String pageAuthorityName,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date registerDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date lastModifyDate
    ) {

        public Response {
            pageAuthorityCode = Objects.requireNonNullElse(pageAuthorityCode, "");
            pageAuthorityName = Objects.requireNonNullElse(pageAuthorityName, "");
        }
    }
}

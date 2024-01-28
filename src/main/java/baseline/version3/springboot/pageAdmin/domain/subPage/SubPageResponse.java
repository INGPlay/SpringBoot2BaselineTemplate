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
            String pageAuthorityCode,
            String pageAuthorityName,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date registerDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date lastModifyDate
    ) {

    }
}

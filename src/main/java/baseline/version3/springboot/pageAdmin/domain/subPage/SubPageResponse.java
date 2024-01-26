package baseline.version3.springboot.pageAdmin.domain.subPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class SubPageResponse {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long subPageId;
        private String subPageTitle;
        private String subPageDescription;
        private String subPagePath;
        private String pageAuthorityCode;
        private String pageAuthorityName;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date registerDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date lastModifyDate;
    }
}

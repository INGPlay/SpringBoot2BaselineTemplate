package baseline.version3.springboot.pageAdmin.domain.parentPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class ParentPageResponse {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long parentPageId;
        private String parentPageTitle;
        private String parentPageDescription;
        private String parentPageRootPath;
        private String parentPageIndexPath;
        private Long countSubPageList;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date registerDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date lastModifyDate;
    }
}

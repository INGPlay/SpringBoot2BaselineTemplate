package baseline.version3.springboot.pageAdmin.domain.pageAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class PageAuthorityResponse {

    @Getter
    @AllArgsConstructor
    public static class Response {
        // 권한 id
        private Long pageAuthorityId;

        // 권한 코드
        private String pageAuthorityCode;

        // 권한 이름
        private String pageAuthorityName;

        // 권한 묘사
        private String pageAuthorityDescription;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date registerDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date lastModifyDate;
    }
}

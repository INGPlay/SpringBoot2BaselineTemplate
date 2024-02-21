package baseline.version3.springboot.pageAdmin.page.domain.pageAuthority;

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
import java.util.Objects;

public class PageAuthorityResponse {

    public record Response (
            // 권한 id
            Long pageAuthorityId,

            // 권한 코드
            String pageAuthorityCode,

            // 권한 이름
            String pageAuthorityName,

            // 권한 묘사
            String pageAuthorityDescription,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date registerDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
            Date lastModifyDate
    ){

        public Response {
            pageAuthorityCode = Objects.requireNonNullElse(pageAuthorityCode, "");
            pageAuthorityName = Objects.requireNonNullElse(pageAuthorityName, "");
        }
    }
}

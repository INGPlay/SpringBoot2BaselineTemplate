package baseline.version3.springboot.ipAccess.domain;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public class IpAccessResponse {

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long ipAccessId;

        private String ipAddress;

        private ApplyStatus applyStatus;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date registerDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date lastModifyDate;

    }
}

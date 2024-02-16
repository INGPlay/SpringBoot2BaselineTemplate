package baseline.version3.springboot.ipAccess.domain;

import baseline.version3.springboot.common.domain.subType.CommonStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class IpAccessResponse {

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long ipAccessId;

        private String ipAddress;

        private CommonStatus.ActiveStatus activeStatus;

        private Date registerDate;
        private Date lastModifyDate;

    }
}

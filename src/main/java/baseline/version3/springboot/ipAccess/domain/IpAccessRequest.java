package baseline.version3.springboot.ipAccess.domain;

import baseline.version3.springboot.common.domain.subType.CommonStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class IpAccessRequest {

    @Getter @Setter
    public static class Request {

        @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$\n")
        private String ipAddress;

        @NotNull
        private CommonStatus.ActiveStatus activeStatus;

        @Max(value = 100)
        private String ipAccessDescription;
    }

    @Getter @Setter
    public static class RequestUpdate{

        @NotNull
        private Long ipAccessId;

        @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$\n")
        private String ipAddress;

        @NotNull
        private CommonStatus.ActiveStatus activeStatus;

        @Max(value = 100)
        private String ipAccessDescription;
    }

    @Getter @Setter
    public static class RequestDelete{
        @NotNull
        private Long ipAccessId;
    }

    @Getter
    public static class RequestDynamicQueryOne {
        private final Long ipAccessId;

        public RequestDynamicQueryOne(Long ipAccessId) {
            this.ipAccessId = ipAccessId;
        }
    }
}

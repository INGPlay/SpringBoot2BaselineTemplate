package baseline.version3.springboot.ipAccess.domain;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class IpAccessRequest {

    @Getter @Setter
    public static class Request {

        @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$",
                message = "IP 주소 형식에 맞춰 작성해주세요.")
        private String ipAddress;

        @NotNull(message = "활성화 상태를 선택해주세요.")
        private ApplyStatus applyStatus;

        @Size(max = 30, message = "최대 {max}자까지 작성할 수 있습니다.")
        private String ipAccessDescription;
    }

    @Getter @Setter
    public static class RequestUpdate{

        @NotNull
        private Long ipAccessId;

        @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$",
                message = "IP 주소 형식에 맞춰 작성해주세요.")
        private String ipAddress;

        @NotNull(message = "활성화 상태를 선택해주세요.")
        private ApplyStatus applyStatus;

        @Max(value = 30, message = "최대 {max}자까지 작성할 수 있습니다.")
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

package baseline.version3.springboot.project.account.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class AccountRequest {
    @Getter
    @Setter
    public static class RegisterAccountDTO {

        @Pattern(regexp = "^[a-z0-9]{6,20}$", message = "6 ~ 20자의 영어 소문자 및 숫자로만 구성되야 합니다.")
        private String accountName;

        @Pattern(regexp = "^[a-zA-Z0-9!@#$%\\-+]{8,32}", message = "8 ~ 32자의 영어 및 숫자 그리고 특수문자 !,@,#,$,%,-,+를 허용됩니다.")
        private String accountPassword;

        private String accountPasswordCheck;

        @Pattern(regexp = "^[가-힣A-Za-z0-9]{4,20}$", message = "한글 및 영어 숫자로 이루어질 수 있습니다.")
        private String accountDisplayName;

        @NotNull(message = "생성할 계정을 선택해주세요.")
        private AccountSub.RoleEnum accountRole;
        private AccountSub.OAuthTypeEnum oauthType;
    }

    @Getter @Setter
    public static class UpdateAccountDTO{
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%\\-+]{8,32}", message = "8 ~ 32자의 영어 및 숫자 그리고 특수문자 !,@,#,$,%,-,+를 허용됩니다.")
        private String accountPassword;

        private String accountPasswordCheck;

        @Pattern(regexp = "^[가-힣A-Za-z0-9]{4,20}$", message = "한글 및 영어 숫자로 이루어질 수 있습니다.")
        private String accountDisplayName;
    }

}

package baseline.version3.springboot.account.controller.api;

import baseline.version3.springboot.account.domain.AccountRequest;
import baseline.version3.springboot.account.domain.AccountSub;
import baseline.version3.springboot.account.repository.AccountRepository;
import baseline.version3.springboot.account.service.AccountService;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.response.ResponseForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;

    private final ResponseUtil responseUtil;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseForm> registerAccount(@RequestBody @Valid AccountRequest.RegisterAccountDTO registerAccountDTO,
                                                        BindingResult bindingResult){
        if (isNotValidateForRegister(registerAccountDTO, bindingResult)){
            return responseUtil.makeResponseEntity(false, bindingResult.getFieldErrors());
        }

        registerAccountDTO.setOauthType(AccountSub.OAuthTypeEnum.LOCAL);
        accountService.createAccount(registerAccountDTO);

        return responseUtil.makeResponseEntity();
    }

    private boolean isNotValidateForRegister(AccountRequest.RegisterAccountDTO registerAccountDTO, BindingResult bindingResult) {

        if (accountService.isInDb(registerAccountDTO.getAccountName())){
            FieldError fieldError = new FieldError("accountName", "accountName", "이미 등록된 계정이름입니다.");
            bindingResult.addError(fieldError);
        }

        if (!registerAccountDTO.getAccountPassword().equals(registerAccountDTO.getAccountPasswordCheck())){
            FieldError fieldError = new FieldError("accountPasswordCheck", "accountPasswordCheck", "비밀번호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()){
            return true;
        }

        return false;
    }
}

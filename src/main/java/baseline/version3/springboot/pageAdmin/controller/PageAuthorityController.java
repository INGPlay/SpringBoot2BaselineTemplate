package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.controllerAdvice.exception.CustomValidationException;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.service.PageAuthorityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page-admin/page/auth")
@RequiredArgsConstructor
public class PageAuthorityController {

    private final PageAuthorityService pageAuthorityService;

    @GetMapping("/list")
    public String registerPage(){
        return "page-admin/page/auth/list";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute PageAuthorityRequest.RequestInsert requestInsert,
                           BindingResult bindingResult,
                           HttpServletRequest httpServletRequest){

        if (isNotValidateForRegister(requestInsert, bindingResult)){
            return "list";
        }

        pageAuthorityService.createAuth(requestInsert);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    private boolean isNotValidateForRegister(PageAuthorityRequest.RequestInsert requestInsert, BindingResult bindingResult) {

        validateDuplicatePageAuthorityCode(requestInsert.getPageAuthorityCode(), bindingResult);

        log.info("{}", bindingResult);

        if (bindingResult.hasErrors()){
            return true;
        }

        return false;
    }


    private void validateDuplicatePageAuthorityCode(String pageAuthorityCode, BindingResult bindingResult) {
        PageAuthorityRequest.RequestDynamicQueryOne requestDynamicQueryOne = new PageAuthorityRequest.RequestDynamicQueryOne(pageAuthorityCode);
        if (pageAuthorityService.findOne(requestDynamicQueryOne).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "pageAuthorityCode", "이미 존재하는 권한 코드입니다.");
            bindingResult.addError(fieldError);
            throw new CustomValidationException(bindingResult);
        }
    }
}

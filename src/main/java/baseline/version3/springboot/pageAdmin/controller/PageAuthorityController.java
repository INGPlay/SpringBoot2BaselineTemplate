package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.service.PageAuthorityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("requestInsert", new PageAuthorityRequest.RequestInsert());
        return "page-admin/page/auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute PageAuthorityRequest.RequestInsert requestInsert,
                           BindingResult bindingResult,
                           HttpServletRequest httpServletRequest){

        if (isNotValidateForRegister(requestInsert, bindingResult)){
            return "page-admin/page/auth/register";
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
        PageAuthorityRequest.RequestDynamicQueryOne requestDynamicQueryOne = new PageAuthorityRequest.RequestDynamicQueryOne();
        requestDynamicQueryOne.setPageAuthorityCode(pageAuthorityCode);
        if (pageAuthorityService.findOne(requestDynamicQueryOne).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "pageAuthorityCode", "이미 존재하는 권한 코드입니다.");
            bindingResult.addError(fieldError);
        }
    }
}

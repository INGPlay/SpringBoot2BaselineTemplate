package baseline.version3.springboot.pageAdmin.page.controller;

import baseline.version3.springboot.controllerAdvice.exception.CustomValidationException;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.page.service.PageAuthorityService;
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
    @GetMapping("/list")
    public String registerPage(){
        return "page-admin/page/auth/list";
    }
}

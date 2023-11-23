package baseline.version2.springboot.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/login")
    public String loginPage(){
        return "account/login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "account/register";
    }
}

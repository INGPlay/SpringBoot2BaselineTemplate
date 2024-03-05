package baseline.version3.springboot.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {

    @Value("${custom.page.intro}")
    private String introPage;

    @@GetMapping
    public String rootPage(){
        return "redirect:" + introPage;
    }

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }

}

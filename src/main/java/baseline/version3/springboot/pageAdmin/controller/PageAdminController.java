package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.pageAdmin.service.PageAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page-admin")
@RequiredArgsConstructor
public class PageAdminController {

    private final PageAdminService pageAdminService;

    @GetMapping
    public String pageAdminRoot(){
        return "redirect:/page-admin/index";
    }

    @GetMapping("/index")
    public String pageAdminIndex(){
        return "page-admin/index";
    }
}

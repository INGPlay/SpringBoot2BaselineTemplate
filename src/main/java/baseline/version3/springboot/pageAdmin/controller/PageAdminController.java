package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/page-admin")
@RequiredArgsConstructor
public class PageAdminController {

    private final ParentPageService parentPageService;

    @GetMapping
    public String pageAdminRoot(){
        return "redirect:/page-admin/index";
    }

    @GetMapping("/index")
    public String pageAdminIndex(){
        return "page-admin/index";
    }

    @GetMapping("/page/hierachy")
    public String hierachyPage(){
        return "page-admin/page/hierachy";
    }

    @GetMapping("/develop")
    public String developPage(){
        return "page-admin/develop";
    }
}

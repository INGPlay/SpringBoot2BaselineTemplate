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
    @GetMapping(value = { "/index"})
    public String pageAdminIndex(){
        return "page-admin/index";
    }
}

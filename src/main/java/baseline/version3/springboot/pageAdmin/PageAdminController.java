package baseline.version3.springboot.pageAdmin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/page-admin")
@RequiredArgsConstructor
public class PageAdminController {

    @GetMapping
    public String pageAdmin(){
        return "redirect:/page-admin/index";
    }
    @GetMapping("/index")
    public String pageAdminIndex(){
        return "page-admin/index";
    }
}

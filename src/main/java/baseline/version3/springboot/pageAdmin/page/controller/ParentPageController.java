package baseline.version3.springboot.pageAdmin.page.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/page-admin/page")
@RequiredArgsConstructor
public class ParentPageController {
    /**
     * list 페이지
     * @return
     */
    @GetMapping("/list")
    public String listPage(){
        return "page-admin/page/list";
    }
}

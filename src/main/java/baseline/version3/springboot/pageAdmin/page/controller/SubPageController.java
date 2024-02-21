package baseline.version3.springboot.pageAdmin.page.controller;

import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.page.service.SubPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/page-admin/page/sub")
@RequiredArgsConstructor
public class SubPageController {

    /**
     * register 페이지
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String registerPage(@RequestParam Long parentPageId,
                               Model model){
        SubPageRequest.RequestInsert requestInsert = new SubPageRequest.RequestInsert();
        requestInsert.setParentPageId(parentPageId);
        model.addAttribute("requestInsert", requestInsert);

        return "page-admin/page/sub/register";
    }
}

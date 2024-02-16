package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
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

    /**
     * register 페이지
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("requestInsert", new ParentPageRequest.RequestInsert());
        return "page-admin/page/register";
    }


    /**
     * Update 페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/list/update")
    public String updatePage(@RequestParam Long id,
                             Model model){
        model.addAttribute("id", id);
        return "page-admin/page/update";
    }
}

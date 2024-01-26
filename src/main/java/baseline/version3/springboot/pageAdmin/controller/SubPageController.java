package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
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

    private final SubPageService subPageService;

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

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid SubPageRequest.RequestInsert requestInsert,
                           BindingResult bindingResult){
        if (isNotValidateForRegister(requestInsert, bindingResult)){
            return "page-admin/page/sub/register";
        }

        subPageService.registerSubPage(requestInsert);
        return "redirect:/page-admin/page/list";
    }

    private boolean isNotValidateForRegister(SubPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {

        validateDuplicateSubPagePath(requestInsert.getParentPageId(), requestInsert.getSubPagePath(), bindingResult);

        log.info("{}", bindingResult);

        if (bindingResult.hasErrors()){
            return true;
        }

        return false;
    }

    private void validateDuplicateSubPagePath(Long parentPageId, String subPagePath, BindingResult bindingResult) {
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(parentPageId, subPagePath);
        if (subPageService.findOne(requestDynamicQueryOne).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "subPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);
        }
    }
}

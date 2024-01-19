package baseline.version3.springboot.pageAdmin.controller;

import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
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

    private final ParentPageService parentPageService;

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

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid ParentPageRequest.RequestInsert requestInsert,
                           BindingResult bindingResult){
        if (isNotValidateForRegister(requestInsert, bindingResult)){
            return "page-admin/page/register";
        }

        parentPageService.registerParentPage(requestInsert);
        return "redirect:/page-admin/page/list";
    }

    private boolean isNotValidateForRegister(ParentPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {

        validateDuplicateParentPageRootPath(requestInsert.getParentPageRootPath(), bindingResult);

        log.info("{}", bindingResult);

        return bindingResult.hasErrors();
    }

    private void validateDuplicateParentPageRootPath(String parentPageRootPath, BindingResult bindingResult) {
        ParentPageRequest.RequestDynamicQuery requestDynamicQuery = new ParentPageRequest.RequestDynamicQuery();
        requestDynamicQuery.setParentPageRootPath(parentPageRootPath);
        if (parentPageService.findOne(requestDynamicQuery).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "parentPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);
        }
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

    @PostMapping("/list/update")
    public String update(@ModelAttribute @Valid ParentPageRequest.RequestUpdate requestUpdate,
                         BindingResult bindingResult,
                         Model model){
        if (isNotValidateForUpdate(requestUpdate, bindingResult)){

            ParentPageResponse.Response response = parentPageService.findOneById(requestUpdate.getParentPageId()).orElseThrow(() -> {
                throw new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY);
            });
            model.addAttribute("registerDate", response.getRegisterDate());
            model.addAttribute("lastModifyDate", response.getLastModifyDate());
            return "page-admin/page/update";
        }

        parentPageService.updateParentPage(requestUpdate);
        return "redirect:/page-admin/page/list";
    }

    private boolean isNotValidateForUpdate(ParentPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {

        validateDuplicateParentPageRootPath(requestUpdate.getParentPageRootPath(), bindingResult);

        log.info("{}", bindingResult);

        return bindingResult.hasErrors();
    }
}

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

import java.util.Optional;

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

    @GetMapping("/page/list")
    public String listPage(){
        return "page-admin/page/list";
    }

    @GetMapping("/page/list/update")
    public String updatePage(@RequestParam Long id,
                             Model model){
        fillUpdatePage(id, model);
        return "page-admin/page/update";
    }

    @PostMapping("/page/list/update")
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

    private void fillUpdatePage(Long id, Model model) {
        ParentPageResponse.Response response = parentPageService.findOneById(id).orElseThrow(() -> {
            throw new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY);
        });
        ParentPageRequest.RequestUpdate requestUpdate = new ParentPageRequest.RequestUpdate();
        requestUpdate.setParentPageId(response.getParentPageId());
        requestUpdate.setParentPageTitle(response.getParentPageTitle());
        requestUpdate.setParentPageDescription(response.getParentPageDescription());
        requestUpdate.setParentPageRootPath(response.getParentPageRootPath());
        requestUpdate.setParentPageIndexPath(response.getParentPageIndexPath());

        model.addAttribute("requestUpdate", requestUpdate);
        model.addAttribute("registerDate", response.getRegisterDate());
        model.addAttribute("lastModifyDate", response.getLastModifyDate());
    }

    private boolean isNotValidateForUpdate(ParentPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {

        String rootPath = requestUpdate.getParentPageRootPath().strip();
        String indexPath = requestUpdate.getParentPageIndexPath().strip();

        ParentPageRequest.RequestDynamicQuery requestDynamicQuery = new ParentPageRequest.RequestDynamicQuery();
        requestDynamicQuery.setNotParentPageId(requestUpdate.getParentPageId());
        requestDynamicQuery.setParentPageRootPath(rootPath);
        if (parentPageService.findOne(requestDynamicQuery).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "parentPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);

        } else {
            if (!bindingResult.hasFieldErrors("parentPageRootPath")){
                // 루트 경로가 앞에 포함되어 있는지 확인
                if (!indexPath.startsWith(rootPath)){
                    FieldError fieldError = new FieldError("isNotContainRootPath", "parentPageIndexPath", "인덱스 경로에 루트경로를 포함해주세요.");
                    bindingResult.addError(fieldError);
                }
            }
        }

        log.info("{}", bindingResult);

        if (bindingResult.hasErrors()){
            return true;
        }

        return false;
    }

    @GetMapping("/page/register")
    public String registerPage(Model model){
        model.addAttribute("requestInsert", new ParentPageRequest.RequestInsert());
        return "page-admin/page/register";
    }

    @PostMapping("/page/register")
    public String register(@ModelAttribute @Valid ParentPageRequest.RequestInsert requestInsert,
                           BindingResult bindingResult){
        if (isNotValidateForRegister(requestInsert, bindingResult)){
            return "page-admin/page/register";
        }

        parentPageService.registerParentPage(requestInsert);
        return "redirect:/page-admin/page/list";
    }

    private boolean isNotValidateForRegister(ParentPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {

        String rootPath = requestInsert.getParentPageRootPath().strip();
        String indexPath = requestInsert.getParentPageIndexPath().strip();

        if (parentPageService.findOneByRoot(rootPath).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "parentPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);

        } else {
            if (!bindingResult.hasFieldErrors("parentPageRootPath")){
                // 루트 경로가 앞에 포함되어 있는지 확인
                if (!indexPath.startsWith(rootPath)){
                    FieldError fieldError = new FieldError("isNotContainRootPath", "parentPageIndexPath", "인덱스 경로에 루트경로를 포함해주세요.");
                    bindingResult.addError(fieldError);
                }
            }
        }

        log.info("{}", bindingResult);

        if (bindingResult.hasErrors()){
            return true;
        }

        return false;
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

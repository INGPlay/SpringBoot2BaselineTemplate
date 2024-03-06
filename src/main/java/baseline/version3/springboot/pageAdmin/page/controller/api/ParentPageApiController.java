package baseline.version3.springboot.pageAdmin.page.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.controllerAdvice.exception.CustomValidationException;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.page.service.ParentPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/parent-page")
@RequiredArgsConstructor
public class ParentPageApiController {

    private final ParentPageService parentPageService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<ResponseForm> list(){
        List<ParentPageResponse.Response> list = parentPageService.findList();
        return ResponseUtil.makeResponseEntity(list);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseForm> one(@PathVariable Long id){
        ParentPageResponse.Response response = parentPageService.findOneById(id).orElseThrow(() ->
                new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        return ResponseUtil.makeResponseEntity(response);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<ResponseForm> create(@Valid @RequestBody ParentPageRequest.RequestInsert requestInsert,
                                               BindingResult bindingResult){

        validateForRegister(requestInsert, bindingResult);

        parentPageService.registerParentPage(requestInsert);
        return ResponseUtil.makeResponseEntity();
    }

    private void validateForRegister(ParentPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {
        checkHasErrors(bindingResult);
        validateDuplicateParentPageRootPath(requestInsert.getParentPageRootPath(), bindingResult);
    }

    private void validateDuplicateParentPageRootPath(String parentPageRootPath, BindingResult bindingResult) {
        ParentPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new ParentPageRequest.RequestDynamicQueryOne(parentPageRootPath);
        if (parentPageService.findOne(requestDynamicQueryOne).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "parentPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);
            throw new CustomValidationException(bindingResult);
        }
    }

    private void checkHasErrors(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new CustomValidationException(bindingResult);
        }
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<ResponseForm> update(@Valid @RequestBody ParentPageRequest.RequestUpdate requestUpdate,
                                               BindingResult bindingResult){
        validateForUpdate(requestUpdate, bindingResult);
        parentPageService.updateParentPage(requestUpdate);
        return ResponseUtil.makeResponseEntity();
    }

    private void validateForUpdate(ParentPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {

        checkHasErrors(bindingResult);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        parentPageService.deleteParentPageById(requestDelete.getParentPageId());
        return ResponseUtil.makeResponseEntity();
    }
}

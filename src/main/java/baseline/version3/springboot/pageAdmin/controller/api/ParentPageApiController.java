package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.response.ResponseForm;
import baseline.version3.springboot.exceptionHandler.exception.CustomValidationException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/parent-page")
@RequiredArgsConstructor
public class ParentPageApiController {

    private final ResponseUtil responseUtil;
    private final ParentPageService parentPageService;
    @GetMapping
    public ResponseEntity<ResponseForm> list(){
        List<ParentPageResponse.Response> list = parentPageService.findList();
        return responseUtil.makeResponseEntity(list);
    }

    @PostMapping
    public ResponseEntity<ResponseForm> create(@Valid @RequestBody ParentPageRequest.RequestInsert requestInsert,
                                               BindingResult bindingResult){

        validateForRegister(requestInsert, bindingResult);

        parentPageService.registerParentPage(requestInsert);
        return responseUtil.makeResponseEntity();
    }

    private void validateForRegister(ParentPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {
        checkHasErrors(bindingResult);
        validateDuplicateParentPageRootPath(requestInsert.getParentPageRootPath(), bindingResult);
        checkHasErrors(bindingResult);
    }

    private void validateDuplicateParentPageRootPath(String parentPageRootPath, BindingResult bindingResult) {
        ParentPageRequest.RequestDynamicQuery requestDynamicQuery = new ParentPageRequest.RequestDynamicQuery();
        requestDynamicQuery.setParentPageRootPath(parentPageRootPath);
        if (parentPageService.findOne(requestDynamicQuery).isPresent()){
            FieldError fieldError = new FieldError("duplicated", "parentPageRootPath", "이미 존재하는 루트 경로입니다.");
            bindingResult.addError(fieldError);
        }
    }

    private void checkHasErrors(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new CustomValidationException(bindingResult);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseForm> update(@Valid @RequestBody ParentPageRequest.RequestUpdate requestUpdate,
                                               BindingResult bindingResult){
        isNotValidateForUpdate(requestUpdate, bindingResult);
        parentPageService.updateParentPage(requestUpdate);
        return responseUtil.makeResponseEntity();
    }

    private boolean isNotValidateForUpdate(ParentPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {

        checkHasErrors(bindingResult);
        validateDuplicateParentPageRootPath(requestUpdate.getParentPageRootPath(), bindingResult);
        checkHasErrors(bindingResult);

        return bindingResult.hasErrors();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        parentPageService.deleteParentPageById(requestDelete.getParentPageId());
        return responseUtil.makeResponseEntity();
    }
}

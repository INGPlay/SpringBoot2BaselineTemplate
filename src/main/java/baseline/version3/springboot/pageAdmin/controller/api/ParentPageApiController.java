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
        if (isNotValidateForRegister(requestInsert, bindingResult)){
            throw new CustomValidationException(bindingResult);
        }

        parentPageService.registerParentPage(requestInsert);
        return responseUtil.makeResponseEntity();
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

    @PutMapping
    public ResponseEntity<ResponseForm> update(@Valid @RequestBody ParentPageRequest.RequestUpdate requestUpdate,
                                               BindingResult bindingResult){
        if (isNotValidateForUpdate(requestUpdate, bindingResult)){
            throw new CustomValidationException(bindingResult);
        }
        parentPageService.updateParentPage(requestUpdate);
        return responseUtil.makeResponseEntity();
    }

    private boolean isNotValidateForUpdate(ParentPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {

        validateDuplicateParentPageRootPath(requestUpdate.getParentPageRootPath(), bindingResult);

        log.info("{}", bindingResult);

        return bindingResult.hasErrors();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        parentPageService.deleteParentPageById(requestDelete.getParentPageId());
        return responseUtil.makeResponseEntity();
    }
}

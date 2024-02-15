package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import faraway.util.restformat.domain.exception.CustomValidationException;
import faraway.util.restformat.domain.exception.ServiceException;
import faraway.util.restformat.domain.exception.ServiceLayerException;
import faraway.util.restformat.domain.response.ResponseForm;
import faraway.util.restformat.util.ResponseUtil;
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

    private final ParentPageService parentPageService;
    @GetMapping
    public ResponseEntity<ResponseForm> list(){
        List<ParentPageResponse.Response> list = parentPageService.findList();
        return ResponseUtil.makeResponseEntity(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseForm> one(@PathVariable Long id){
        ParentPageResponse.Response response = parentPageService.findOneById(id).orElseThrow(() ->
                new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        return ResponseUtil.makeResponseEntity(response);
    }

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

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        parentPageService.deleteParentPageById(requestDelete.getParentPageId());
        return ResponseUtil.makeResponseEntity();
    }
}

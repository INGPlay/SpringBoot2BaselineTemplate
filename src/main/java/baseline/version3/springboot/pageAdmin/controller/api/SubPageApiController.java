package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.util.ValidationUtil;
import baseline.version3.springboot.controllerAdvice.exception.CustomValidationException;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/parent-page/sub-page")
@RequiredArgsConstructor
public class SubPageApiController {

    private final SubPageService subPageService;
    @GetMapping
    public ResponseEntity<ResponseForm> list(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        List<SubPageResponse.Response> list = subPageService.findList(requestDynamicQuery);
        return ResponseUtil.makeResponseEntity(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseForm> one(@PathVariable Long id){

        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(id);

        SubPageResponse.Response response = subPageService.findOne(requestDynamicQueryOne).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        return ResponseUtil.makeResponseEntity(response);
    }

    @PostMapping
    public ResponseEntity<ResponseForm> register(@Valid @RequestBody SubPageRequest.RequestInsert requestInsert,
                                                 BindingResult bindingResult){
        ValidationUtil.checkErrors(bindingResult);
        validateForRegister(requestInsert, bindingResult);

        subPageService.registerSubPage(requestInsert);
        return ResponseUtil.makeResponseEntity();
    }

    private void validateForRegister(SubPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(
                requestInsert.getParentPageId(), requestInsert.getSubPagePath()
        );

        if (subPageService.findOne(requestDynamicQueryOne).isPresent()){
            FieldError fieldError = new FieldError("Duplicated", "subPagePath", "중복되는 경로가 존재합니다.");
            bindingResult.addError(fieldError);
            throw new CustomValidationException(bindingResult);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseForm> update(@Valid @RequestBody SubPageRequest.RequestUpdate requestUpdate,
                                               BindingResult bindingResult){
        ValidationUtil.checkErrors(bindingResult);
        validateForUpdate(requestUpdate, bindingResult);

        subPageService.updateSubPage(requestUpdate);

        return ResponseUtil.makeResponseEntity();
    }

    private void validateForUpdate(SubPageRequest.RequestUpdate requestUpdate, BindingResult bindingResult) {
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(
                requestUpdate.getSubPageId()
        );

        SubPageResponse.Response response = subPageService.findOne(requestDynamicQueryOne).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        // 같은 경로가 입력된 게 아니고
        if (!requestUpdate.getSubPagePath().equals(response.subPagePath())){

            SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne1 = new SubPageRequest.RequestDynamicQueryOne(
                    response.parentPageId(), requestUpdate.getSubPagePath()
            );
            // 같은 경로가 존재한다면
            if (subPageService.findOne(requestDynamicQueryOne1).isPresent()){
                FieldError fieldError = new FieldError("Duplicated", "subPagePath", "중복되는 경로가 존재합니다.");
                bindingResult.addError(fieldError);
                throw new CustomValidationException(bindingResult);
            }

        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody SubPageRequest.RequestDelete requestDelete){
        subPageService.deleteParentPageById(requestDelete.getSubPageId());
        return ResponseUtil.makeResponseEntity();
    }
}

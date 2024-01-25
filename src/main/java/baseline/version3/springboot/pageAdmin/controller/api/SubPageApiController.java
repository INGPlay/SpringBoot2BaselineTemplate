package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.util.ValidationUtil;
import baseline.version3.springboot.exceptionHandler.exception.CustomValidationException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
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

    @PostMapping
    public ResponseEntity<ResponseForm> register(@Valid @RequestBody SubPageRequest.RequestInsert requestInsert,
                                                 BindingResult bindingResult){
        ValidationUtil.checkErrors(bindingResult);
        validateForRegister(requestInsert, bindingResult);

        subPageService.registerSubPage(requestInsert);
        return ResponseUtil.makeResponseEntity();
    }

    private void validateForRegister(SubPageRequest.RequestInsert requestInsert, BindingResult bindingResult) {
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne();
        requestDynamicQueryOne.setParentPageId(requestInsert.getParentPageId());
        requestDynamicQueryOne.setSubPagePath(requestInsert.getSubPagePath());

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

        subPageService.updateSubPage(requestUpdate);

        return ResponseUtil.makeResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        subPageService.deleteParentPageById(requestDelete.getParentPageId());
        return ResponseUtil.makeResponseEntity();
    }
}

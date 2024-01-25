package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.domain.ResponseTypeForm;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.exceptionHandler.exception.CustomValidationException;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityResponse;
import baseline.version3.springboot.pageAdmin.service.PageAuthorityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/page-admin/type")
@RequiredArgsConstructor
public class PageAdminTypeApiController {

    private final PageAuthorityService pageAuthorityService;
    @GetMapping("/authority")
    public ResponseEntity<ResponseForm> authorityType(){
        List<ResponseTypeForm> responseTypeList = pageAuthorityService.findList().stream().map(authority -> {
            return new ResponseTypeForm(
                    authority.getPageAuthorityName() + " (" + authority.getPageAuthorityCode() +  ")",
                    authority.getPageAuthorityCode());
        }).toList();
        return ResponseUtil.makeResponseEntity(responseTypeList);
    }
}

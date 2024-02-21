package baseline.version3.springboot.pageAdmin.page.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.domain.ResponseTypeForm;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.pageAdmin.page.service.PageAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    authority.pageAuthorityName() + " (" + authority.pageAuthorityCode() +  ")",
                    authority.pageAuthorityCode());
        }).toList();
        return ResponseUtil.makeResponseEntity(responseTypeList);
    }
}

package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.response.ResponseForm;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityResponse;
import baseline.version3.springboot.pageAdmin.service.PageAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/page-admin/auth")
@RequiredArgsConstructor
public class PageAuthorityApiController {

    private final PageAuthorityService pageAuthorityService;
    private final ResponseUtil responseUtil;

    @GetMapping
    public ResponseEntity<ResponseForm> findList(){
        List<PageAuthorityResponse.Response> list = pageAuthorityService.findList();
        return responseUtil.makeResponseEntity(list);
    }
}

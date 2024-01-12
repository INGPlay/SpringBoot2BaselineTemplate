package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.response.ResponseForm;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/parent-page/sub-page")
@RequiredArgsConstructor
public class SubPageApiController {

    private final ResponseUtil responseUtil;
    private final SubPageService subPageService;
    @GetMapping
    public ResponseEntity<ResponseForm> list(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        List<SubPageResponse.Response> list = subPageService.findList(requestDynamicQuery);
        return responseUtil.makeResponseEntity(list);
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
//        subPageService.deleteParentPageById(requestDelete.getParentPageId());
        return responseUtil.makeResponseEntity();
    }
}

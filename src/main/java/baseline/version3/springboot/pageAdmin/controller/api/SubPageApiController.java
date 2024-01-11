package baseline.version3.springboot.pageAdmin.controller.api;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.response.ResponseForm;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.service.ParentPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/parent-page/{subPageId}")
@RequiredArgsConstructor
public class SubPageApiController {

    private final ResponseUtil responseUtil;
    private final ParentPageService parentPageService;
    @GetMapping
    public ResponseEntity<ResponseForm> list(@PathVariable Long subPageId){
        List<ParentPageResponse.Response> list = parentPageService.findList();
        return responseUtil.makeResponseEntity(list);
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ParentPageRequest.RequestDelete requestDelete){
        parentPageService.deleteParentPageById(requestDelete.getParentPageId());
        return responseUtil.makeResponseEntity();
    }
}

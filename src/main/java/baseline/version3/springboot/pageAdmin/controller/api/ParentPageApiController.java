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
    public ResponseEntity<ResponseForm> registerParentPage(ParentPageRequest.RequestInsert requestInsert){
        parentPageService.registerParentPage(requestInsert);

        return responseUtil.makeResponseEntity();
    }

    @PutMapping
    public ResponseEntity<ResponseForm> updateParentPage(ParentPageRequest.RequestUpdate requestUpdate){
        parentPageService.updateParentPage(requestUpdate);

        return responseUtil.makeResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> deleteParentPage(@RequestParam Long id){
        parentPageService.deleteParentPageById(id);

        return responseUtil.makeResponseEntity();
    }
}

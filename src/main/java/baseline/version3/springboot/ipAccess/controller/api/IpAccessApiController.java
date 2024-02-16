package baseline.version3.springboot.ipAccess.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.ipAccess.domain.IpAccessRequest;
import baseline.version3.springboot.ipAccess.domain.IpAccessResponse;
import baseline.version3.springboot.ipAccess.service.IpAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/ip-access")
@RequiredArgsConstructor
public class IpAccessApiController {
    private final IpAccessService ipAccessService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseForm> selectOne(@PathVariable Long id){
        IpAccessResponse.Response response = ipAccessService.findOne(id);
        return ResponseUtil.makeResponseEntity(response);
    }

    @GetMapping
    public ResponseEntity<ResponseForm> selectList(){
        List<IpAccessResponse.Response> responseList = ipAccessService.findList();
        return ResponseUtil.makeResponseEntity(responseList);
    }

    @PostMapping
    public ResponseEntity<ResponseForm> insert(@RequestBody IpAccessRequest.Request request){
        ipAccessService.insertOne(request);

        return ResponseUtil.makeResponseEntity();
    }

    @PutMapping
    public ResponseEntity<ResponseForm> update(@RequestBody IpAccessRequest.RequestUpdate requestUpdate){
        ipAccessService.updateOne(requestUpdate);

        return ResponseUtil.makeResponseEntity();
    }
}

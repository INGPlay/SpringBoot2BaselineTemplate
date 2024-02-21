package baseline.version3.springboot.pageAdmin.ipAccess.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.common.util.ValidationUtil;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessRequest;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessResponse;
import baseline.version3.springboot.pageAdmin.ipAccess.service.IpAccessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/page-admin/ip-access")
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
    public ResponseEntity<ResponseForm> insert(@Valid @RequestBody IpAccessRequest.Request request,
                                               BindingResult bindingResult){

        ValidationUtil.checkErrors(bindingResult);

        ipAccessService.insertOne(request);

        return ResponseUtil.makeResponseEntity();
    }

    @PutMapping
    public ResponseEntity<ResponseForm> update(@Valid @RequestBody IpAccessRequest.RequestUpdate requestUpdate,
                                               BindingResult bindingResult){

        ValidationUtil.checkErrors(bindingResult);

        ipAccessService.updateOne(requestUpdate);

        return ResponseUtil.makeResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@Valid @RequestBody IpAccessRequest.RequestDelete requestDelete,
                                               BindingResult bindingResult){
        ValidationUtil.checkErrors(bindingResult);

        ipAccessService.deleteOne(requestDelete);

        return ResponseUtil.makeResponseEntity();
    }
}

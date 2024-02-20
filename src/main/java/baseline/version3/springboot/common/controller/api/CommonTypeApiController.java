package baseline.version3.springboot.common.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.domain.ResponseTypeForm;
import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/common/type")
@RequiredArgsConstructor
public class CommonTypeApiController {
    @GetMapping("/active-status")
    public ResponseEntity<ResponseForm> authorityType(){
        List<ResponseTypeForm> response = Arrays.stream(ApplyStatus.values()).map(status -> new ResponseTypeForm(
                status.getText(),
                status.name()
        )).toList();
        return ResponseUtil.makeResponseEntity(response);
    }
}

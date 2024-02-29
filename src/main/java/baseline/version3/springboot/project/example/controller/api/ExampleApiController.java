package baseline.version3.springboot.project.example.controller.api;

import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.project.example.domain.ExampleRequest;
import baseline.version3.springboot.project.example.domain.ExampleResponse;
import baseline.version3.springboot.project.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/example")
@RequiredArgsConstructor
public class ExampleApiController {
    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseForm> selectOne(@PathVariable Long id,
                                                  @RequestBody ExampleRequest.RequestDynamicQueryOne requestDynamicQueryOne){

        requestDynamicQueryOne.setId(id);
        ExampleResponse.Response one = exampleService.findOne(requestDynamicQueryOne);
        return ResponseUtil.makeResponseEntity(one);
    }

    @GetMapping
    public ResponseEntity<ResponseForm> selectList(@RequestBody ExampleRequest.RequestDynamicQuery requestDynamicQuery){
        List<ExampleResponse.Response> list = exampleService.findList(requestDynamicQuery);
        return ResponseUtil.makeResponseEntity(list);
    }

    @PostMapping
    public ResponseEntity<ResponseForm> insert(@RequestBody ExampleRequest.RequestInsert requestInsert){
        exampleService.insertOne(requestInsert);

        return ResponseUtil.makeResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseForm> update(@RequestBody ExampleRequest.RequestUpdate requestUpdate){

        exampleService.updateOne(requestUpdate);

        return ResponseUtil.makeResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<ResponseForm> delete(@RequestBody ExampleRequest.RequestDelete requestDelete){
        exampleService.deleteOne(requestDelete);

        return ResponseUtil.makeResponseEntity();
    }
}

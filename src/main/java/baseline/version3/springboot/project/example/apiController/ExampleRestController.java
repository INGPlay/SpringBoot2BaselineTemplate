package baseline.version3.springboot.project.example.apiController;

import baseline.version3.springboot.project.example.domain.ExampleRequest;
import baseline.version3.springboot.project.example.domain.ExampleResponse;
import baseline.version3.springboot.project.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/example")
@RequiredArgsConstructor
public class ExampleRestController {
    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public ExampleResponse.Response selectOne(@PathVariable Long id){
        return exampleService.findOne(id);
    }

    @GetMapping
    public List<ExampleResponse.Response> selectList(){
        return exampleService.findList();
    }

    @PostMapping
    public String insert(@RequestBody ExampleRequest.Request editExampleDTO){
        exampleService.insertOne(editExampleDTO);

        return "ok";
    }

    @PutMapping
    public String update(@RequestBody ExampleRequest.Request editExampleDTO){

        exampleService.updateOne(editExampleDTO);

        return "ok";
    }
}

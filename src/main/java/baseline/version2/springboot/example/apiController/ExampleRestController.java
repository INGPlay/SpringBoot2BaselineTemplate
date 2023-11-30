package baseline.version2.springboot.example.apiController;

import baseline.version2.springboot.example.domain.dto.ExampleDomain;
import baseline.version2.springboot.example.service.ExampleService;
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
    public ExampleDomain.ResponseVO selectOne(@PathVariable Long id){
        return exampleService.findOne(id);
    }

    @GetMapping
    public List<ExampleDomain.ResponseVO> selectList(){
        return exampleService.findList();
    }

    @PostMapping
    public String insert(@RequestBody ExampleDomain.EditDTO editExampleDTO){
        exampleService.insertOne(editExampleDTO);

        return "ok";
    }

    @PutMapping
    public String update(@RequestBody ExampleDomain.EditDTO editExampleDTO){

        exampleService.updateOne(editExampleDTO);

        return "ok";
    }
}

package baseline.version2.springboot.example.apiController;

import baseline.version2.springboot.example.domain.EditExampleDTO;
import baseline.version2.springboot.example.domain.ResponseVO;
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
    public ResponseVO selectOne(@PathVariable Long id){
        return exampleService.findOne(id);
    }

    @GetMapping
    public List<ResponseVO> selectList(){
        return exampleService.findList();
    }

    @PostMapping
    public String insert(@RequestBody EditExampleDTO editExampleDTO){
        exampleService.insertOne(editExampleDTO);

        return "ok";
    }

    @PutMapping
    public String update(@RequestBody EditExampleDTO editExampleDTO){

        exampleService.updateOne(editExampleDTO);

        return "ok";
    }
}

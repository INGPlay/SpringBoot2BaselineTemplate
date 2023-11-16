package baseline.version2.springboot.example.service;

import baseline.version2.springboot.common.entity.Example;
import baseline.version2.springboot.example.domain.ExampleMapper;
import baseline.version2.springboot.example.domain.EditExampleDTO;
import baseline.version2.springboot.example.domain.ResponseVO;
import baseline.version2.springboot.example.repository.ExampleRepository;
import baseline.version2.springboot.example.repository.queryDsl.QueryExampleRepository;
import baseline.version2.springboot.exceptionHandler.exception.NotFoundCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ExampleService {
    private final QueryExampleRepository queryExampleRepository;
    private final ExampleRepository exampleRepository;

    public ResponseVO findOne(Long id){
        Example example = exampleRepository.findById(id).orElseThrow(
                () -> new NotFoundCustomException("값이 없습니다.")
        );

        return ExampleMapper.INSTANCE.entityToResponse(example);
    }

    public List<ResponseVO> findList(){
        List<Example> all = exampleRepository.findAll();
        return all.stream().map(example -> ExampleMapper.INSTANCE.entityToResponse(example))
                .collect(Collectors.toList());
    }

    public void insertOne(EditExampleDTO editExampleDTO){
        Example example = ExampleMapper.INSTANCE.requestInsert(editExampleDTO);

        exampleRepository.save(example);
    }

    public void updateOne(EditExampleDTO editExampleDTO){
        Example example = ExampleMapper.INSTANCE.requestUpdate(editExampleDTO);

        exampleRepository.save(example);
    }
}

package baseline.version3.springboot.project.example.service;

import baseline.version3.springboot.project.example.domain.ExampleMapper;
import baseline.version3.springboot.project.example.domain.ExampleRequest;
import baseline.version3.springboot.project.example.repository.entity.Example;
import baseline.version3.springboot.project.example.domain.ExampleResponse;
import baseline.version3.springboot.project.example.repository.ExampleRepository;
import baseline.version3.springboot.project.example.repository.queryDsl.QueryExampleRepository;
import faraway.util.restformat.domain.exception.ServiceException;
import faraway.util.restformat.domain.exception.ServiceLayerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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

    private final ExampleMapper exampleMapper;

    private final QueryExampleRepository queryExampleRepository;
    private final ExampleRepository exampleRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void exampleEvent(ExampleRequest.Request request){
        ExampleRequest.Event event = exampleMapper.toEvent(request);
        applicationEventPublisher.publishEvent(event);
    }

    @Transactional(readOnly = true)
    public ExampleResponse.Response findOne(Long id){
        Example example = exampleRepository.findById(id).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        return exampleMapper.toResponse(example);
    }

    @Transactional(readOnly = true)
    public List<ExampleResponse.Response> findList(){
        List<Example> all = exampleRepository.findAll();
        return all.stream().map(example -> exampleMapper.toResponse(example))
                .collect(Collectors.toList());
    }

    public void insertOne(ExampleRequest.Request editExampleDTO){
        Example example = exampleMapper.requestInsert(editExampleDTO);

        exampleRepository.save(example);
    }

    public void updateOne(ExampleRequest.Request editExampleDTO){

        Example example = exampleRepository.findById(editExampleDTO.getId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        exampleMapper.requestUpdate(editExampleDTO, example);

        exampleRepository.save(example);
    }
}

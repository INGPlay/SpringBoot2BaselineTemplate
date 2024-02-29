package baseline.version3.springboot.project.example.service;

import baseline.version3.springboot.project.example.domain.ExampleMapper;
import baseline.version3.springboot.project.example.domain.ExampleRequest;
import baseline.version3.springboot.project.example.entity.Example;
import baseline.version3.springboot.project.example.domain.ExampleResponse;
import baseline.version3.springboot.project.example.repository.ExampleRepository;
import baseline.version3.springboot.project.example.repository.queryDsl.QExampleRepository;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleMapper exampleMapper;

    private final QExampleRepository qExampleRepository;
    private final ExampleRepository exampleRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional(readOnly = true)
    public ExampleResponse.Response findOne(ExampleRequest.RequestDynamicQueryOne requestDynamicQueryOne){

        return qExampleRepository.selectOne(requestDynamicQueryOne).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
    }

    @Transactional(readOnly = true)
    public List<ExampleResponse.Response> findList(ExampleRequest.RequestDynamicQuery requestDynamicQuery){
        return qExampleRepository.selectList(requestDynamicQuery);
    }

    public void insertOne(ExampleRequest.RequestInsert editExampleDTO){
        Example example = exampleMapper.requestInsert(editExampleDTO);

        exampleRepository.save(example);
    }

    public void updateOne(ExampleRequest.RequestUpdate requestUpdate){

        Example example = exampleRepository.findById(requestUpdate.getId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        exampleMapper.requestUpdate(requestUpdate, example);

        exampleRepository.save(example);
    }

    public void deleteOne(ExampleRequest.RequestDelete requestDelete){
        Example example = exampleRepository.findById(requestDelete.getId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        exampleRepository.delete(example);
    }
}

package baseline.version3.springboot.project.example.event;

import baseline.version3.springboot.project.example.domain.ExampleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleEventListener {

    @TransactionalEventListener
    public void requestDynamicQuery(ExampleRequest.RequestDynamicQuery requestDynamicQuery){
        log.info("----- 이벤트 발생 -----");
    }

    @TransactionalEventListener
    public void requestDynamicQueryOne(ExampleRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        log.info("----- 이벤트 발생 -----");
    }
    @TransactionalEventListener
    public void requestInsert(ExampleRequest.RequestInsert requestInsert){
        log.info("----- 이벤트 발생 -----");
    }

    @TransactionalEventListener
    public void requestUpdate(ExampleRequest.RequestUpdate requestUpdate){
        log.info("----- 이벤트 발생 -----");
    }

    @TransactionalEventListener
    public void requestDelete(ExampleRequest.RequestDelete requestDelete){
        log.info("----- 이벤트 발생 -----");
    }
}

package baseline.version3.springboot.project.example.service.event;

import baseline.version3.springboot.project.example.domain.ExampleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleEventListner {

    @EventListener
    public void exampleListener(ExampleRequest.Event event){
        log.info("----- 이벤트 발생 -----");
    }
}

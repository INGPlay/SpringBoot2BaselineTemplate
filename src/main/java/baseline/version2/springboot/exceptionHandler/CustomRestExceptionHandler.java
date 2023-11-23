package baseline.version2.springboot.exceptionHandler;

import baseline.version2.springboot.common.util.ResponseUtil;
import baseline.version2.springboot.exceptionHandler.exception.ServiceLayerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomRestExceptionHandler {

    private ResponseUtil responseUtil;

    @ExceptionHandler(ServiceLayerException.class)
    protected ResponseEntity<?> serviceLayerException(ServiceLayerException serviceLayerException){
        return responseUtil.makeExceptionEntity(serviceLayerException.getServiceException());
    }
}

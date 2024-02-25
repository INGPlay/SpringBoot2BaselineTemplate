package baseline.version3.springboot.controllerAdvice;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
@Order(100)
public class CustomRestExceptionHandler {

    /**
     * Service Layer 예외 처리
     * @param serviceLayerException
     * @return
     */
    @ExceptionHandler(ServiceLayerException.class)
    protected ResponseEntity<?> serviceLayerException(ServiceLayerException serviceLayerException){
        log.error("[ServiceLayerException] {}", serviceLayerException.getStackTrace()[0]);
        return ResponseUtil.makeExceptionEntity(serviceLayerException.getServiceException());
    }

    /**
     * Custom BindingResult 예외 처리
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> handleValidationException(CustomValidationException ex){
        log.error("[ValidateException] {}", ex.getStackTrace()[0]);
        return ResponseUtil.makeExceptionEntity(ex.getBindingResult());
    }

    /**
     * @Valid 만 붙고, BindingResult가 없어야 예외 처리
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseUtil.makeExceptionEntity(ex.getBindingResult());
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        return ResponseUtil.makeExceptionEntity("권한이 없습니다.", HttpStatus.UNAUTHORIZED);
    }
}

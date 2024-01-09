package baseline.version3.springboot.exceptionHandler;

import baseline.version3.springboot.common.util.ResponseUtil;
import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomRestExceptionHandler {

    private ResponseUtil responseUtil;

    /**
     * 모든 Rest 컨트롤러 매핑 공통
     */
    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();

        log.info("[API] {}", requestURI);
    }

    @ExceptionHandler(ServiceLayerException.class)
    protected ResponseEntity<?> serviceLayerException(ServiceLayerException serviceLayerException){
        log.error("[ServiceLayerException] {}", serviceLayerException.getStackTrace()[0]);
        return responseUtil.makeExceptionEntity(serviceLayerException.getServiceException());
    }
}

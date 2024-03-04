package baseline.version3.springboot.controllerAdvice;

import baseline.version3.springboot.common.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected String exception(Exception exception,
                               HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes) {
        log.error("[RuntimeException] {}", exception.getStackTrace()[0]);
        redirectAttributes.addFlashAttribute("referer", HttpUtil.getReferrer(httpServletRequest));
        redirectAttributes.addFlashAttribute("errorMessage", "페이지에서 에러가 발생하였습니다.");
        return "redirect:/error";
    }

}

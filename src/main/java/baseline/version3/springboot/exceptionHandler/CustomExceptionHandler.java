package baseline.version3.springboot.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 모든 컨트롤러 매핑 공통
     */
    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();

        log.info("[URI] {}", requestURI);
    }

    @ExceptionHandler(RuntimeException.class)
    protected String exception(RuntimeException exception,
                               HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes){
        log.error("[RuntimeException] {}", exception.getStackTrace()[0]);
        redirectAttributes.addFlashAttribute("referer", getReferer(httpServletRequest));
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        return "redirect:/exception";
    }

    private static String getReferer(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Referer");
    }
}

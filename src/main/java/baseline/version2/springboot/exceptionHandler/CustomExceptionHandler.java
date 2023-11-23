package baseline.version2.springboot.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected String exception(RuntimeException exception,
                               HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("referer", getReferer(httpServletRequest));
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        return "redirect:/exception";
    }

    private static String getReferer(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Referer");
    }
}

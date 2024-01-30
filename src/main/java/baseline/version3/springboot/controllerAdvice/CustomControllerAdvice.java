package baseline.version3.springboot.controllerAdvice;

import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.service.SubPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CustomControllerAdvice {

    private final SubPageService subPageService;

    /**
     * 모든 컨트롤러 매핑 공통
     */
    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {

        Optional<SubPageResponse.Response> optionalResponse = subPageService.findList(SubPageRequest.RequestDynamicQuery.builder().build()).stream().filter(
                subPage -> subPage.concatPagePath().equals(request.getRequestURI())
        ).findAny();

        if (optionalResponse.isPresent()){
            SubPageResponse.Response subPage = optionalResponse.get();
            model.addAttribute("parentPageTitle", subPage.parentPageTitle());
            model.addAttribute("subPageTitle", subPage.parentPageTitle());

            log.info("Matching !!!!!");
        }

        log.info("Request : {} {}", request.getMethod(), request.getRequestURI());
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

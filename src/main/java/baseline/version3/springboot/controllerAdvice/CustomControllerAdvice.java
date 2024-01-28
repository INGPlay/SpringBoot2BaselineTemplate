package baseline.version3.springboot.controllerAdvice;

import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageRequest;
import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageResponse;
import baseline.version3.springboot.pageAdmin.service.ConcatPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CustomControllerAdvice {

    private final ConcatPageService concatPageService;

    /**
     * 모든 컨트롤러 매핑 공통
     */
    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {
        ConcatPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new ConcatPageRequest.RequestDynamicQueryOne();
        requestDynamicQueryOne.setConcatPagePath(request.getRequestURI());
        ConcatPageResponse.Response response = concatPageService.findOne(requestDynamicQueryOne).orElse(null);

        if (response != null){
            log.info("Matching !!!!!");
            model.addAttribute("parentPageTitle", response.getParentPageTitle());
            model.addAttribute("subPageTitle", response.getParentPageTitle());
            model.addAttribute("pageIndexPath", response.getParentPageIndexPath());
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

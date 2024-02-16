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

import java.util.List;
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

        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(request.getRequestURI());
        Optional<SubPageResponse.Response> response = subPageService.findOne(requestDynamicQueryOne);

        if (response.isPresent()){
            SubPageResponse.Response subPage = response.get();
            model.addAttribute("pageInfo", subPage);
        }

        log.info("Request : {} {}", request.getMethod(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    protected String exception(Exception exception,
                               HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes){
        log.error("[RuntimeException] {}", exception.getStackTrace()[0]);
        redirectAttributes.addFlashAttribute("referer", getReferer(httpServletRequest));
        redirectAttributes.addFlashAttribute("errorMessage", "페이지에서 에러가 발생하였습니다.");
        return "redirect:/error";
    }

    private static String getReferer(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Referer");
    }
}

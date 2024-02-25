package baseline.version3.springboot.pageAdmin.page.aspect;

import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.page.service.SubPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class PageAdminAdvice {

    private final SubPageService subPageService;

    /**
     * 모든 컨트롤러 매핑 공통
     */
    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {

        log.info("Request : {} {}", request.getMethod(), request.getRequestURI());

        if (request.getMethod().equalsIgnoreCase("GET")) {
            SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(request.getRequestURI());
            Optional<SubPageResponse.Response> response = subPageService.findOne(requestDynamicQueryOne);

            if (response.isPresent()){
                SubPageResponse.Response subPage = response.get();
                model.addAttribute("pageInfo", subPage);
            }
        }
    }

}

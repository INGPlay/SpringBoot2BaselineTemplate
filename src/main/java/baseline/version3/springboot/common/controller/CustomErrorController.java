package baseline.version3.springboot.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String REFERER = "referer";
    private static final String ERROR_MESSAGE = "errorMessage";
    @GetMapping("/error")
    public String errorPage(HttpServletRequest request,
                            HttpServletResponse response,
                            @ModelAttribute(ERROR_MESSAGE) String errorMessage,
                            @ModelAttribute(REFERER) String referer,
                            Model model){

        model.addAttribute(REFERER, getReferer(request));

        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            model.addAttribute(ERROR_MESSAGE, "찾을 수 없는 페이지 입니다.");
        } else if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN){
            model.addAttribute(ERROR_MESSAGE, "권한이 없습니다.");
        } else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            model.addAttribute(ERROR_MESSAGE, "서버 에러입니다.");
        }

        return "error";
    }

    private static String getReferer(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Referer");
    }
}

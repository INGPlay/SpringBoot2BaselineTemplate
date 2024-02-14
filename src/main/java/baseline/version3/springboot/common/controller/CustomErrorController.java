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

    @GetMapping("/error")
    public String errorPage(HttpServletRequest request,
                            HttpServletResponse response,
                            @ModelAttribute("errorMessage") String errorMessage,
                            @ModelAttribute("referer") String referer,
                            Model model){

        model.addAttribute("referer", getReferer(request));
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            model.addAttribute("errorMessage", "찾을 수 없는 페이지 입니다.");
        } else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            model.addAttribute("errorMessage", "서버 에러입니다.");
        }

        return "error";
    }

    private static String getReferer(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Referer");
    }
}

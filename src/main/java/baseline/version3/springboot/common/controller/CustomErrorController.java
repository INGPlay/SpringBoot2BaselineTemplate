package baseline.version3.springboot.common.controller;

import baseline.version3.springboot.common.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String REFERRER = "referrer";
    private static final String ERROR_MESSAGE = "errorMessage";
    @GetMapping("/error")
    public String errorPage(HttpServletRequest request,
                            HttpServletResponse response,
                            @ModelAttribute(ERROR_MESSAGE) String errorMessage,
                            @ModelAttribute(REFERRER) String referer,
                            Model model){

        model.addAttribute(REFERRER, HttpUtil.getReferrer(request));

        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            model.addAttribute(ERROR_MESSAGE, "찾을 수 없는 페이지 입니다.");
        } else if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN){
            model.addAttribute(ERROR_MESSAGE, "권한이 없습니다.");
        } else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            model.addAttribute(ERROR_MESSAGE, "서버 에러입니다.");
        }

        return "error";
    }
}

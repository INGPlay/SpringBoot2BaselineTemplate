package baseline.version2.springboot.common.controller;

import baseline.version2.springboot.common.ActiveStatusEnum;
import baseline.version2.springboot.common.TempDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {

    @GetMapping
    public String rootPage(){
        return "index";
    }
}

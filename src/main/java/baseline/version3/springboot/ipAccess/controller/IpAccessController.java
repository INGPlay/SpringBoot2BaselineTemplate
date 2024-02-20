package baseline.version3.springboot.ipAccess.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ip-access")
@RequiredArgsConstructor
public class IpAccessController {

    @GetMapping(value = { "/list"})
    public String list(){
        return "ip-access/list";
    }
}

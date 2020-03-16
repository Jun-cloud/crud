package boot.jpa.crud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

        // 자동으로 잡아줌
        return "index";
    }

}

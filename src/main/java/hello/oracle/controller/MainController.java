package hello.oracle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value={"/", "/layout/main.do"})
    public String main() {
        return "layout/main";
    }
}

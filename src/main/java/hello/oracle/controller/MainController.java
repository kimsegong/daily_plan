package hello.oracle.controller;

import hello.oracle.service.PlanServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final PlanServiceImpl planService;

    @GetMapping(value={"/", "/layout/main.do"})
    public String main() {
        return "layout/main";
    }

    @GetMapping(value="/layout/main.do")
    public Map<String, Object> getPlan(@RequestParam(value = "userNo", required = false, defaultValue = "defaultCondition")
                                       int userNo, HttpServletRequest request){
        return planService.getPlan(userNo, request);
    }
}

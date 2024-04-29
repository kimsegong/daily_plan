package hello.oracle.controller;

import hello.oracle.dto.PlanDto;
import hello.oracle.service.PlanServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class KakaoController {

    private final PlanServiceImpl planService;

    @GetMapping(value="/kakaoMap/mapMain.do")
    public String kakaoMain(){
        return "kakaoMap/mapMain";
    }





}

package hello.oracle.controller;

import hello.oracle.dto.PlanDto;
import hello.oracle.service.PlanServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class PlanController {

    private final PlanServiceImpl planService;


    @GetMapping("/plan/write.form")
    public String writePlan(){
        return "plan/write";
    }

    @PostMapping("/plan/add.do")
    public String insertPlan(HttpServletRequest request){
        planService.insertPlan(request);
        return "redirect:/layout/main.do";
    }



}

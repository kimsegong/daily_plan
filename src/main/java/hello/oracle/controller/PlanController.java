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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(value="/plan")
@Controller
public class PlanController {

    private final PlanServiceImpl planService;


    @GetMapping("/write.form")
    public String writePlan(){
        return "plan/write";
    }

    @PostMapping("/add.do")
    public String insertPlan(HttpServletRequest request){
        planService.insertPlan(request);
        return "redirect:/layout/main.do";
    }

    @GetMapping("/allPlan.do")
    public String getAllPlan(@RequestParam(value="userNo", required=false, defaultValue="0") int userNo, Model model) throws Exception {
        List<PlanDto> plan = planService.getPlan(userNo);
        model.addAttribute("plan", plan);
        return "plan/allPlan";
    }

    @ResponseBody
    @GetMapping(value="/getPlanList.do", produces="application/json")
    public Map<String, Object> allPlanLoad(@RequestParam(value="userNo", required=false, defaultValue="0") int userNo, HttpServletRequest request){
        return planService.getAllPlan(request, userNo);
    }



}

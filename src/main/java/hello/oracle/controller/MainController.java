package hello.oracle.controller;

import hello.oracle.dto.PlanDto;
import hello.oracle.service.PlanServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final PlanServiceImpl planService;

    @GetMapping(value={"/", "/layout/main.do"})
    public String main(){
        return "layout/main";
    }

    @GetMapping("/layout/plan.do")
    public ResponseEntity<List<PlanDto>> getPlans() {
        try {
            List<PlanDto> plans = planService.getPlan();
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "/layout/modify.do", produces ="application/json")
    public Map<String, Object> updatePlan(HttpServletRequest request, RedirectAttributes redirectAttributes){

        Map<String, Object> response = new HashMap<>();
        int modifyResult = planService.modifyPlan(request);
        response.put("modifyResult", modifyResult);
        System.out.println("!!!!!!!!!!!!!!!!!!!" + modifyResult);
        return response;
    }

}

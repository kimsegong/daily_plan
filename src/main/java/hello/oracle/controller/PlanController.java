package hello.oracle.controller;

import hello.oracle.dto.PlanDto;
import hello.oracle.service.PlanServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlanController {

    private final PlanServiceImpl planService;

    @GetMapping("/layout/plan.do")
    public ResponseEntity<List<PlanDto>> getPlans() {
        try {
            List<PlanDto> plans = planService.getPlan();
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plan/write.form")
    public String writePlan(){
        return "plan/write";
    }

    @PostMapping("/layout/add.do")
    public ResponseEntity<String> insertPlan(HttpServletRequest request, HttpServletResponse response) {
        try {
            planService.insertPlan(request, response);
            return ResponseEntity.ok("Event added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding event");
        }
        
    }

}

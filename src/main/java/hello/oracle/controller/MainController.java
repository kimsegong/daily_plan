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
public class MainController {

    private final PlanServiceImpl planService;

    @GetMapping(value={"/", "/layout/main.do"})
    public String main(){
        return "layout/main";
    }


    // 마이페이지에서 수정
    @ResponseBody                        
    @GetMapping(value="/layout/selectPlanModal.do", produces ="application/json")
    public ResponseEntity<PlanDto> selectPlanModal(@RequestParam(value="userNo", required=false, defaultValue="0") int userNo,
                                                  @RequestParam(value="planNo", required=false, defaultValue="0") int planNo) {
        try {
            PlanDto plan = planService.getPlanModal(userNo, planNo); // 결과를 PlanDto 객체로 가정
            if (plan != null) {
                return ResponseEntity.ok(plan); // 성공적으로 데이터를 찾은 경우
            } else {
                return ResponseEntity.notFound().build(); // 데이터를 찾지 못한 경우
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @ResponseBody
    @GetMapping("/layout/plan.do")
    public ResponseEntity<List<PlanDto>> getPlan(@RequestParam(value="userNo", required=false, defaultValue="0") int userNo){
        try {
            List<PlanDto> planList = planService.getPlan(userNo);
            return new ResponseEntity<>(planList, HttpStatus.OK);
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
        return response;
    }

    @ResponseBody
    @PostMapping(value = "/layout/delete.do", produces ="application/json")
    public Map<String, Object> deletePlan(@RequestParam(value="planNo", required=false, defaultValue="0") int planNo){
        return planService.deletePlan(planNo);
    }

}

package hello.oracle.service;

import hello.oracle.dao.PlanMapper;
import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService{

    private final PlanMapper planMapper;
    @Transactional(readOnly=true)
    @Override
    public void getPlan(HttpServletRequest request, Model model) throws Exception {
        int userNo = Integer.parseInt(request.getParameter("userNo"));

        Map<String, Object> map = Map.of("userNo", userNo);

        List<PlanDto> planList = planMapper.selectPlan(map);

        model.addAttribute("planList", planList);
        String params = "userNo=" + request.getParameter("userNo");

    }

    @Override
    public void insertPlan(HttpServletRequest request) {

        String startAt = request.getParameter("startAt");
        String endAt = request.getParameter("endAt");
        String firstPlan = request.getParameter("firstPlan");
        String firstComment = request.getParameter("firstComment");
        String secondPlan = request.getParameter("secondPlan");
        String secondComment = request.getParameter("secondComment");
        String thirdPlan = request.getParameter("thirdPlan");
        String thirdComment = request.getParameter("thirdComment");

        PlanDto plan = PlanDto.builder()
                        .startAt(startAt)
                        .endAt(endAt)
                        .firstPlan(firstPlan)
                        .firstComment(firstComment)
                        .secondPlan(secondPlan)
                        .secondComment(secondComment)
                        .thirdPlan(thirdPlan)
                        .thirdComment(thirdComment)
                .build();
        int planResult = planMapper.insertPlan(plan);


    }

    @Override
    public int modifyPlan(HttpServletRequest request) {
        int planNo = Integer.parseInt(request.getParameter("planNo"));
        String startAt = request.getParameter("startAt");
        String endAt = request.getParameter("endAt");
        String firstPlan = request.getParameter("firstPlan");
        String firstComment = request.getParameter("firstComment");
        String secondPlan = request.getParameter("secondPlan");
        String secondComment = request.getParameter("secondComment");
        String thirdPlan = request.getParameter("thirdPlan");
        String thirdComment = request.getParameter("thirdComment");

        PlanDto plan = PlanDto.builder()
                .planNo(planNo)
                .startAt(startAt)
                .endAt(endAt)
                .firstPlan(firstPlan)
                .firstComment(firstComment)
                .secondPlan(secondPlan)
                .secondComment(secondComment)
                .thirdPlan(thirdPlan)
                .thirdComment(thirdComment)
                .build();

        int modifyResult = planMapper.modifyPlan(plan);

        return modifyResult;

    }

    @Override
    public Map<String, Object> deletePlan(int planNo) {
      int removeResult =  planMapper.deletePlan(planNo);
      return Map.of("removeResult", removeResult);
    }


}

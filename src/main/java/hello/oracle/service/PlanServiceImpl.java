package hello.oracle.service;

import hello.oracle.dao.PlanMapper;
import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService{

    private final PlanMapper planMapper;
    @Transactional(readOnly=true)
    @Override
    public List<PlanDto> getPlan() throws Exception {
        return planMapper.selectPlan();
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

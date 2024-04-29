package hello.oracle.service;

import hello.oracle.dao.PlanMapper;
import hello.oracle.dao.UserMapper;
import hello.oracle.dto.PlanDto;
import hello.oracle.util.MyPageUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService{

    private final PlanMapper planMapper;
    private final UserMapper userMapper;
    private final MyPageUtils pageUtils;

    @Transactional(readOnly=true)
    @Override
    public List<PlanDto> getPlan(int userNo) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        return planMapper.selectPlan(params);

    }

    @Override
    public PlanDto getPlanModal(int userNo, int planNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("planNo", planNo);
        return planMapper.selectPlanModal(params);
    }

    @Transactional(readOnly=true)
    @Override
    public Map<String, Object> getAllPlan(HttpServletRequest request ,int userNo){
        Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
        int page = Integer.parseInt(opt.orElse("1"));
        int total = userMapper.getUserPlanCount();
        int display = 9;

        pageUtils.setPaging(page, total, display);

        Map<String, Object> map = Map.of("begin", pageUtils.getBegin()
                , "end", pageUtils.getEnd()
                , "userNo", userNo);

        List<PlanDto> planList = planMapper.selectPlan(map);
        return Map.of("planList", planList
                , "totalPage", pageUtils.getTotalPage());

    }


    @Override
    public void insertPlan(HttpServletRequest request) {

        int userNo = Integer.parseInt(request.getParameter("userNo"));
        String startAt = request.getParameter("startAt");
        String endAt = request.getParameter("endAt");
        String firstPlan = request.getParameter("firstPlan");
        String firstComment = request.getParameter("firstComment");
        String secondPlan = request.getParameter("secondPlan");
        String secondComment = request.getParameter("secondComment");
        String thirdPlan = request.getParameter("thirdPlan");
        String thirdComment = request.getParameter("thirdComment");

        PlanDto plan = PlanDto.builder()
                        .userNo(userNo)
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

package hello.oracle.service;

import hello.oracle.dao.PlanMapper;
import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService{

    private final PlanMapper planMapper;

    @Override
    public Map<String, Object> getPlan(int userNo, HttpServletRequest request) {

        Map<String, Object> map = Map.of(
                "userNo", userNo
        );

        List<PlanDto> planList = planMapper.selectPlan(map);
        return Map.of("planList", planList);
    }
}

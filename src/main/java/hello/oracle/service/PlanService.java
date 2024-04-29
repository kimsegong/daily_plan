package hello.oracle.service;

import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface PlanService {
    public List<PlanDto> getPlan(int userNo) throws Exception;
    public PlanDto getPlanModal(int userNo, int planNo);
    public Map<String, Object> getAllPlan(HttpServletRequest request, int userNo);
    public void insertPlan(HttpServletRequest request);
    public int modifyPlan(HttpServletRequest request);
    public Map<String, Object> deletePlan(int planNo);
}

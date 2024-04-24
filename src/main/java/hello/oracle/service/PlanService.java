package hello.oracle.service;

import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public interface PlanService {
    public List<PlanDto> getPlan() throws Exception;
    public void insertPlan(HttpServletRequest request);
    public int modifyPlan(HttpServletRequest request);
    public Map<String, Object> deletePlan(int planNo);
}

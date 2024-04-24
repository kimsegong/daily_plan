package hello.oracle.service;

import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface PlanService {
    public List<PlanDto> getPlan() throws Exception;
    public void insertPlan(HttpServletRequest request, HttpServletResponse response);
    public int modifyPlan(HttpServletRequest request);
}

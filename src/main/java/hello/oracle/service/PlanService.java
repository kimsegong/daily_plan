package hello.oracle.service;

import hello.oracle.dto.PlanDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface PlanService {
    public List<PlanDto> getPlan() throws Exception;
}
